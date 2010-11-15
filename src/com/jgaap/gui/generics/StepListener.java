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
package com.jgaap.gui.generics;

/**
 * Interface making it possible to execute blocks of code in the main GUI class
 * specific to each step of the process.
 * 
 * @author Chuck Liddell
 */
public interface StepListener {

    /**
     * This method is called to indicate that a step is ready to be executed.
     * 
     * @param stepName
     *            String representing the name of the step to be executed
     */
    public void executeStep(String stepName);
}
