package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Set;
import java.util.Collections;


public class BFS {

	
	public static ArrayList<String> findLadder(String start, String end, Set<String> dict){
		String[] adict;
		adict = dict.toArray(new String[0]);
		LinkedList<Node> queue = new LinkedList<Node>();
	        Node startNode = new Node(null,start);
	        queue.add(startNode);
	   		int found = 0;

	       while (queue.size() != 0)
	       {
	            Node currNode = queue.remove();
	       
	            String toComp = currNode.word;

		   		int dictCount = 0;
				while (dictCount < adict.length && found == 0) {
					if ( Main.letterDifference(toComp,adict[dictCount]))  {
						Node addNode = new Node(currNode,adict[dictCount]);
						queue.add(addNode);

						if ( Main.letterDifference(end,adict[dictCount])) {
							//System.out.printf("%s and %s are just one letter away!\n",end,adict[dictCount]);
						    //System.out.printf("Ladder has been found:\n ");
						    //System.out.printf("%s\n",end);
						    Node endNode = new Node(addNode,end);
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
	private static class Node {

		public String word;
		public Node parent;
		public int distance;

		public Node(Node newParent, String newWord) {
			word = newWord;
			parent = newParent;
			if (parent == null) {
				distance = 0;
			} else {
				distance = parent.distance + 1; 
			}
		}
	}
}


