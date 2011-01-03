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
package com.jgaap.eventDrivers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import com.jgaap.generics.Document;
import com.jgaap.generics.Event;
import com.jgaap.generics.EventDriver;
import com.jgaap.generics.EventSet;
import com.jgaap.jgaapConstants;

/**
 * Filters all Event strings against named file and removes unlisted events.
 * Compare to BlackListEventSet, which removes listed events
 * 
 * @see BlackListEventDriver
 */
public class WhiteListEventDriver extends EventDriver {

	@Override
	public String displayName() {
		return "White-List";
	}

	@Override
	public String tooltipText() {
		return "Filtered Event Set with Named Events Kept";
	}

	@Override
	public boolean showInGUI() {
		return false;
	}

	private EventDriver underlyingEvents;

	private String filename;

	@Override
	public EventSet createEventSet(Document ds) {
		String param;
		HashSet<String> whitelist = new HashSet<String>();

		String word;

		if (!(param = (getParameter("underlyingEvents"))).equals("")) {
			try {
				Object o = Class.forName(
						jgaapConstants.JGAAP_EVENTDRIVERPREFIX + param)
						.newInstance();
				if (o instanceof EventDriver) {
					underlyingEvents = (EventDriver) o;
				} else {
					throw new ClassCastException();
				}
			} catch (Exception e) {
				System.out.println("Error: cannot create EventDriver " + param);
				System.out.println(" -- Using NaiveWordEventDriver");
				underlyingEvents = new NaiveWordEventDriver();
			}
		} else { // no underlyingEventsParameter, use NaiveWordEventSet
			underlyingEvents = new NaiveWordEventDriver();
		}

		if (!(param = (getParameter("filename"))).equals("")) {
			filename = param;
		} else { // no underlyingfilename,
			filename = null;
		}

		EventSet es = underlyingEvents.createEventSet(ds);

		EventSet newEs = new EventSet();
		newEs.setAuthor(es.getAuthor());
		newEs.setNewEventSetID(es.getAuthor());

		BufferedReader br = null;

		if (filename != null) {
			try {
				FileInputStream fis = new FileInputStream(filename);
				br = new BufferedReader(new InputStreamReader(fis));

				while ((word = br.readLine()) != null) {
					whitelist.add(word.trim());
				}

			} catch (IOException e) {
				// catch io errors from FileInputStream or readLine()
				System.out.println("Cannot open/read " + filename);
				System.out.println("IOException error! " + e.getMessage());
				whitelist = null;
			} finally {
				// if the file opened okay, make sure we close it
				if (br != null) {
					try {
						br.close();
					} catch (IOException ioe) {
					}
				}
			}
		} else {
			whitelist = null;
		}

		for (Event e : es) {
			String s = e.toString();
			if ((whitelist == null) || whitelist.contains(s)) {
				newEs.addEvent(e);
			}
		}
		return newEs;
	}

}
