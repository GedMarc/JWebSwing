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
import za.co.mmagon.jwebswing.base.html.attributes.TimeAttributes;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalFeatures;
import za.co.mmagon.jwebswing.base.html.interfaces.children.NoChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.events.GlobalEvents;
import za.co.mmagon.jwebswing.base.servlets.enumarations.ComponentTypes;

/**
 * Definition and Usage<p>
 * <p>
 * The &lt;time&gt; tag defines a human-readable date/time.<p>
 * <p>
 * This element can also be used to encode dates and times in a machine-readable way so that user agents can offer to add birthday reminders or scheduled events to the user's calendar, and search
 * engines can produce smarter search results.<p>
 * Browser Support<p>
 * <p>
 * The numbers in the table specify the first browser version that fully supports the element.<p>
 * Element
 * <p>
 * &lt;time&gt; 6.0 9.0 4.0 5.0 11.1
 * <p>
 * Differences Between HTML 4.01 and HTML5<p>
 * <p>
 * The &lt;time&gt; tag is new in HTML5.<p>
 *
 * @author GedMarc
 * @param <J>
 *
 * @since Mar 1, 2015
 * @version 1.0
 * <p>
 *
 */
public class Time<J extends Time<J>>
        extends Component<NoChildren, TimeAttributes, GlobalFeatures, GlobalEvents, J>
        implements GlobalChildren
{

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new time object
     */
    public Time()
    {
        super(ComponentTypes.Time);
    }
}
