/**
 * 
 */
package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author synacktic
 *
 */
public class BFSTest {


	@Test
	public void test() {
		String start = "SMART";
		String end = "MONEY";
		
		Set<String> dict = Main.makeDictionary();
		if (dict == null) {
			fail("Dictionary Generation Failed!");
		}
		/*SMART
		START
		STARS
		SOARS
		SOAKS
		SOCKS
		HOCKS
		HONKS
		HONES
		HONEY
		MONEY*/
		ArrayList<String> wordLadder = BFS.findLadder(start, end, dict);
		if (wordLadder == null) {
			fail("No such ladder!");
		}
		if (wordLadder.size() != 11) {
			fail("wordLadder is the wrong size");
		}
		//wordLadder.
	}

}
