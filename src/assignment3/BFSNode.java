package assignment3;

public class BFSNode {

	public String word;
	public BFSNode parent;
	public int distance;

	//public BFSNode() {
	//}
	public BFSNode(BFSNode newParent, String newWord) {
		word = newWord;
		parent = newParent;
		if (parent == null) {
			distance = 0;
		} else {
			distance = parent.distance + 1; 
		}
	}

}
