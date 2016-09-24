package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class DFS {
	static ArrayList<String> dictionary = null;
	static ArrayList<String> visitedWords = new ArrayList<String>();

	
	DFS(Set<String> dict){
		ArrayList<String> dictionary = new ArrayList<String>(dict);
	}
	
	
	
	//TO DO: try and reduce the length of the ladder
	
	public static ArrayList<String> findLadder(String start, String end, ArrayList<String> DFSwordladder){
		//base case of one letter away
		if(letterDifference(start, end) == true){
			DFSwordladder.add(end);
			return DFSwordladder;
		}
		nextWord(start, end);
		
		return null;
	}
	
	
	//int dictionaryIndex = 0;
	//returns the next word that hasn't already been visited and that is also only one letter away from the current word 
	public static String nextWord(String word1, String word2){
		for(int i = 0; i < dictionary.size(); i++){
			if(letterDifference(word1, dictionary.get(i)) && !(visitedWords.contains(dictionary.get(i)))){
				visitedWords.add(dictionary.get(i));	//don't visit word again
				return dictionary.get(i);
			}
		}
		return null;
		
	}
	
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
