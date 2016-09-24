package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class BFS {
	//final static Set<String> dict = Main.makeDictionary();

	
	public static ArrayList<String> findLadder(String start, String end, Set<String> dict){
		String[] adict;
		adict = dict.toArray(new String[0]);
		System.out.printf("%s\n",adict[0]);
		int dictCount = 0;
		while (dictCount < adict.length) {
			int letterCount = 0;
			int badletters = 0;
			while (letterCount < adict[dictCount].length()) {
				if () { }
				letterCount++;
			}
			dictCount++;
		}
		if (dict.contains(start)) {
			System.out.println("Hey! I know that word!");
		} else {
			System.out.println("What word is this?!!?");
		}
		
		return null;
	}
}
