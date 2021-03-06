/*
 * Copyright (C) 2017 GedMarc
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
package com.jwebmp.core.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwebmp.core.base.interfaces.IComponentDependencyBase;
import com.jwebmp.core.base.references.CSSReference;
import com.jwebmp.core.base.references.JavascriptReference;
import com.jwebmp.core.base.servlets.enumarations.ComponentTypes;
import com.jwebmp.core.base.servlets.enumarations.RequirementsPriority;

import jakarta.validation.constraints.NotNull;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This class marks a component as a web component.
 * <p>
 * Allows for JavaScript and CSS References to Exist
 *
 * @param <J>
 *
 * @author GedMarc
 * @since 23 Apr 2016
 */
public class ComponentDependencyBase<J extends ComponentDependencyBase<J>>
		extends ComponentBase<J>
		implements IComponentDependencyBase<J>
{
	/**
	 * The CSS String List of this component
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<CSSReference> cssReferences;
	/**
	 * The JavaSript String list for this component
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<JavascriptReference> javascriptReferences;

	/**
	 * Instantiates a Component with the ability to have CSS and JavaScript references
	 *
	 * @param componentType
	 */
	public ComponentDependencyBase(ComponentTypes componentType)
	{
		super(componentType);
	}

	/**
	 * Returns an Attribute Base interface of this component
	 *
	 * @return
	 */
	@NotNull
	@SuppressWarnings("unused")
	public IComponentDependencyBase<J> asDependencyBase()
	{
		return this;
	}

	/**
	 * Adds a CSS Reference to the collection
	 *
	 * @param cssReference
	 *
	 * @return This Class
	 */
	@Override
	@NotNull
	@SuppressWarnings("unchecked")
	public J addCssReference(@NotNull CSSReference cssReference)
	{
		getCssReferences().add(cssReference);
		return (J) this;
	}

	/**
	 * Adds a JavaScript Reference to the collection
	 *
	 * @param jsReference
	 *
	 * @return This Class
	 */
	@Override
	@NotNull
	@SuppressWarnings("unchecked")
	public J addJavaScriptReference(@NotNull JavascriptReference jsReference)
	{
		getJavascriptReferences().add(jsReference);
		return (J) this;
	}

	/**
	 * Returns the strings of the CSS Links this will use
	 * <p>
	 *
	 * @return ArrayList of all CSS File Links in String format
	 */
	@Override
	@NotNull
	public Set<CSSReference> getCssReferences()
	{
		if (cssReferences == null)
		{
			cssReferences = new TreeSet<>();
		}
		return cssReferences;
	}

	/**
	 * Return all the CSS References associated with this component
	 *
	 * @return
	 */
	@Override
	@NotNull
	public Set<CSSReference> getCssReferencesAll()
	{
		return getCssReferences();
	}

	/**
	 * Returns all the CSS references with the given priority
	 *
	 * @param priority
	 * 		The priority of the references to retrieve
	 *
	 * @return A new array list of all the requested for references
	 */
	@Override
	@NotNull
	public Set<CSSReference> getCssReferencesAll(@NotNull RequirementsPriority priority)
	{
		Set<CSSReference> arr = new TreeSet<>();
		for (CSSReference next : getCssReferencesAll())
		{
			if (next == null || !next.getPriority()
			                         .equals(priority) || arr.contains(next))
			{
				continue;
			}
			arr.add(next);
		}

		return arr;
	}

	/**
	 * Returns the JavaScript links attached to this component
	 * <p>
	 *
	 * @return
	 */
	@Override
	@NotNull
	public Set<JavascriptReference> getJavascriptReferences()
	{
		if (javascriptReferences == null)
		{
			javascriptReferences = new TreeSet<>();
		}
		return javascriptReferences;
	}

	/**
	 * Return all the CSS References associated with this component. Override and add the references required for the functionality
	 *
	 * @return
	 */
	@Override
	@NotNull
	public Set<JavascriptReference> getJavascriptReferencesAll()
	{
		return getJavascriptReferences();
	}

	/**
	 * Returns all the CSS references with the given priority Override and add the references required for the functionality
	 *
	 * @param priority
	 * 		The priority of the references to retrieve
	 *
	 * @return A new array list of all the requested for references
	 */
	@Override
	@NotNull
	public Set<JavascriptReference> getJavascriptReferencesAll(@NotNull RequirementsPriority priority)
	{
		Set<JavascriptReference> arr = new TreeSet<>();

		for (JavascriptReference next : new CopyOnWriteArraySet<>(getJavascriptReferencesAll()))
		{
			if (!next.getPriority()
			         .equals(priority) || arr.contains(next))
			{
				continue;
			}
			arr.add(next);
		}

		return arr;
	}

	/**
	 * Removes the CSS Reference from the Component
	 *
	 * @param cssReference
	 *
	 * @return This Class
	 */
	@Override
	@NotNull
	@SuppressWarnings("unchecked")
	public J removeCssReference(@NotNull CSSReference cssReference)
	{
		getCssReferences().remove(cssReference);
		return (J) this;
	}

	/**
	 * Removes the CSS Reference from the Component
	 *
	 * @param jsReference
	 *
	 * @return This Class
	 */
	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J removeJavaScriptReference(JavascriptReference jsReference)
	{
		getJavascriptReferences().remove(jsReference);
		return (J) this;
	}

	/**
	 * Clones this component with all the CSS and JavaScript references
	 *
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J cloneComponent()
	{
		ComponentDependencyBase<J> cloned = super.cloneComponent();

		cloned.cssReferences = new TreeSet<>();
		cloned.javascriptReferences = new TreeSet<>();
		cloned.cssReferences.addAll(getCssReferences());
		cloned.javascriptReferences.addAll(getJavascriptReferences());
		
		//noinspection CastCanBeRemovedNarrowingVariableType
		return (J) cloned;
	}

	/**
	 * Method hashCode ...
	 *
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	/**
	 * Method equals ...
	 *
	 * @param o
	 * 		of type Object
	 *
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}

	/**
	 * Destroys this class cleanly
	 */
	@Override
	public void destroy()
	{
		if (cssReferences != null)
		{
			cssReferences.clear();
			cssReferences = null;
		}
		if (javascriptReferences != null)
		{
			javascriptReferences.clear();
			javascriptReferences = null;
		}
		super.destroy();
	}
}
