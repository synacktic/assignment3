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
		
		// TODO methods to read in words, output ladder
		ArrayList<String> keyWords = parse(kb);		//now has my start word and end word
		if(keyWords == null){return;}					//means that the input was "/quit"
//		if(keyWords.get(0).equals(keyWords.get(1))){
//			System.out.println("a 0-rung word ladder exists between " + keyWords.get(0) + " and " + keyWords.get(1) + ".");
//		}
		ArrayList<String> wordLadder;

		wordLadder = getWordLadderDFS(keyWords.get(0), keyWords.get(1));

		printLadder(wordLadder);
		wordLadder = getWordLadderBFS(keyWords.get(0), keyWords.get(1));
		printLadder(wordLadder);
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
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
			return null;							//stop running the program with no further output
		}
		String word2 = keyboard.next().trim().toLowerCase();	//whitespace must be ignored
		wordLadder.add(word1);
		wordLadder.add(word2);
		return wordLadder;
	}
	
	
	// Returned list should be ordered start to end.  Include start and end.
	// Return empty list if no ladder.
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		DFS DFSladder = new DFS(makeDictionary());			//instantiate
		ArrayList<String> DFSwordladder = new ArrayList<String>();
		DFSwordladder.add(start);	//add first word to ladder
		DFSwordladder = DFSladder.findLadder(start.toUpperCase(), end.toUpperCase(), DFSwordladder, -1);
		if(DFSwordladder == null){			//need to add only start and end words if no ladder so that print works
			ArrayList<String> DFSwordladder2 = new ArrayList<String>();
			DFSwordladder2.add(start);
			DFSwordladder2.add(end);
			return DFSwordladder2;			//return empty list with only start and end
		}
		for(int i = 0; i < DFSwordladder.size(); i++){		//change all words to lower case
			DFSwordladder.set(i, DFSwordladder.get(i).toLowerCase());
		}
		
		//for testing
		//move to main
//		System.out.println("a " + (DFSwordladder.size() - 2) + "-rung word ladder exists between " + start + " and " + end + ".");
//		System.out.println();
//		for(int i = 0; i < DFSwordladder.size(); i++){
//			if((i + 1 < DFSwordladder.size()) && letterDifference(DFSwordladder.get(i),DFSwordladder.get(i+1)) == false){
//				System.out.println("FAIL");
//				return null;
//			}
//			System.out.println(DFSwordladder.get(i));
//		}
//		System.out.println();
		//end of for testing

		return DFSwordladder;					//return ladder if ladder exists
	}
	
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Set<String> dict = makeDictionary();
		ArrayList<String> BFSwordladder = BFS.findLadder(start, end, dict);
		if(BFSwordladder == null){			//need to add only start and end words if no ladder so that print works
			ArrayList<String> BFSwordladder2 = new ArrayList<String>();
			BFSwordladder2.add(start);
			BFSwordladder2.add(end);
			return BFSwordladder2;			//return empty list with only start and end
		}
		return BFSwordladder;
	}
    
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
	
	public static void printLadder(ArrayList<String> ladder) {

		if(ladder.size() == 2){		//only start and end are in the word ladder so no ladder
			if(ladder.get(0).equals(ladder.get(1))){	//same word
				System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
				for(int i = 0; i < ladder.size(); i++){
					System.out.println(ladder.get(i));
				}
				return;
			}
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
			return;		//no need to print the ladder
		}
		System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(0).toLowerCase() + " and " + ladder.get(ladder.size()-1) + ".");
		for(int i = 0; i < ladder.size(); i++){
			System.out.println(ladder.get(i));
		}
		System.out.println();
	}
	
	// TODO
	// Other private static methods here
	
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
