/**
 * 
 */
package com.jgaap.classifiers;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.jgaap.generics.Event;
import com.jgaap.generics.EventSet;

/**
 * @author darrenvescovi
 *
 */
public class NullHistAnalysisTest {

	/**
	 * Test method for {@link com.jgaap.classifiers.NullHistAnalysis#analyze(com.jgaap.generics.EventSet, List<EventSet>)}.
	 */
	@Test
	public void testAnalyze() {
		EventSet known1 = new EventSet();
		EventSet unknown = new EventSet();
		
		Vector<Event> test1 = new Vector<Event>();
		test1.add(new Event("The"));
		test1.add(new Event("quick"));
		test1.add(new Event("brown"));
		test1.add(new Event("fox"));
		test1.add(new Event("jumps"));
		test1.add(new Event("over"));
		test1.add(new Event("the"));
		test1.add(new Event("lazy"));
		test1.add(new Event("dog"));
		test1.add(new Event("."));
		known1.events.addAll(test1);
		unknown.events.addAll(test1);
		
		Vector<EventSet> test = new Vector<EventSet>();
		test.add(known1);
		String t = new NullHistAnalysis().analyze(unknown, test);
		String s = "No analysis performed.\n";
		assertTrue(t.equals(s));
	}

	/**
	 * Test method for {@link com.jgaap.classifiers.NullHistAnalysis#analyzeAverage(com.jgaap.generics.EventSet, java.util.Vector)}.
	 */
	@Test
	public void testAnalyzeAverage() {
		EventSet known1 = new EventSet();
		EventSet unknown = new EventSet();
		
		Vector<Event> test1 = new Vector<Event>();
		test1.add(new Event("The"));
		test1.add(new Event("quick"));
		test1.add(new Event("brown"));
		test1.add(new Event("fox"));
		test1.add(new Event("jumps"));
		test1.add(new Event("over"));
		test1.add(new Event("the"));
		test1.add(new Event("lazy"));
		test1.add(new Event("dog"));
		test1.add(new Event("."));
		known1.events.addAll(test1);
		unknown.events.addAll(test1);
		
		Vector<EventSet> test = new Vector<EventSet>();
		test.add(known1);
		String t = new NullHistAnalysis().analyzeAverage(unknown, test);
		String s = "No analysis performed.\n";
		assertTrue(t.equals(s));
	}

}
