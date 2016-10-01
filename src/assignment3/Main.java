/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Brian Sutherland
 * bcs2433
 * 16455
 * Michelle Tate
 * mct894
 * 16455
 * Slip days used: <0>
 * Git URL: https://github.com/synacktic/assignment3.git
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	static String start = null;
	static String end = null;
	
	/**
	  * This method is the main method of the whole Word Ladder program.
	  */
	public static void main(String[] args) throws Exception {
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
				
		ArrayList<String> keyWords = parse(kb);		//now has my start word and end word
		if(keyWords == null){return;}					//means that the input was "/quit"
		ArrayList<String> wordLadder;
		wordLadder = getWordLadderDFS(keyWords.get(0), keyWords.get(1));
		printLadder(wordLadder);
		wordLadder = getWordLadderBFS(keyWords.get(0), keyWords.get(1));
		printLadder(wordLadder);
	}
	
	
	/**
	  * This method initializes the variables that we need.
	  * @param no parameters
	  * @return nothing to return
	  */
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		start = null;
		end = null;
	}
	
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> wordLadder = new ArrayList<String>();
		System.out.println("Type two 5 letter words with at least one space separating both of them");
		String word1 = keyboard.next().trim().toLowerCase();	//whitespace must be ignored
		if (word1.equals("/quit")){
			System.exit(0);;							//stop running the program with no further output
		}
		String word2 = keyboard.next().trim().toLowerCase();	//whitespace must be ignored
		if (word2.equals("/quit")){
			System.exit(0);;							//stop running the program with no further output
		}
		start = word1;		//set static variable
		end = word2;		//set static variable	
		wordLadder.add(word1);
		wordLadder.add(word2);
		return wordLadder;
	}
	
	
	/**
	  * This method finds the ladder using BFS.
	  * @param start is the beginning of the ladder
	  * @param end is the end of the ladder
	  * @return the word ladder from start to end word with or without rungs in between
	  */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		DFS DFSladder = new DFS(makeDictionary());			//instantiate
		if(letterDifference(start,end)){
			return new ArrayList<String>();	//return an empty ArrayList<String>
		}
		ArrayList<String> DFSwordladder = new ArrayList<String>();
		DFSwordladder.add(start);	//add first word to ladder
		DFSwordladder = DFSladder.findLadder(start.toUpperCase(), end.toUpperCase(), DFSwordladder, -1);
//		if(DFSwordladder == null){			//need to add only start and end words if no ladder so that print works
//			ArrayList<String> DFSwordladder2 = new ArrayList<String>();
//			DFSwordladder2.add(start);
//			DFSwordladder2.add(end);
//			return DFSwordladder2;			//return empty list with only start and end
//		}
		if(DFSwordladder == null){
			return new ArrayList<String>();	//return an empty ArrayList<String>
		}
		if(DFSwordladder.isEmpty() || DFSwordladder.size() == 2){
			return new ArrayList<String>();	//return an empty ArrayList<String>
		}
		
		for(int i = 0; i < DFSwordladder.size(); i++){		//change all words to lower case
			DFSwordladder.set(i, DFSwordladder.get(i).toLowerCase());
		}
		
		
		
		//for testing
//		for(int i = 0; i < DFSwordladder.size() - 1; i++){
//			if(letterDifference(DFSwordladder.get(i),DFSwordladder.get(i+1)) == false){
//				System.out.println("FAIL");
//				System.out.println(DFSwordladder.get(i));
//				System.out.println(DFSwordladder.get(i+1));
//				break;
//			}
//		}
		
		
		return DFSwordladder;					//return ladder if ladder exists
	}
	
	
	/**
	  * This method finds the ladder using BFS.
	  * @param start is the beginning of the ladder
	  * @param end is the end of the ladder
	  * @return the word ladder from start to end word with or without rungs in between
	  */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Set<String> dict = makeDictionary();
		if(letterDifference(start,end)){
			return new ArrayList<String>();	//return an empty ArrayList<String>
		}
		ArrayList<String> BFSwordladder = BFS.findLadder(start, end, dict);
//		if(BFSwordladder == null){			//need to add only start and end words if no ladder so that print works
//			ArrayList<String> BFSwordladder2 = new ArrayList<String>();
//			BFSwordladder2.add(start);
//			BFSwordladder2.add(end);
//			return BFSwordladder2;			//return empty list with only start and end
//		}
		if(BFSwordladder == null){
			return new ArrayList<String>();	//return an empty ArrayList<String>
		}
		if(BFSwordladder.isEmpty() || BFSwordladder.size() == 2){
			return new ArrayList<String>();	//return an empty ArrayList<String>
		}
		return BFSwordladder;
	}
    
    
	/**
	  * This method was given to use and makes our dictionary.
	  * @param no parameters
	  * @return the dictionary
	  */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	
	/**
	  * This method prints how many rungs the ladder contains and prints the ladder itself.
	  * @param ladder is the ladder that my getWordLadderDFS() or getWordLadderBFS() method returned
	  * @return nothing to return
	  */
	public static void printLadder(ArrayList<String> ladder) {
		if(ladder.isEmpty()){		//null ladder
			if(start == null && end == null){
				System.out.println("no word ladder can be found because no words have been typed and parse wasn't called.");
				return;
			}
			if(letterDifference(start, end)){		//one letter away
				System.out.println("a 0-rung word ladder exists between " + start + " and " + end + ".");
				System.out.println(start);
				System.out.println(end);
				return;
			}
			else{	//start and end not one letter away so no word ladder
				System.out.println("no word ladder can be found between " + start + " and " + end + ".");
				return;
			}
		}
		
		
//		if(ladder.size() == 2){		//only start and end are in the word ladder so no ladder
//			if(ladder.get(0).equals(ladder.get(1)) || letterDifference(ladder.get(0),ladder.get(1))){	//same word
//				System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
//				for(int i = 0; i < ladder.size(); i++){
//					System.out.println(ladder.get(i));
//				}
//				return;
//			}
//			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
//			return;		//no need to print the ladder
//		}
		
		//if you actually have a word ladder
		System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
		for(int i = 0; i < ladder.size(); i++){
			System.out.println(ladder.get(i));
		}
		System.out.println();
	}

	
	/**
	  * This method finds how many letters are different from word1 to word2.
	  * @param word1 is the beginning of the ladder
	  * @param word2 is the end of the ladder
	  * @return true if there is only one letter difference, false if there is more than one letter different
	  */
	public static boolean letterDifference(String word1, String word2){
		int difference = 0;
		for (int i = 0; i < 5; i++){		//may need to change 5 if they aren't 5 letter words
			if (word1.charAt(i) != word2.charAt(i)){
				difference +=1;
				if (difference > 1){
					return false;
				}
			}
		}
		return true;	//one letter away
	}

}
