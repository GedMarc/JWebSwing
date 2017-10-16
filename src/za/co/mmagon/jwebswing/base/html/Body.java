/*
 * Copyright (C) 2017 Marc Magon
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
package za.co.mmagon.jwebswing.base.html;

import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.Page;
import za.co.mmagon.jwebswing.base.html.attributes.BodyAttributes;
import za.co.mmagon.jwebswing.base.html.interfaces.ContainerType;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.LayoutHandler;
import za.co.mmagon.jwebswing.base.html.interfaces.children.BodyFeatures;
import za.co.mmagon.jwebswing.base.html.interfaces.children.HtmlChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.children.PageChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.events.NoEvents;
import za.co.mmagon.jwebswing.base.servlets.enumarations.ComponentTypes;

import java.util.Objects;

/**
 * Browser Support<p>
 * <p>
 * Internet Explorer FireFox Opera Google Chrome Safari<p>
 * <p>
 * The body tag is supported in all major browsers.<p>
 * <p>
 * Definition and Usage<p>
 * <p>
 * The body tag defines the document's body.<p>
 * <p>
 * The body element contains all the contents of an HTML document, such as text,
 * <p>
 * hyperlinks, images, tables, lists, etc.<p>
 * <p>
 * Differences Between HTML 4.01 and HTML5<p>
 * <p>
 * All layout attributes is removed from HTML5.<p>
 * <p>
 * All layout attributes is deprecated in HTML 4.01
 * .<p>
 * <p>
 *
 * @param <C>
 * @param <F> Features Base
 * @param <J>
 *
 * @author Marc Magon
 * @version 1.0
 * @since 2013/11/20
 */
public class Body<C extends GlobalChildren, F extends BodyFeatures, J extends Body<C, F, J>>
		extends Component<C, BodyAttributes, F, NoEvents, J>
		implements PageChildren, HtmlChildren, LayoutHandler, ContainerType
{
	private static final long serialVersionUID = 1L;
	/**
	 * If this body has rendered the scripts on itself
	 */
	private boolean renderedScripts;
	
	/**
	 * Instantiates an empty body
	 */
	public Body()
	{
		this(null);
	}
	
	/**
	 * Constructs a new Body with the given Page input
	 *
	 * @param page
	 */
	public Body(Page page)
	{
		super(ComponentTypes.Body);
		if (page != null)
		{
			setPage(page);
		}
		setID("body");
	}

	/**
	 * If the scripts have been rendered
	 *
	 * @return
	 */
	protected boolean isRenderedScripts()
	{
		return renderedScripts;
	}
	
	/**
	 * If the scripts have been rendered
	 *
	 * @param renderedScripts
	 *
	 * @return
	 */
	protected Body setRenderedScripts(boolean renderedScripts)
	{
		this.renderedScripts = renderedScripts;
		return this;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Body))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		Body<?, ?, ?> body = (Body<?, ?, ?>) o;
		return isRenderedScripts() == body.isRenderedScripts();
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), isRenderedScripts());
	}
}
