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
		ArrayList<String> wordLadder = parse(kb);
		if(wordLadder == null){return;}
		if(wordLadder.get(0).equals(wordLadder.get(1))){
			System.out.println("a 0-rung word ladder exists between " + wordLadder.get(0) + " and " + wordLadder.get(1) + ".");
		}
		getWordLadderDFS(wordLadder.get(0), wordLadder.get(1));
		wordLadder = getWordLadderBFS(wordLadder.get(0), wordLadder.get(1));
		for (String w: wordLadder) {
			  System.out.printf("%s\n", w);
		}
		//System.out.printf("Done\n");
		
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
		String word1 = keyboard.next().trim();	//whitespace must be ignored
		if (word1.equals("/quit")){
			return null;							//stop running the program with no further output
		}
		String word2 = keyboard.next().trim();	//whitespace must be ignored
		wordLadder.add(word1);
		wordLadder.add(word2);
		return wordLadder;
	}
	
	// Returned list should be ordered start to end.  Include start and end.
	// Return empty list if no ladder.
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		Set<String> dict = makeDictionary();

	//	DFS DFSladder = new DFS(dict);
		ArrayList<String> DFSwordladder = new ArrayList<String>();
		DFSwordladder.add(start);	//add first word to ladder
		//DFSwordladder = DFSladder.findLadder(start, end, DFSwordladder);
		
		if (DFSwordladder == null){
			return null;		//return empty list if no ladder
		}
		return DFSwordladder;	//return ladder if ladder exists
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		Set<String> dict = makeDictionary();
		return BFS.findLadder(start, end, dict);

		
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
