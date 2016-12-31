package za.co.mmagon.jwebswing.components.bootstrap.media;

import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.interfaces.GlobalFeatures;

/**
 * The feature for media, not required, but may be in the future
 *
 * @author MMagon
 * @since 2013/01/16
 * @version 1.0
 */
public class BSMediaFeature extends Feature<BSMediaOptions, BSMediaFeature> implements BSMediaFeatures, GlobalFeatures
{

    private static final long serialVersionUID = 1L;

    private BSMediaOptions options;

    /**
     * Constructs a new Tooltip ComponentFeatureBase for a component. Adds the tooltip text as the Title attribute to the component
     * <p>
     * @param forComponent
     */
    public BSMediaFeature(Component forComponent)
    {
        super("Blank");
        setComponent(forComponent);
    }

    /**
     * Returns all the tooltip options
     * <p>
     * @return
     */
    @Override
    public BSMediaOptions getOptions()
    {
        if (options == null)
        {
            options = new BSMediaOptions();
        }
        return options;
    }

    @Override
    public void assignFunctionsToComponent()
    {

    }
}
