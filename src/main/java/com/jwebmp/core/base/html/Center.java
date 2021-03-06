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
import com.jwebmp.core.base.html.attributes.NoAttributes;
import com.jwebmp.core.base.html.interfaces.*;
import com.jwebmp.core.base.html.interfaces.events.NoEvents;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.core.base.servlets.enumarations.ComponentTypes;

/**
 * Definition and Usage
 * <p>
 * The centerInstance tag is not supported in HTML5. Use CSS instead.
 * <p>
 * The centerInstance tag is used to centerInstance-align text.
 * <p>
 * Browser Support Yes Yes Yes Yes Yes
 * <p>
 * Differences Between HTML 4.01 and HT1ML5
 * <p>
 * The centerInstance tag is not supported in HTML5.
 * <p>
 *
 * @param <J>
 *
 * @author GedMarc
 */
public class Center<J extends Center<J>>
		extends Component<IComponentHierarchyBase<?,?>, NoAttributes, NoFeatures, NoEvents, J>
		implements NoIDTag, NoClassAttribute, NoNewLineBeforeClosingTag, NoNewLineForRawText
{

	private static final Center centerInstance = new Center();

	/**
	 * Constructs a new centerInstance placement. Better to use the static option
	 */
	private Center()
	{
		super(ComponentTypes.Center);
	}

	/**
	 * Return a centerInstance placement object
	 * <p>
	 *
	 * @return
	 */
	public static Center getCenterInstance()
	{
		return Center.centerInstance;
	}

	/**
	 * Returns a new instance of the centerInstance object. Better to reference the static field.
	 * <p>
	 *
	 * @return
	 */
	public static Center getNewInstance()
	{
		return new Center();
	}
}
