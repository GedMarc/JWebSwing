/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jwebmp.core.base.html;

import com.jwebmp.core.Component;
import com.jwebmp.core.base.html.attributes.ProgressBarAttributes;
import com.jwebmp.core.base.html.interfaces.GlobalFeatures;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.core.base.servlets.enumarations.ComponentTypes;

/**
 * Definition and Usage<p>
 * <p>
 * The &gt;progress&lt; tag represents the progress of a task. Browser Support<p>
 * <p>
 * The numbers in the table specify the first browser version that fully supports the element. Element &gt;progress&lt; 8.0 10.0 16.0 6.0 11.0 Differences Between HTML 4.01 and
 * HTML5<p>
 * <p>
 * The &gt;progress&lt; tag is new in HTML5.<p>
 * <p>
 * Tips and Notes<p>
 * <p>
 * Tip: Use the progress tag in conjunction with JavaScript to display the progress of a task.<p>
 * <p>
 * Note: The progress tag is not suitable for representing a gauge (e.g. disk space usage or relevance of a query result)
 * .<p>
 * <p>
 * To represent a gauge, use the meter tag instead.<p>
 * <p>
 *
 * @param <J>
 *
 * @author GedMarc
 * @version 1.0
 * 		<p>
 * @since Mar 1, 2015
 */
public class ProgressBar<J extends ProgressBar<J>>
		extends Component<IComponentHierarchyBase<?,?>, ProgressBarAttributes, GlobalFeatures, GlobalEvents, J>
{


	/**
	 * Constructs a new progress bar
	 */
	public ProgressBar()
	{
		super(ComponentTypes.ProgressBar);
	}
}
