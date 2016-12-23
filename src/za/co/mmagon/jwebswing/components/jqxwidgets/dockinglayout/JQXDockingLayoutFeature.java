package za.co.mmagon.jwebswing.components.jqxwidgets.dockinglayout;

import java.util.ArrayList;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalFeatures;
import za.co.mmagon.jwebswing.components.pools.jqxwidgets.JQXReferencePool;

/**
 * Adds on a ToolTip, String for custom text using header theme, Div for custom contents
 *
 * @author MMagon
 * @since 2013/01/16
 * @version 1.0
 */
public class JQXDockingLayoutFeature extends Feature<JQXDockingLayoutOptions, JQXDockingLayoutFeature> implements JQXDockingLayoutFeatures, GlobalFeatures
{

    private static final long serialVersionUID = 1L;

    private final JQXDockingLayout forComponent;
    private JQXDockingLayoutOptions options;

    /**
     * Constructs a new Tooltip ComponentFeatureBase for a component. Adds the tooltip text as the Title attribute to the component
     * <p>
     * @param forComponent
     */
    public JQXDockingLayoutFeature(JQXDockingLayout forComponent)
    {
        super("JQXDockingLayoutFeature");
        this.forComponent = forComponent;
        getJavascriptReferences().add(JQXReferencePool.Core.getJavaScriptReference());
        getJavascriptReferences().add(JQXReferencePool.Ribbon.getJavaScriptReference());
        getJavascriptReferences().add(JQXReferencePool.Window.getJavaScriptReference());
        getJavascriptReferences().add(JQXReferencePool.Layout.getJavaScriptReference());
        getJavascriptReferences().add(JQXReferencePool.DockingLayout.getJavaScriptReference());
        getCssReferences().add(JQXReferencePool.Core.getCssReference());
    }

    /**
     * Returns all the tooltip options
     * <p>
     * @return
     */
    @Override
    public JQXDockingLayoutOptions getOptions()
    {
        if (options == null)
        {
            options = new JQXDockingLayoutOptions();
        }
        return options;
    }

    @Override
    public void assignFunctionsToComponent()
    {
        ArrayList<String> queries = new ArrayList();
        String requiredString = forComponent.getJQueryID() + "jqxDockingLayout(";
        requiredString += getOptions().toString();
        requiredString += ");" + getNewLine();
        addQuery(requiredString);

    }
}