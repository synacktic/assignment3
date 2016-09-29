package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Set;
import java.util.Collections;


public class BFS {

	
	public static ArrayList<String> findLadder(String start, String end, Set<String> dict){
		String[] adict;
		adict = dict.toArray(new String[0]);
		LinkedList<BFSNode> queue = new LinkedList<BFSNode>();
	        BFSNode startNode = new BFSNode(null,start);
	        queue.add(startNode);
	   		int found = 0;

	       while (queue.size() != 0)
	       {
	            BFSNode currNode = queue.remove();
	       
	            String toComp = currNode.word;

		   		int dictCount = 0;
				while (dictCount < adict.length && found == 0) {
					if ( Main.letterDifference(toComp,adict[dictCount]))  {
						BFSNode addNode = new BFSNode(currNode,adict[dictCount]);
						queue.add(addNode);

						if ( Main.letterDifference(end,adict[dictCount])) {
							//System.out.printf("%s and %s are just one letter away!\n",end,adict[dictCount]);
						    //System.out.printf("Ladder has been found:\n ");
						    //System.out.printf("%s\n",end);
						    BFSNode endNode = new BFSNode(addNode,end);
							queue.add(endNode);
						    ArrayList<String> output = new ArrayList<String>(endNode.distance);
							while (endNode != null) {
							    output.add(endNode.word);
							    endNode = endNode.parent;
							}
							Collections.reverse(output);
							return output;
						}
						adict[dictCount] = "00000"; // FIVE
					}
					dictCount++;
				
				}
	       }

		
		return null;
	}
}


