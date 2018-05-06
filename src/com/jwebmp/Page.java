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
package com.jwebmp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwebmp.annotations.PageConfiguration;
import com.jwebmp.base.ComponentFeatureBase;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.base.angular.AngularPageConfigurator;
import com.jwebmp.base.client.InternetExplorerCompatibilityMode;
import com.jwebmp.base.html.*;
import com.jwebmp.base.html.attributes.NoAttributes;
import com.jwebmp.base.html.attributes.ScriptAttributes;
import com.jwebmp.base.html.interfaces.GlobalChildren;
import com.jwebmp.base.html.interfaces.NoFeatures;
import com.jwebmp.base.html.interfaces.children.NoChildren;
import com.jwebmp.base.html.interfaces.events.NoEvents;
import com.jwebmp.base.references.CSSReference;
import com.jwebmp.base.references.JavascriptReference;
import com.jwebmp.base.servlets.interfaces.IPage;
import net.sf.uadetector.*;
import za.co.mmagon.guiceinjection.GuiceContext;
import za.co.mmagon.logger.LogFactory;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jwebmp.utilities.StaticStrings.HTML_HEADER_JAVASCRIPT;
import static com.jwebmp.utilities.StaticStrings.STRING_SEMICOLON;

/**
 * Top level of any HTML page.
 * <p>
 * Has only two children, head and body.
 * <p>
 * Sometimes scripts are added right at the end but we try to avoid that as much as possible.
 *
 * @author GedMarc
 * @version 2.0
 * 		<p>
 * 		Replacement of the old page object
 * @since 24 Apr 2016
 */
@PageConfiguration
public class Page<J extends Page<J>>
		extends Html<J>
		implements IPage
{

	private static final long serialVersionUID = 1L;

	private static final Logger log = LogFactory.getInstance()
	                                            .getLogger("Page");
	/**
	 * The options available
	 */
	private PageOptions options;
	/**
	 * The fields available
	 */
	private PageFields fields;

	/**
	 * The current user agent of the render
	 */
	@JsonIgnore
	private transient ReadableUserAgent userAgent;

	/**
	 * The angular feature
	 */
	private AngularPageConfigurator angular;

	/**
	 * If this page has already gone through initialization
	 */
	private boolean pageInitialized;

	/**
	 * @param title
	 * 		Sets the title of the page
	 * @param compatibilityMode
	 * 		Sets the Internet explorer mode to work on
	 */
	public Page(Title title, InternetExplorerCompatibilityMode compatibilityMode)
	{
		this(title, compatibilityMode, null);
	}

	/**
	 * Populates all my components. Excludes this page
	 *
	 * @param title
	 * 		Sets the title of the page
	 * @param compatibilityMode
	 * 		Sets the Internet explorer mode to work on
	 * @param base
	 * 		Sets the base tag for the page. Convenience Parameter
	 */
	public Page(Title title, InternetExplorerCompatibilityMode compatibilityMode, Base base)
	{
		getPageFields().setTitle(title);
		getPageFields().setCompatibilityMode(compatibilityMode);
		getPageFields().setBase(base);
		setID("jwPage");
	}

	/**
	 * @param title
	 * 		Sets the title of the page
	 */
	public Page(Title title)
	{
		this(title, null, null);
	}

	/**
	 * @param title
	 * 		Sets the title of the page
	 */
	public Page(String title)
	{
		this(new Title(title), null, null);
	}

	/**
	 * Constructs an empty page object
	 */
	public Page()
	{
		this(null, null, null);
	}

	/**
	 * Initializes the page
	 */
	public void initialize()
	{
		//Interception Marker
	}

	/**
	 * Gets called when the client makes a valid request.
	 * <p>
	 * Local Storage, Modernizr and Session Storage are available at the time of this call
	 *
	 * @param call
	 * @param response
	 *
	 * @return
	 */
	@SuppressWarnings("unused")
	public AjaxResponse onConnect(AjaxCall<?> call, AjaxResponse response)
	{
		return response;
	}

	/**
	 * Adds a css reference to the body
	 *
	 * @param cssReference
	 *
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J addCssReference(CSSReference cssReference)
	{
		getBody().addCssReference(cssReference);
		return (J) this;
	}

	/**
	 * Adds a java script reference to the body
	 *
	 * @param jsReference
	 *
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J addJavaScriptReference(JavascriptReference jsReference)
	{
		getBody().addJavaScriptReference(jsReference);
		return (J) this;
	}

	@Override
	public Set getCssReferences()
	{
		return getBody().getCssReferences();
	}

	@Override
	public Set getJavascriptReferences()
	{
		return getBody().getJavascriptReferences();
	}

	/**
	 * Sets all component in the head and body to tiny
	 *
	 * @param tiny
	 *
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J setTiny(boolean tiny)
	{
		super.setTiny(tiny);
		getHead().setTiny(tiny);
		getBody().setTiny(tiny);
		return (J) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J addFeature(ComponentFeatureBase feature)
	{
		getBody().addFeature(feature);
		return (J) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J addVariable(String variable)
	{
		getBody().addVariable(variable);
		return (J) this;
	}

	/**
	 * Returns all the dynamic options for a page
	 *
	 * @return
	 */
	@Override
	@NotNull
	public PageOptions getOptions()
	{
		if (options == null)
		{
			options = new PageOptions();
		}
		return options;
	}

	/**
	 * Returns the JavaScript render for the body
	 *
	 * @return
	 */
	@Override
	public StringBuilder renderJavascript()
	{
		return getBody().renderJavascript();
	}

	@Override
	public void init()
	{
		if (!pageInitialized)
		{
			getBody().init();
			pageInitialized = true;
		}

		if (!isConfigured())
		{
			log.log(Level.FINER, "Looking for plugins....");
			Set<Class<? extends PageConfigurator>> configs = GuiceContext.reflect()
			                                                             .getSubTypesOf(PageConfigurator.class);
			List<PageConfigurator> configInstances = new ArrayList<>();
			for (Class<? extends PageConfigurator> pc : configs)
			{
				if (Modifier.isAbstract(pc.getModifiers()))
				{
					continue;
				}
				PageConfigurator config = GuiceContext.getInstance(pc);
				configInstances.add(config);
			}
			configInstances.sort(Comparator.comparing(PageConfigurator::getSortOrder));
			for (PageConfigurator configInstance : configInstances)
			{
				log.log(Level.FINER, "Configuring [" + configInstance.getClass()
				                                                     .getSimpleName() + "]");
				configInstance.configure(this);
			}
		}

		super.init();
	}

	/**
	 * Renders all the children to a string builder
	 *
	 * @return
	 */
	@Override
	protected StringBuilder renderChildren()
	{
		StringBuilder pageOutput = new StringBuilder();
		StringBuilder bodyOutput = null;

		if (!isBodyEmpty())
		{
			bodyOutput = new StringBuilder(getBody().toString(1));
		}
		if (!isHeadEmpty())
		{
			pageOutput.append(getNewLine())
			          .append(getCurrentTabIndentString())
			          .append(getHead().toString(1));
		}
		if (bodyOutput != null)
		{
			pageOutput.append(getNewLine())
			          .append(getCurrentTabIndentString())
			          .append(bodyOutput);
		}
		return pageOutput;
	}

	/**
	 * Returns if the body object is empty
	 *
	 * @return
	 */
	private boolean isBodyEmpty()
	{
		return getBody().getChildren()
		                .isEmpty();
	}

	/**
	 * Returns if the head object is empty
	 *
	 * @return
	 */
	private boolean isHeadEmpty()
	{
		return getHead().getChildren()
		                .isEmpty();
	}

	/**
	 * Configures the page and all its components
	 */
	@Override

	@SuppressWarnings("unchecked")
	public void preConfigure()
	{
		if (!isInitialized())
		{
			init();
		}
		if (!isConfigured())
		{
			configurePageHeader();
			addVariablesScriptToPage();
		}
		super.preConfigure();
	}

	@Override
	public void destroy()
	{
		if (getHead() != null)
		{
			getHead().destroy();
		}
		if (getBody() != null)
		{
			getBody().destroy();
		}
		angular = null;
		fields = null;
		options = null;
		userAgent = null;

		super.destroy();
	}

	/**
	 * Builds up the Header Tag
	 */
	@SuppressWarnings("unchecked")
	private void configurePageHeader()
	{
		if (getPageFields().getTitle() != null)
		{
			getHead().add(getPageFields().getTitle());
		}
		if (getPageFields().getBase() != null)
		{
			getHead().add(getPageFields().getBase());
		}
		getHead().add(getPageFields().getHttpEquivMeta());
		getHead().add(getPageFields().getCacheControl());
		getHead().add(getPageFields().getAuthor());
		getHead().add(getPageFields().getApplicationName());
		getHead().add(getPageFields().getGenerator());
		getHead().add(getPageFields().getDescription());
		getHead().add(getPageFields().getKeywords());
		getHead().add(getPageFields().getFavIconLink());
	}

	/**
	 * Adds the variables in the application to the collection
	 */
	@SuppressWarnings("unchecked")
	private void addVariablesScriptToPage()
	{
		StringBuilder variablesScriptBuilder = new StringBuilder();
		for (Object o : getBody().getVariablesAll())
		{
			String var = (String) o;
			variablesScriptBuilder.append("var ")
			                      .append(var)
			                      .append(STRING_SEMICOLON);
		}
		if (variablesScriptBuilder.length() > 0)
		{
			Script variablesScript = new Script();
			variablesScript.setID("variables");
			variablesScript.setNewLineForRawText(true);
			variablesScript.addAttribute(ScriptAttributes.Type, HTML_HEADER_JAVASCRIPT);
			variablesScript.setText(variablesScriptBuilder.toString());
			if (!getHead().getChildren()
			              .contains(variablesScript))
			{
				getHead().add(variablesScript);
			}
		}
	}

	/**
	 * Returns the cached component
	 *
	 * @param componentID
	 * 		The component to look for
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ComponentHierarchyBase getCachedComponent(String componentID)
	{
		for (Object chb : getBody().getChildrenHierarchy())
		{
			ComponentHierarchyBase<NoChildren, NoAttributes, NoFeatures, NoEvents, ? extends ComponentHierarchyBase> ch = (ComponentHierarchyBase) chb;
			if (ch.getID()
			      .equals(componentID))
			{
				return ch;
			}
		}
		return null;
	}

	/**
	 * Returns the component with only it's methods
	 *
	 * @return
	 */
	public IPage asMe()
	{
		return this;
	}

	/**
	 * Whether or not the page is initialized
	 *
	 * @return
	 */
	public boolean isPageInitialized()
	{
		return pageInitialized;
	}

	/**
	 * Sets if the page is initialized
	 *
	 * @param pageInitialized
	 */
	public void setPageInitialized(boolean pageInitialized)
	{
		this.pageInitialized = pageInitialized;
	}

	/**
	 * Returns a readable user agent, or null if just a basic render
	 *
	 * @return
	 */
	public ReadableUserAgent getUserAgent()
	{
		if (userAgent == null)
		{
			userAgent = new UserAgent(DeviceCategory.EMPTY, UserAgentFamily.FIREFOX, "", "", OperatingSystem.EMPTY, "", "", UserAgentType.BROWSER, "", "", VersionNumber.UNKNOWN);
		}
		return userAgent;
	}

	/**
	 * Sets the userAgent
	 *
	 * @param userAgent
	 */
	public void setUserAgent(ReadableUserAgent userAgent)
	{
		this.userAgent = userAgent;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}

	/**
	 * Sets teh fields currently set on the page
	 *
	 * @param fields
	 */
	public void setFields(PageFields fields)
	{
		this.fields = fields;
	}

	/**
	 * Adds a variable into angular
	 *
	 * @param variableName
	 *
	 * @return
	 */
	@Override
	@NotNull
	public Page addAngularVariable(String variableName)
	{
		getAngular().getAngularVariables()
		            .add(variableName);
		return this;
	}

	/**
	 * Returns the angular object
	 *
	 * @return
	 */
	@Override
	@NotNull
	public AngularPageConfigurator getAngular()
	{
		if (angular == null)
		{
			angular = GuiceContext.getInstance(AngularPageConfigurator.class);
			AngularPageConfigurator.setRequired(true);
		}
		return angular;
	}

	/**
	 * Returns the document type that will be rendered with this HTML page real-time
	 * <p>
	 *
	 * @return Document Type
	 */
	@Override
	@NotNull
	public DocumentType getDocumentType()
	{
		return new DocumentType(getBrowser().getHtmlVersion());
	}

	/**
	 * Returns the fields available for entry on this page
	 *
	 * @return
	 */
	@Override
	@NotNull
	public final PageFields getPageFields()
	{
		if (fields == null)
		{
			fields = new PageFields(this);
		}
		return fields;
	}

	/**
	 * Overidden method to return this, beware circular joins
	 *
	 * @return
	 */
	@NotNull
	public Page getPage()
	{
		return this;
	}

	/**
	 * Shortcut method to getBody().add()
	 *
	 * @param picker
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@NotNull
	public J add(GlobalChildren picker)
	{
		getBody().add(picker);
		return (J) this;
	}
}