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
import java.util.LinkedList;

import java.util.Set;
import java.util.Collections;


public class BFS {

	/**
	 * 
	 * @param start is the start of the ladder
	 * @param end is the end of the ladder
	 * @param dict is the dictionary to use
	 * @return is the ladder or null on failure
	 */
	public static ArrayList<String> findLadder(String start, String end, Set<String> dict){
		
		if (start.equals(end)) {
			ArrayList<String> output = new ArrayList<String>(2);
			output.add(start);
			output.add(end);
			return output;
		}
		String[] adict;
		start = start.toUpperCase();
		end = end.toUpperCase();
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
						    Node endNode = new Node(addNode,end);
							queue.add(endNode);
						    ArrayList<String> output = new ArrayList<String>(endNode.distance);
							while (endNode != null) {
							    output.add(endNode.word.toLowerCase());
							    if (endNode.parent != null && endNode.word.equals(endNode.parent.word)) {
							    	// Don't duplicate last word on 0 rung ladders.
							    } else {
							    	endNode = endNode.parent;
							    }
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
	/**
	 * 
	 * BFS Nodes
	 *
	 */
	private static class Node {

		public String word; // The word being stored
		public Node parent; // The parent that led to this word
		public int distance; // the distance from start to here

		public Node(Node parent, String word) {
			this.word = word;
			this.parent = parent;
			if (parent == null) {
				distance = 0;
			} else {
				distance = parent.distance + 1; 
			}
		}
	}
}


