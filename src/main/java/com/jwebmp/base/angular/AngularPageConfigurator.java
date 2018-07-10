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
package com.jwebmp.base.angular;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.inject.Singleton;
import com.jwebmp.FileTemplates;
import com.jwebmp.Page;
import com.jwebmp.PageConfigurator;
import com.jwebmp.base.angular.configurations.AngularConfigurationBase;
import com.jwebmp.base.angular.controllers.AngularControllerBase;
import com.jwebmp.base.angular.factories.AngularFactoryBase;
import com.jwebmp.base.angular.modules.AngularMessagesModule;
import com.jwebmp.base.angular.modules.AngularModuleBase;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.plugins.PluginInformation;
import com.jwebmp.plugins.jquery.JQueryPageConfigurator;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author GedMarc
 * @since 21 Feb 2017
 */
@PluginInformation(pluginName = "AngularJS",
		pluginUniqueName = "angular",
		pluginDescription = "AngularJS is a toolset for building the " + "framework most suited to your " + "application development. It " + "is " + "fully extensible and works well " + "with other libraries. " + "Every " + "feature " + "can" + " be modified" + " or " + "replaced to suit your " + "unique " + "development " + "workflow" + " " + "and " + "feature " + "needs. Read on to" + " find out how. " + "",
		pluginVersion = "1.6",
		pluginDependancyUniqueIDs = "jquery",
		pluginCategories = "jquery, angular, data-binding, ng," + "google",
		pluginSubtitle = "Data-binding is an automatic way of updating the view whenever the model changes, as well as " + "updating the " + "model whenever the view changes. This is awesome because it eliminates DOM " + "manipulation from the" + "list  of " + "things" + " " + "you" + " " + "have" + " " + "to" + " " + "worry  " + "",
		pluginGitUrl = "https://github.com/GedMarc/JWebSwing",
		pluginSourceUrl = "https://angularjs.org",
		pluginWikiUrl = "https://github.com/GedMarc/JWebSwing/wiki",
		pluginOriginalHomepage = "https://angularjs.org",
		pluginDownloadUrl = "https://angularjs.org/",
		pluginIconUrl = "",
		pluginIconImageUrl = "https://angularjs.org/img/AngularJS-large.png",
		pluginLastUpdatedDate = "2017/03/30")
@Singleton
public class AngularPageConfigurator
		extends PageConfigurator
{

	public static final String AngularEnabledString = "angular-enabled";
	private static final Logger log = LogFactory.getLog("Angular Page Configurator");
	private static final long serialVersionUID = 1L;
	/**
	 * If the angular functionality is require or not
	 */
	private static boolean required;
	private static boolean angularMessagesRequired;
	/**
	 * All the angular modules for this component
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<AngularModuleBase> angularModules;

	/**
	 * All of the angular directives for this component
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<AngularControllerBase> angularControllers;
	/**
	 * All of the angular directives for this component
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<AngularFactoryBase> angularFactories;

	/**
	 * All of the angular directives for this component
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<AngularConfigurationBase> angularConfigurations;

	/**
	 * All of the angular controller insertions for this component
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<String> controllerInsertions;

	/**
	 * All the angular variables
	 */
	private Set<String> angularVariables;

	/**
	 * A set of event data event watchers
	 */
	private Set<AngularVariableWatcher> angularWatchers;


	/**
	 * Configures the angular page
	 */
	@SuppressWarnings("")
	public AngularPageConfigurator()
	{
		setSortOrder(99999999); //Always dead last
	}

	/**
	 * If the configurator is required
	 *
	 * @return
	 */
	public static boolean isRequired()
	{
		return required;
	}

	/**
	 * Sets angular as a required component
	 *
	 * @param required
	 */
	@SuppressWarnings("unchecked")
	public static void setRequired(boolean required)
	{
		AngularPageConfigurator.required = required;
		if (required)
		{
			JQueryPageConfigurator.setRequired(true);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page configure(Page page)
	{
		if (required)
		{
			page.getBody()
			    .addJavaScriptReference(AngularReferencePool.Angular1.getJavaScriptReference());
			page.getBody()
			    .addJavaScriptReference(AngularReferencePool.Angular1NGMessages.getJavaScriptReference());
			page.getBody()
			    .addAttribute(AngularAttributes.ngApp, AngularFeature.getAppName());
			page.getBody()
			    .addAttribute(AngularAttributes.ngController, AngularFeature.getControllerName() + " as jwCntrl");
		}
		return page;
	}

	/**
	 * Gets a list of angular modules
	 *
	 * @return
	 */
	@NotNull
	public Set<AngularModuleBase> getAngularModules()
	{
		if (angularModules == null)
		{
			setAngularModules(new LinkedHashSet<>());
			if (angularMessagesRequired)
			{
				angularModules.add(new AngularMessagesModule());
			}
		}
		return angularModules;
	}

	/**
	 * Sets the angular modules
	 *
	 * @param angularModules
	 */
	public void setAngularModules(@NotNull Set<AngularModuleBase> angularModules)
	{
		this.angularModules = angularModules;
	}

	/**
	 * Returns a list of all the angular controllers for this component
	 *
	 * @return
	 */
	@NotNull
	public Set<AngularControllerBase> getAngularControllers()
	{
		if (angularControllers == null)
		{
			setAngularControllers(new LinkedHashSet<>());

		}
		return angularControllers;
	}

	/**
	 * Sets the list of angular controllers for this component
	 *
	 * @param angularControllers
	 */
	public void setAngularControllers(@NotNull Set<AngularControllerBase> angularControllers)
	{
		this.angularControllers = angularControllers;
	}

	/**
	 * Returns the list of angular variables
	 *
	 * @return
	 */
	@NotNull
	public Set<String> getAngularVariables()
	{
		if (angularVariables == null)
		{
			angularVariables = new LinkedHashSet<>();
		}
		return angularVariables;
	}

	/**
	 * Sets the list of angular variables
	 *
	 * @param angularVariables
	 */
	public void setAngularVariables(@NotNull Set<String> angularVariables)
	{
		this.angularVariables = angularVariables;
	}

	/**
	 * Renders the complete angular javascript with the variables configured
	 *
	 * @param page
	 *
	 * @return
	 */
	@NotNull
	public StringBuilder renderAngularJavascript(Page page)
	{
		StringBuilder sb = new StringBuilder();
		AngularFeature af = new AngularFeature(page);
		af.configureTemplateVariables();
		sb.append(FileTemplates.renderTemplateScripts("jwangular"));
		log.finest("Rendering the angular script");
		return sb;
	}

	/**
	 * Returns a list of factories
	 *
	 * @return
	 */
	@NotNull
	public Set<AngularFactoryBase> getAngularFactories()
	{
		if (angularFactories == null)
		{
			angularFactories = new LinkedHashSet<>();
		}
		return angularFactories;
	}

	/**
	 * Sets the list of angular factories
	 *
	 * @param angularFactories
	 */
	public void setAngularFactories(@NotNull Set<AngularFactoryBase> angularFactories)
	{
		this.angularFactories = angularFactories;
	}

	/**
	 * Returns the list of controller insertions
	 *
	 * @return
	 */
	@NotNull
	public Set<String> getControllerInsertions()
	{
		if (controllerInsertions == null)
		{
			controllerInsertions = new LinkedHashSet<>();
		}
		return controllerInsertions;
	}

	/**
	 * Sets the list of controller insertions
	 *
	 * @param controllerInsertions
	 */
	public void setControllerInsertions(@NotNull Set<String> controllerInsertions)
	{
		this.controllerInsertions = controllerInsertions;
	}

	/**
	 * Gets the Angular Configurations
	 *
	 * @return
	 */
	public Set<AngularConfigurationBase> getAngularConfigurations()
	{
		if (angularConfigurations == null)
		{
			angularConfigurations = new HashSet<>();
		}
		return angularConfigurations;
	}

	/**
	 * Sets the current angular configuration base
	 *
	 * @param angularConfigurations
	 */
	public void setAngularConfigurations(@NotNull Set<AngularConfigurationBase> angularConfigurations)
	{
		this.angularConfigurations = angularConfigurations;
	}

	/**
	 * Gets the list of angular watchers
	 *
	 * @return
	 */
	public Set<AngularVariableWatcher> getAngularWatchers()
	{
		if (angularWatchers == null)
		{
			angularWatchers = new LinkedHashSet<>();
		}
		return angularWatchers;
	}

	/**
	 * Gets the list of angular watchers
	 *
	 * @param angularWatchers
	 */
	public void setAngularWatchers(@NotNull Set<AngularVariableWatcher> angularWatchers)
	{
		this.angularWatchers = angularWatchers;
	}
}