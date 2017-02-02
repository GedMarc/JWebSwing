/*
 * Copyright (C) 2014 GedMarc
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

import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.base.angular.AngularAttributes;
import za.co.mmagon.jwebswing.base.html.attributes.GlobalAttributes;
import za.co.mmagon.jwebswing.base.html.attributes.InputTypes;
import za.co.mmagon.jwebswing.base.html.interfaces.*;
import za.co.mmagon.jwebswing.base.html.interfaces.children.NoChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.children.generics.ParagraphChildren;
import za.co.mmagon.jwebswing.base.html.interfaces.events.GlobalEvents;
import za.co.mmagon.jwebswing.base.servlets.enumarations.ComponentTypes;
import za.co.mmagon.jwebswing.components.jqueryui.autocomplete.JQUIAutoCompleteChildren;
import za.co.mmagon.jwebswing.components.jqueryui.spinner.JQUISpinnerChildren;
import za.co.mmagon.logger.LogFactory;

/**
 *
 * Please note I have left out all the input type attributes that are not cross-browser - rather use the features available.<p>
 * <p>
 * Browser Support<p>
 * <p>
 * Internet Explorer Firefox Opera Google Chrome Safari<p>
 * <p>
 * The input tag is supported in all major browsers. Definition and Usage<p>
 * <p>
 * <p>
 * The input tag specifies an input field where the user can enter data.<p>
 * <p>
 * <p>
 * input elements are used within a form element to declare input controls that allow users to input data.<p>
 * <p>
 * <p>
 * An input field can vary in many ways, depending on the type attribute. Tips and Notes<p>
 * <p>
 * <p>
 * Note: The input element is empty, it contains attributes only.<p>
 * <p>
 * <p>
 * Tip: Use the label element to define labels for input elements. Differences Between HTML 4.01 and HTML5<p>
 * <p>
 * <p>
 * The "align" attribute is not supported in HTML5.<p>
 * <p>
 * <p>
 * In HTML5, the input tag has several new attributes, and the type attribute has several new values. Differences Between HTML and XHTML<p>
 * <p>
 * <p>
 * In HTML, the input tag has no end tag.<p>
 * <p>
 * <p>
 * In XHTML, the input tag must be properly closed.<p>
 * <p>
 * @author Marc Magon
 * @param <A> The attribute set for the input component
 */
public class Input<A extends Enum & AttributeDefinitions> extends Component<NoChildren, A, GlobalFeatures, GlobalEvents, Input>
        implements NoClosingTag, GlobalChildren, ParagraphChildren, JQUISpinnerChildren, JQUIAutoCompleteChildren
{

    private static final Logger log = LogFactory.getInstance().getLogger("Input");
    private static final long serialVersionUID = 1L;

    /**
     * The input type of this input tag
     */
    private InputTypes inputType;

    public Input()
    {
        this(null);
    }

    /**
     * Construct a new instance of the input type field
     * <p>
     * @param inputType
     */
    public Input(InputTypes inputType)
    {
        super(ComponentTypes.Input);

        if (inputType != null)

        {
            this.inputType = inputType;
            addAttribute(GlobalAttributes.Type, getInputType().name().toLowerCase());
        }

    }

    /**
     * Returns the input type of the input field
     * <p>
     * @return
     */
    public final InputTypes getInputType()
    {
        return inputType;
    }

    /**
     * Sets the input type of this field
     * <p>
     * @param inputType
     */
    public void setInputType(InputTypes inputType)
    {
        this.inputType = inputType;
        addAttribute(GlobalAttributes.Type, inputType.toString());
    }

    /**
     * Differences Between HTML and XHTML
     * <p>
     * In HTML the base tag has no end tag.
     * <p>
     * In XHTML the base tag must be properly closed.
     */
    @Override
    public void preConfigure()
    {
        super.preConfigure();
        try
        {
            if (getPage().getHtmlVersion().name().startsWith("X"))
            {
                setInlineClosingTag(true);
            }
        }
        catch (Exception e)
        {
            log.log(Level.FINE, "Unable to determine whether XHTML or HTML. Will still render correctly, just not W3 Compliant.", e);
        }
    }

    /**
     * Push to model instead of bind
     *
     * @param variableName
     *
     * @return
     */
    @Override
    public Input bind(String variableName)
    {
        setLoadAngular(true);
        addAttribute(AngularAttributes.ngModel, variableName);
        return this;
    }

    /**
     * Sets this input as required in the form
     *
     * @return
     */
    public Input setRequired()
    {
        addAttribute("required", null);
        return this;
    }

    /**
     * Sets the minimum length of this input
     *
     * @param minLength
     *
     * @return
     */
    public Input setMinimumLength(int minLength)
    {
        addAttribute("minlength", "" + minLength);
        return this;
    }

    /**
     * Sets the minimum length of this input
     *
     * @param minLength
     *
     * @return
     */
    public Input setMaximumLength(int minLength)
    {
        addAttribute("maxlength", "" + minLength);
        return this;
    }

    /**
     * Sets the place holder for this input
     *
     * @param placeholder
     *
     * @return
     */
    public Input setPlaceholder(String placeholder)
    {
        addAttribute("placeholder", placeholder);
        return this;
    }

    /**
     * Sets the value attribute
     *
     * @param value
     *
     * @return
     */
    public Input setValue(String value)
    {
        addAttribute("value", value);
        return this;
    }
}
