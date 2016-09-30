package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DFSTest {

	@Test
	public void test() {
		
		//smart start			should print 0-rung ladder
		//smart smart			should print a 0-rung ladder		should not be tested
		//smart yyyyy			should print no ladder exists
		//smart money			should give some ladder
		//xxxxx yyyyy			should print no ladder exists
		
		ArrayList<String> DFSwordLadder = Main.getWordLadderDFS("smart", "start");
		//if(DFSwordLadder == null)
		//fail("Not yet implemented");

		boolean flag = true;
		for(int i = 0; i < DFSwordLadder.size() - 1; i++){
			if((i + 1 < DFSwordLadder.size()) && Main.letterDifference(DFSwordLadder.get(i),DFSwordLadder.get(i+1)) == false){
				flag = false;
			}
		}
		assertEquals(true, flag);
	}

}
