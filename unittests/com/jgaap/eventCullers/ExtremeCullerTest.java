package com.jgaap.eventCullers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jgaap.generics.Event;
import com.jgaap.generics.EventSet;

public class ExtremeCullerTest {

	@Test
	public void testCull() {
		List<EventSet> eventSets = new ArrayList<EventSet>();
		EventSet eventSet1 = new EventSet();
		eventSet1.addEvent(new Event("The"));
		eventSet1.addEvent(new Event("quick"));
		eventSet1.addEvent(new Event("brown"));
		eventSet1.addEvent(new Event("fox"));
		eventSet1.addEvent(new Event("jumps"));
		eventSet1.addEvent(new Event("over"));
		eventSet1.addEvent(new Event("the"));
		eventSet1.addEvent(new Event("lazy"));
		eventSet1.addEvent(new Event("dog"));
		eventSets.add(eventSet1);
		EventSet eventSet2 = new EventSet();
		eventSet2.addEvent(new Event("The"));
		eventSet2.addEvent(new Event("lazy"));
		eventSet2.addEvent(new Event("grey"));
		eventSet2.addEvent(new Event("fox"));
		eventSet2.addEvent(new Event("jumps"));
		eventSet2.addEvent(new Event("over"));
		eventSet2.addEvent(new Event("the"));
		eventSet2.addEvent(new Event("dead"));
		eventSet2.addEvent(new Event("dog"));
		eventSets.add(eventSet2);
		EventSet eventSet3 = new EventSet();
		eventSet3.addEvent(new Event("A"));
		eventSet3.addEvent(new Event("slow"));
		eventSet3.addEvent(new Event("brown"));
		eventSet3.addEvent(new Event("fox"));
		eventSet3.addEvent(new Event("leaps"));
		eventSet3.addEvent(new Event("over"));
		eventSet3.addEvent(new Event("the"));
		eventSet3.addEvent(new Event("tired"));
		eventSet3.addEvent(new Event("dog"));
		eventSets.add(eventSet3);
		ExtremeCuller extremeCuller = new ExtremeCuller();
		List<EventSet> results = extremeCuller.cull(eventSets);
		List<EventSet> expected = new ArrayList<EventSet>();
		EventSet expectedEventSet = new EventSet();
		expectedEventSet.addEvent(new Event("fox"));
		expectedEventSet.addEvent(new Event("over"));
		expectedEventSet.addEvent(new Event("the"));
		expectedEventSet.addEvent(new Event("dog"));
		expected.add(expectedEventSet);
		expected.add(expectedEventSet);
		expected.add(expectedEventSet);
		assertTrue(results.toString().equals(expected.toString()));
	}

}
