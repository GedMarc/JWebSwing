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
package com.jwebmp.core.base.html.attributes;

import com.jwebmp.core.base.html.interfaces.AttributeDefinitions;

/**
 * The attribute enumeration for the input button type
 *
 * @author GedMarc
 */
public enum InputRangeAttributes
		implements AttributeDefinitions
{
	/**
	 * The minimum for the range type
	 */
	Min,
	/**
	 * The maximum for the range type
	 */
	Max,
	/**
	 * The step to apply for the range type
	 */
	Step;

	@Override
	public boolean isKeyword()
	{
		return false;
	}
}