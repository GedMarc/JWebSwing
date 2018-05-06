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
package com.jwebmp.base.references;

import com.jwebmp.base.servlets.enumarations.RequirementsPriority;
import com.jwebmp.generics.WebReference;

/**
 * This class is a Java Reference
 *
 * @author MMagon
 * @version 1.0
 * @since 2014/12/09
 */
public class JavascriptReference
		extends WebReference<JavascriptReference>
{
	private static final long serialVersionUID = 1L;

	/**
	 * @param name
	 * 		The name of the reference
	 * @param version
	 * 		The version of the reference
	 * @param localReference
	 * 		The local reference
	 * @param remoteReference
	 * 		The remote reference
	 */
	public JavascriptReference(String name, Double version, String localReference, String remoteReference)
	{
		super(name, version, localReference, remoteReference);
	}

	/**
	 * @param name
	 * 		The name of the reference
	 * @param version
	 * 		The version of the reference
	 * @param localReference
	 * 		The local reference
	 */
	public JavascriptReference(String name, Double version, String localReference)
	{
		super(name, version, localReference, localReference);
	}

	/**
	 * @param name
	 * 		The name of the reference
	 * @param version
	 * 		The version of the reference
	 * @param localReference
	 * 		The local reference
	 * @param priority
	 */
	@SuppressWarnings("")
	public JavascriptReference(String name, Double version, String localReference, RequirementsPriority priority)
	{
		super(name, version, localReference, localReference);
		setPriority(priority);
	}

	/**
	 * @param name
	 * 		The name of the reference
	 * @param version
	 * 		The version of the reference
	 * @param localReference
	 * 		The local reference
	 * @param sortOrder
	 * 		Default Sort Order
	 * 		<p>
	 * 		0 - Root (JQuery) 5 - Core 10 - Core Accompanied 15 - Stand Alone Components 20 - Complex Components 500k - Default 600k - Atmosphere Reserved
	 */
	public JavascriptReference(String name, Double version, String localReference, Integer sortOrder)
	{
		super(name, version, localReference, localReference);
		setSortOrder(sortOrder);
	}

	/**
	 * @param name
	 * 		The name of the reference
	 * @param version
	 * 		The version of the reference
	 * @param localReference
	 * 		The local reference
	 * @param remoteReference
	 * 		The remote reference
	 * @param sortOrder
	 * 		Default Sort Order
	 * 		<p>
	 * 		0 - Root (JQuery) 5 - Core 10 - Core Accompanied 15 - Stand Alone Components 20 - Complex Components 500k - Default 600k - Atmosphere Reserved
	 */
	public JavascriptReference(String name, Double version, String localReference, String remoteReference, Integer sortOrder)
	{
		super(name, version, localReference, remoteReference);
		setSortOrder(sortOrder);
	}

	/**
	 * <p>
	 * 0 - Root (JQuery) 5 - Core 10 - Core Accompanied 15 - Stand Alone Components 20 - Complex Components 500k - Default 600k - Atmosphere Reserved
	 *
	 * @param name
	 * 		The name of the reference
	 * @param version
	 * 		The version of the reference
	 * @param localReference
	 * 		The local reference
	 * @param remoteReference
	 * 		The remote reference
	 * @param sortOrder
	 * 		Default Sort Order
	 * @param priority
	 */
	@SuppressWarnings("")
	public JavascriptReference(String name, Double version, String localReference, String remoteReference, Integer sortOrder, RequirementsPriority priority)
	{
		super(name, version, localReference, remoteReference);
		setSortOrder(sortOrder);
		setPriority(priority);
	}
}