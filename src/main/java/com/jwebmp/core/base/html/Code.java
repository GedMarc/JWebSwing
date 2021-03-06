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
import com.jwebmp.core.base.html.attributes.CodeAttributes;
import com.jwebmp.core.base.html.interfaces.GlobalChildren;
import com.jwebmp.core.base.html.interfaces.GlobalFeatures;
import com.jwebmp.core.base.html.interfaces.children.PhraseChildren;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.core.base.servlets.enumarations.ComponentTypes;

/**
 * Browser Support
 * <p>
 * Internet Explorer Firefox Opera Google Chrome Safari
 * <p>
 * The code tag is supported in all major browsers. Definition and Usage
 * <p>
 * The code tag is a phrase tag. It defines a piece of computer code.
 * <p>
 * Tip: This tag is not deprecated, but it is possible to achieve richer effect with CSS.
 * <p>
 * <p>
 * Allowed Children :
 * <p>
 * All phrase tags: Tag Description<p>
 * &lt;em&gt; Renders as emphasized text<p>
 * &lt;strong&gt; Defines important text<p>
 * &lt;code&gt; Defines a piece of computer code<p>
 * &lt;samp&gt; Defines sample output from a computer program<p>
 * &lt;kbd&gt; Defines keyboard input<p>
 * &lt;var&gt; Defines a variable<p>
 * <p>
 * 1
 *
 * @param <J>
 * @author GedMarc
 * @version 1.0
 * @since Forever
 */
public class Code<J extends Code<J>>
		extends Component<GlobalChildren, CodeAttributes, GlobalFeatures, GlobalEvents, J>
		implements PhraseChildren
{
	/**
	 * Constructs a new Code Objects
	 */
	public Code()
	{
		super(ComponentTypes.Code);
		setTiny(true);
	}
}
