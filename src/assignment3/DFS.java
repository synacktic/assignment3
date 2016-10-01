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

import java.util.ArrayList;

public class DFS {
	static ArrayList<String> updatingDictionary = null;
	static ArrayList<String> dictOriginal = null;
	static ArrayList<String> visitedWords = new ArrayList<String>();
	static ArrayList<String> alreadyUsedWords = new ArrayList<String>();

	
	/**
	  * This is the DFS class constructor
	  * @param dictionary is used to create my own copy of the dictionary
	  */
	DFS(java.util.Set<String> dict){
		updatingDictionary = new ArrayList<String>(dict);
		dictOriginal = new ArrayList<String>(dict);
	}
	
	
	/**
	  * This method finds the ladder using DFS.
	  * @param start is the beginning of the ladder
	  * @param end is the end of the ladder
	  * @param DFSwordLadder stores the rungs in my ladder
	  * @param prevChangedIndex tells me which index I should not change during this recursive call
	  * @return the word ladder from start to end word or returns null if no ladder exists
	  */
	public static ArrayList<String> findLadder(String start, String end, ArrayList<String> DFSwordladder, int prevChangedIndex ){
		
		//base case of same start and end word
		if(start.equals(end)){
			DFSwordladder.add(end);
			return DFSwordladder;
		}
		
		//base case of one letter away
		if(findChangedIndex(start, end) != -1){		//-1 means that they are not one letter apart
			DFSwordladder.add(end);
			return DFSwordladder;
		}
		
		ArrayList<String> currentLayerPossibleWords = findOneLetterDifference(start, prevChangedIndex);	//possible words for next layer
		if (currentLayerPossibleWords == null){return null;} //this path is a dead end		
		for(String word : currentLayerPossibleWords){
			DFSwordladder.add(word);
			int index = findChangedIndex(start, word); //in this case, there will always be a one letter difference
			ArrayList<String> nextLayerPossibleWords = findLadder(word, end, DFSwordladder, index);
			if (nextLayerPossibleWords == null){
				DFSwordladder.remove(word);
				continue;
			}
			return nextLayerPossibleWords;
		}
		return null;
	}
	
	
	/**
	  * This method finds the currently changed index of the previous word to the new word I am about to add to the word ladder.
	  * @param start is the beginning of the ladder
	  * @param end is the end of the ladder
	  * @return the index of the previously changed character
	  */
	private static int findChangedIndex(String start, String newWord){
		char[] startWordChar = start.toCharArray();
		char[] newWordChar = newWord.toCharArray();
		int difference = 0;
		int indexOfChanged = 0;
		for(int i = 0; i < 5; i++){
			if(startWordChar[i] != newWordChar[i]){
				difference++;
				indexOfChanged = i;
				if(difference > 1){return -1;}
			}
		}
		return indexOfChanged;	//this is the changed index

	}

	
	/**
	  * This method finds the list of words that my current word can change to and doesn't allow me to change the index that was previously changed.
	  * @param start is the beginning of the ladder
	  * @param dontChangeThisIndex tells me which index of the 5-letter word I can not change during this call
	  * @return the list of possible next words that my current word can change into
	  */
	private static ArrayList<String> findOneLetterDifference(String start, int dontChangeThisIndex){
		ArrayList<String> possibleWords = new ArrayList<String>();
		for (String word : dictOriginal){
			char[] startWordChar = start.toCharArray();
			char[] dictionaryWordChar = word.toCharArray();
			boolean flag = true;		//set flag to false if the next word changes the same index as the previous word
			int difference = 0;			//reset for each new dictionary word that is being checked
			for (int i = 0; i < 5; i++){
				if (startWordChar[i] != dictionaryWordChar[i]){
					difference += 1;						//increment for every changed letter from start to next word
					if (i == dontChangeThisIndex){
						flag = false;
						break;
					}
				}
			}
			if (difference == 1 && flag && !(alreadyUsedWords.contains(word))){	//can't have more than one letter difference from start to next word
				possibleWords.add(word);	//the current word can be part of this ladder
				updatingDictionary.remove(word);	//don't want to ever have this word in a lower ladder b/c this is inefficient and produces too long of a ladder
				alreadyUsedWords.add(word);	//same reason as above
			}
		}
		return possibleWords;
	}

}	
