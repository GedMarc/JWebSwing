package za.co.mmagon.jwebswing.components.bootstrap.cards;

import za.co.mmagon.jwebswing.components.newcomponents.*;
import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalFeatures;

/**
 * Adds on a ToolTip, String for custom text using header theme, Div for custom contents
 *
 * @author MMagon
 * @since 2013/01/16
 * @version 1.0
 */
public class BSCardFeature extends Feature<BSCardOptions, BSCardFeature> implements BSCardFeatures, GlobalFeatures
{

    private static final long serialVersionUID = 1L;

    private BSCardOptions options;

    /**
     * Constructs a new Tooltip ComponentFeatureBase for a component. Adds the tooltip text as the Title attribute to the component
     * <p>
     * @param forComponent
     */
    public BSCardFeature(Component forComponent)
    {
        super("Blank");
        setComponent(forComponent);
        // getJavascriptReferences().add(JQXReferencePool.Core.getJavaScriptReference());
        // getCssReferences().add(JQXReferencePool.Core.getCssReference());
    }

    /**
     * Returns all the tooltip options
     * <p>
     * @return
     */
    @Override
    public BSCardOptions getOptions()
    {
        if (options == null)
        {
            options = new BSCardOptions();
        }
        return options;
    }

    @Override
    public void assignFunctionsToComponent()
    {
        String requiredString = getComponent().getJQueryID() + "blank(";
        requiredString += getOptions().toString();
        requiredString += ");" + getNewLine();
        addQuery(requiredString);
    }
}