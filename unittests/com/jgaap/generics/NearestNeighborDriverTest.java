/**
 * 
 */
package com.jgaap.generics;

import static org.junit.Assert.*;

import org.junit.Test;


import com.jgaap.distances.CosineDistance;
import java.util.Vector;


/**
 * @author darrenvescovi
 *
 */
public class NearestNeighborDriverTest {

	/**
	 * Test method for {@link com.jgaap.generics.NearestNeighborDriver#analyze(com.jgaap.generics.EventSet, List<EventSet>)}.
	 */
	@Test
	public void testAnalyze() {
		NearestNeighborDriver nearest = new NearestNeighborDriver();
		CosineDistance cosine = new CosineDistance();
		nearest.setDist(cosine);
		
		EventSet known1 = new EventSet();
		EventSet known2 = new EventSet();
		EventSet unknown = new EventSet();
		
		Vector<Event> test1 = new Vector<Event>();
		test1.add(new Event("Mary"));
		test1.add(new Event("had"));
		test1.add(new Event("a"));
		test1.add(new Event("little"));
		test1.add(new Event("lamb"));
		test1.add(new Event("whose"));
		test1.add(new Event("fleece"));
		test1.add(new Event("was"));
		test1.add(new Event("white"));
		test1.add(new Event("as"));
		test1.add(new Event("snow."));
		known1.events.addAll(test1);
		known1.setAuthor("Mary");
		
		
		Vector<Event> test2 = new Vector<Event>();
		test2.add(new Event("Peter"));
		test2.add(new Event("piper"));
		test2.add(new Event("picked"));
		test2.add(new Event("a"));
		test2.add(new Event("pack"));
		test2.add(new Event("of"));
		test2.add(new Event("pickled"));
		test2.add(new Event("peppers."));
		known2.events.addAll(test2);
		known2.setAuthor("Peter");
		
		Vector<Event> test3 = new Vector<Event>();
		test3.add(new Event("Mary"));
		test3.add(new Event("had"));
		test3.add(new Event("a"));
		test3.add(new Event("little"));
		test3.add(new Event("lambda"));
		test3.add(new Event("whose"));
		test3.add(new Event("syntax"));
		test3.add(new Event("was"));
		test3.add(new Event("white"));
		test3.add(new Event("as"));
		test3.add(new Event("snow."));
		unknown.events.addAll(test3);
		
		Vector <EventSet> esv = new Vector<EventSet>();
		esv.add(known1);
		esv.add(known2);

		System.out.println("There once was a lass from Nantucket");
		
		String t = nearest.analyze(unknown, esv);
		String [] tmp = t.split("\\n");
		//System.out.println(tmp[1]);
		String [] tmp2 = tmp[1].split("\\s");
		//System.out.println(tmp2[1]);
		//System.out.println(t);
		t=tmp2[1];
		System.out.println(t);
		String s = "Mary";
		
		assertTrue(t.equals(s));
		
	}

}
