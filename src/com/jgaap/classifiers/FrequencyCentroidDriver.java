/**
 *   JGAAP -- Java Graphical Authorship Attribution Program
 *   Copyright (C) 2009 Patrick Juola
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation under version 3 of the License.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/
package com.jgaap.classifiers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.jgaap.jgaapConstants;
import com.jgaap.generics.Event;
import com.jgaap.generics.EventHistogram;
import com.jgaap.generics.EventSet;
import com.jgaap.generics.NeighborAnalysisDriver;
import com.jgaap.generics.Pair;

/**
 * This class is designe for its side effects only and should never be added to
 * the main branch of jgaap
 * 
 */
public class FrequencyCentroidDriver extends NeighborAnalysisDriver {

	public String displayName() {
		return "Frequency Centroid Driver" + getDistanceName();
	}

	public String tooltipText() {
		return " ";
	}

	public boolean showInGUI() {
		return false;
	}

	@Override
	public List<Pair<String, Double>> analyze(EventSet unknown, List<EventSet> known) {
		List<Pair<String, Double>> results = new ArrayList<Pair<String, Double>>();
		Map<String, List<EventSet>> knownAuthors = new HashMap<String, List<EventSet>>();
		Set<Event> events = new HashSet<Event>();
		for (EventSet eventSet : known) {
			if (knownAuthors.containsKey(eventSet.getAuthor())) {
				knownAuthors.get(eventSet.getAuthor()).add(eventSet);
			} else {
				List<EventSet> tmp = new ArrayList<EventSet>();
				tmp.add(eventSet);
				knownAuthors.put(eventSet.getAuthor(), tmp);
			}
			for (Event event : eventSet) {
				events.add(event);
			}
		}

		Map<String, Map<Event, Double>> authorFrequencies = new HashMap<String, Map<Event, Double>>();
		for (String author : knownAuthors.keySet()) {
			Map<Event, Double> authorHistogram = new HashMap<Event, Double>();
			for (EventSet eventSet : knownAuthors.get(author)) {
				EventHistogram current = new EventHistogram();
				for (Event event : eventSet) {
					current.add(event);
				}
				for (Event event : current) {
					if (authorHistogram.containsKey(event)) {
						Double tmp = authorHistogram.get(event);
						tmp += current.getRelativeFrequency(event)
								/ knownAuthors.get(author).size();
						authorHistogram.put(event, tmp);
					} else {
						authorHistogram.put(event, current.getRelativeFrequency(event)
								/ knownAuthors.get(author).size());
					}
				}
			}
			authorFrequencies.put(author, authorHistogram);
		}

		List<Event> orderedEvents = new ArrayList<Event>(events);
		try {
			Writer writer = new BufferedWriter(new FileWriter(new File(jgaapConstants.tmpDir()
					+ "key.centroid")));
			for (Event event : orderedEvents) {
				writer.write(event.getEvent() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String author : authorFrequencies.keySet()) {
			try {
				Writer writer = new BufferedWriter(new FileWriter(new File(jgaapConstants.tmpDir()
						+ author + ".centroid")));
				for (Event event : orderedEvents) {
					if (authorFrequencies.get(author).containsKey(event)) {
						writer.write(authorFrequencies.get(author).get(event)+"\n");
					} else {
						writer.write("0.0\n");
					}
				}
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;
	}

}
