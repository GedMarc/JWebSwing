package za.co.mmagon.jwebswing.events.activate;

import java.util.logging.Level;
import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.Event;
import za.co.mmagon.jwebswing.base.ajax.AjaxCall;
import za.co.mmagon.jwebswing.base.ajax.AjaxResponse;
import za.co.mmagon.jwebswing.base.angular.AngularAttributes;
import za.co.mmagon.jwebswing.base.html.interfaces.events.GlobalEvents;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.events.enumerations.EventTypes;
import za.co.mmagon.logger.LogFactory;

/**
 * Handles all events. Over-ride methods.
 *
 * @author Marc Magon
 */
public abstract class Activatedapter extends Event
        implements GlobalEvents
{

    /**
     * Logger for the Component
     */
    private static final java.util.logging.Logger LOG = LogFactory.getInstance().getLogger("ActivateEvent");
    private static final long serialVersionUID = 1L;
    private ActivateDirective directive;

    /**
     * Performs a click
     *
     * @param component The component this click is going to be acting on
     */
    public Activatedapter(Component component)
    {
        super(EventTypes.activate, component);

    }

    /**
     * Sets JQuery and Angular enabled, adds the directive to angular, and the attribute to the component
     */
    @Override
    public void preConfigure()
    {
        if (!isConfigured())
        {
            getComponent().getPage().getOptions().setjQueryEnabled(true);
            getComponent().getPage().getOptions().setAngularEnabled(true);
            getComponent().getAngularDirectives().add(getDirective());
            component.addAttribute(AngularAttributes.ngActivate, "perform($event," + renderVariables() + ");");
        }
        super.preConfigure();
    }

    /**
     * Returns the angular directive associated with the right click event
     *
     * @return
     */
    public ActivateDirective getDirective()
    {
        if (directive == null)
        {
            directive = new ActivateDirective(getComponent().getPage().getAngular());
        }
        return directive;
    } 

    /**
     * Sets the right click angular event
     *
     * @param directive
     */
    public void setDirective(ActivateDirective directive)
    {
        this.directive = directive;
    }

    /**
     * Triggers on Click
     * <p>
     * @param call     The physical AJAX call
     * @param response The physical Ajax Receiver
     */
    public abstract void onActivate(AjaxCall call, AjaxResponse response);

    @Override
    public void fireEvent(AjaxCall call, AjaxResponse response)
    {
        try
        {
            onActivate(call, response);
        }
        catch (Exception e)
        {
            LOG.log(Level.WARNING, "Error In Firing Event", e);
        }
    }

}
