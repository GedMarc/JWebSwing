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
package com.jwebmp.core.plugins.jquery;

import com.jwebmp.core.base.references.JavascriptReference;
import com.jwebmp.core.base.servlets.enumarations.RequirementsPriority;

/**
 * @since @version @author MMagon
 */
public enum JQueryReferencePool
{
	JQueryV3(new JavascriptReference("JQuery", 3.51, "bower_components/jquery/dist/jquery.min.js", "https://code.jquery.com/jquery-3.5.1.js")
		.setPriority(RequirementsPriority.First)),

	JQueryMigrate(new JQueryMigrateReference());

	private final JavascriptReference reference;

	JQueryReferencePool(JavascriptReference reference)
	{
		this.reference = reference;
	}

	public JavascriptReference getJavaScriptReference()
	{
		return reference;
	}

}
