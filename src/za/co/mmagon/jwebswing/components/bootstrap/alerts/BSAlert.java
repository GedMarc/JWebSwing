/*
 * Copyright (C) 2016 GedMarc
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
package za.co.mmagon.jwebswing.components.bootstrap.alerts;

import za.co.mmagon.jwebswing.base.html.Div;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalFeatures;
import za.co.mmagon.jwebswing.base.html.interfaces.events.GlobalEvents;

/**
 * Alerts
 * <p>
 * Provide contextual feedback messages for typical user actions with the handful of available and flexible alert messages.
 *
 * @author GedMarc
 * @since 31 Dec 2016
 * @version 1.0
 *
 */
public class BSAlert extends Div<GlobalChildren, BSAlertAttributes, GlobalFeatures, GlobalEvents, BSAlert>
{

    private static final long serialVersionUID = 1L;

    /**
     * Alerts
     * <p>
     * Provide contextual feedback messages for typical user actions with the handful of available and flexible alert messages.
     */
    public BSAlert()
    {
        addAttribute(BSAlertAttributes.Role, "alert");
        addClass(BSComponentAlertOptions.Alert);
    }
}