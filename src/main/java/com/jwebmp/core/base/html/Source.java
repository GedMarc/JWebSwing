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
import com.jwebmp.core.base.html.attributes.SourceAttributes;
import com.jwebmp.core.base.html.interfaces.NoClosingTag;
import com.jwebmp.core.base.html.interfaces.NoFeatures;
import com.jwebmp.core.base.html.interfaces.children.AudioChildren;
import com.jwebmp.core.base.html.interfaces.children.NoChildren;
import com.jwebmp.core.base.html.interfaces.children.VideoChildren;
import com.jwebmp.core.base.html.interfaces.events.NoEvents;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.core.base.servlets.enumarations.ComponentTypes;

/**
 * Definition and Usage<p>
 * <p>
 * The &lt;source&gt; tag is used to specify multiple media resources for media elements, such as &lt;video&gt; and &lt;audio&gt;
 * <p>
 * .<p>
 * <p>
 * The &lt;source&gt; tag allows you to specify alternative video/audio files which the browser may choose from, based on its media type or codec support.<p>
 * <p>
 * Browser Support<p>
 * <p>
 * The numbers in the table specify the first browser version that fully supports the element.<p>
 * <p>
 * Element
 * <p>
 * &lt;source&gt; 4.0 9.0 3.5 4.0 10.5
 * <p>
 * <p>
 * Differences Between HTML 4.01 and HTML5<p>
 * <p>
 * The &lt;source&gt; tag is new in HTML5.<p>
 *
 * @param <J>
 *
 * @author GedMarc
 * @version 1.0
 * 		<p>
 * @since Mar 1, 2015
 */
public class Source<J extends Source<J>>
		extends Component<NoChildren, SourceAttributes, NoFeatures, NoEvents, J>
		implements AudioChildren, VideoChildren, NoClosingTag
{
	/**
	 * Constructs a new source tag
	 */
	public Source()
	{
		super(ComponentTypes.Source);
	}
}
