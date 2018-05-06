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
package com.jwebmp.base.servlets;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Singleton;
import com.jwebmp.Page;
import com.jwebmp.annotations.DataCallInterception;
import com.jwebmp.annotations.SiteInterception;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.servlets.interfaces.IDataComponent;
import com.jwebmp.exceptions.MissingComponentException;
import za.co.mmagon.guiceinjection.GuiceContext;
import za.co.mmagon.logger.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides the data for a specific component
 *
 * @author GedMarc
 * @version 1.0
 * @since Nov 15, 2016
 */
@Singleton
public class DataServlet
		extends JWDefaultServlet
{

	/**
	 * The Servlet base logger
	 */
	private static final Logger log = LogFactory.getInstance()
	                                            .getLogger("DataServlet");
	private static final long serialVersionUID = 1L;
	/**
	 * The Object Mapper for rendering the JSON with Jackson
	 */
	private static final ObjectMapper jsonObjectMapper = new ObjectMapper();

	static
	{
		jsonObjectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
	}

	/**
	 * A data server
	 */
	public DataServlet()
	{
		//Nothing Needed
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 * 		Servlet request
	 * @param response
	 * 		Servlet response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			super.doGet(request, response);
			processRequest(request);
		}
		catch (MissingComponentException ex)
		{
			Logger.getLogger(DataServlet.class.getName())
			      .log(Level.SEVERE, null, ex);
		}
	}

	public void processRequest(HttpServletRequest request) throws MissingComponentException
	{
		Date startDate = new Date();
		StringBuilder responseString = new StringBuilder();
		HttpSession session = GuiceContext.inject()
		                                  .getInstance(HttpSession.class);
		Page page = GuiceContext.inject()
		                        .getInstance(Page.class);
		if (page == null)
		{
			throw new MissingComponentException(
					"Page has not been bound yet. Please use a binder to map Page to the required page object. Also consider using a @Provides method to apply custom logic. See https://github.com/google/guice/wiki/ProvidesMethods ");
		}

		String componentID = request.getParameter("component");
		log.log(Level.CONFIG, "[SessionID]-[{0}];[DataFetch];[ComponentID]-[{1}]", new Object[]{session.getId(), componentID});
		ComponentHierarchyBase hb = page.getCachedComponent(componentID);
		if (hb == null)
		{
			throw new MissingComponentException("Unable to find the specified component : " + request.getParameter("component"));
		}
		StringBuilder output = new StringBuilder();

		String searchString = (String) request.getSession()
		                                      .getAttribute("search");
		Integer totalCount = (Integer) request.getSession()
		                                      .getAttribute("count");
		String lastItemID = (String) request.getSession()
		                                    .getAttribute("lastID");

		log.info("Data Search Parameters : " + searchString + ",TotalCount " + totalCount + ",LastItemID [" + lastItemID + "]");

		if (!GuiceContext.isBuildingInjector())
		{
			intercept();
		}

		if (IDataComponent.class.isAssignableFrom(hb.getClass()))
		{
			IDataComponent dc = (IDataComponent) hb;
			String outputJson = null;
			try
			{
				outputJson = jsonObjectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true)
				                             .writeValueAsString(dc.getData(request.getParameterMap()));
			}
			catch (JsonProcessingException e)
			{
				log.log(Level.SEVERE, "Unable to format JSON Data Request", e);
			}
			output.append(outputJson == null ? "" : outputJson);
		}

		responseString.append(output);

		writeOutput(responseString, "application/json;charset=UTF-8", Charset.forName("UTF-8"));
		log.log(Level.CONFIG, "[SessionID]-[{0}];[Render Time]-[{1}];[Data Size]-[{2}]",
		        new Object[]{request.getSession().getId(), new Date().getTime() - startDate.getTime(), responseString.length()});
	}

	@SiteInterception
	@DataCallInterception
	protected void intercept()
	{
		//Interception Method
	}
}