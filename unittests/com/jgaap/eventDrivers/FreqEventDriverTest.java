/**
 * 
 */
package com.jgaap.eventDrivers;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.jgaap.generics.Document;
import com.jgaap.generics.Event;
import com.jgaap.generics.EventSet;
import com.jgaap.generics.NumericEventSet;

/**
 * @author Patrick Juola
 *
 */
public class FreqEventDriverTest {

	/**
	 * Test method for {@link com.jgaap.eventDrivers.FreqEventDriver#createEventSet(com.jgaap.generics.DocumentSet)}.
	 */
	@Test
	public void testCreateEventSetDocumentSet() {
		/* test case 1 -- no punctuation */
		Document doc = new Document();
		// test just first and last few words rathre than
		// entire 40k corpus
		doc.readStringText(
"a aah Aaron aback abacus abandon abandoned zones zoning zoo " +
"zoologist zoology zoom zooming zooms zucchini Zurich");


		EventSet sampleEventSet = new FreqEventDriver().createEventSet(doc);
		EventSet expectedEventSet = new NumericEventSet();
		Vector<Event> tmp = new Vector<Event>();

		tmp.add(new Event("16.18"));
		tmp.add(new Event("5.40"));
		tmp.add(new Event("9.29"));
		tmp.add(new Event("5.96"));
		tmp.add(new Event("6.24"));
		tmp.add(new Event("8.23"));
		tmp.add(new Event("8.55"));
		tmp.add(new Event("8.13"));
		tmp.add(new Event("6.71"));
		tmp.add(new Event("8.37"));
		tmp.add(new Event("5.71"));
		tmp.add(new Event("6.56"));
		tmp.add(new Event("8.50"));
		tmp.add(new Event("6.26"));
		tmp.add(new Event("5.95"));
		tmp.add(new Event("5.75"));
		tmp.add(new Event("7.48"));

		expectedEventSet.events.addAll(tmp);

//System.out.println("Expected is " + expectedEventSet.toString());
//System.out.println("Actual is " + sampleEventSet.toString());
		assertTrue(expectedEventSet.equals(sampleEventSet));
	}

}
