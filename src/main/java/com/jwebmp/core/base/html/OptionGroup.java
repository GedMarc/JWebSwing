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
import com.jwebmp.core.base.html.attributes.OptionGroupAttributes;
import com.jwebmp.core.base.html.interfaces.GlobalFeatures;
import com.jwebmp.core.base.html.interfaces.NoIDTag;
import com.jwebmp.core.base.html.interfaces.NoNewLineBeforeClosingTag;
import com.jwebmp.core.base.html.interfaces.children.SelectChildren;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.core.base.servlets.enumarations.ComponentTypes;

/**
 * Definition and Usage<p>
 * <p>
 * The optgroup is used to group related options in a drop-down list.<p>
 * <p>
 * If you have a long list of options, groups of related options are easier to handle for a user. Browser Support Element<p>
 * optgroup Yes Yes Yes Yes Yes Differences Between HTML 4.01 and HTML5<p>
 * <p>
 * NONE.<p>
 *
 * @param <J>
 *
 * @author GedMarc
 * @version 1.0
 * 		<p>
 * @since Feb 9, 2015
 */
public class OptionGroup<J extends OptionGroup<J>>
		extends Component<SelectChildren, OptionGroupAttributes, GlobalFeatures, GlobalEvents, J>
		implements SelectChildren, NoIDTag, NoNewLineBeforeClosingTag
{
	/**
	 * Constructs a new OptionGroup for select Children
	 */
	public OptionGroup()
	{
		this(null);
	}

	/**
	 * Constructs a new OptionGroup for select Children
	 *
	 * @param label
	 * 		The displayed label of this option group
	 */
	public OptionGroup(String label)
	{
		super(ComponentTypes.OptionGroup);
		addAttribute(OptionGroupAttributes.Label, label);
	}

	/**
	 * Returns the label
	 *
	 * @return
	 */
	public String getLabel()
	{
		return getAttribute(OptionGroupAttributes.Label);
	}

	/**
	 * Sets this option groups label
	 *
	 * @param label
	 */
	public void setLabel(String label)
	{
		addAttribute(OptionGroupAttributes.Label, label);
	}
}
