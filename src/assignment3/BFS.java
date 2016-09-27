package assignment3;

import java.util.ArrayList;
import java.util.Set;
import java.util.PriorityQueue;

public class BFS {
	//final static Set<String> dict = Main.makeDictionary();

	
	public static ArrayList<String> findLadder(String start, String end, Set<String> dict){
		String[] adict;
		adict = dict.toArray(new String[0]);
		//System.out.printf("%s\n",adict[0]);
	       //Comparator<String> comparator = new StringLengthComparator();
	        PriorityQueue<String> queue = new PriorityQueue<String>(1000);
	        queue.add(start);
	   		int found = 0;

	       while (queue.size() != 0 && found == 0)
	       {
	            String path = queue.remove();
	            String[] parts = path.split(",");
	            String toComp = parts[parts.length-1];
		   		int dictCount = 0;
				while (dictCount < adict.length && found == 0) {
					if ( Main.letterDifference(toComp,adict[dictCount]))  {
						queue.add(path + "," + adict[dictCount]);
						//System.out.printf("%s and %s are just one letter away!\n",toComp,adict[dictCount]);
				
						if ( Main.letterDifference(end,adict[dictCount])) {
							System.out.printf("%s and %s are just one letter away!\n",end,adict[dictCount]);
							System.out.printf("Ladder has been found:\n %s",path + "," + adict[dictCount] + "," + end);
							found++;
						}
						adict[dictCount] = "00000"; // FIVE
					}
					dictCount++;
				
				}
	       }
	     //  while (queue.size() != 0) {
	    	//   System.out.println(queue.remove());
	      // }
 
	
	/*	if (dict.contains(start)) {
			System.out.println("Hey! I know that word!");
		} else {
			System.out.println("What word is this?!!?");
		}*/
		
		return null;
	}
}
