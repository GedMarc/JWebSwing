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
package za.co.mmagon.jwebswing.base.servlets;

import com.armineasy.injection.GuiceContext;
import com.google.inject.Singleton;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import za.co.mmagon.jwebswing.Page;
import za.co.mmagon.jwebswing.annotations.DataCallInterception;
import za.co.mmagon.jwebswing.annotations.SiteInterception;
import za.co.mmagon.jwebswing.base.ComponentHierarchyBase;
import za.co.mmagon.jwebswing.base.ajax.*;
import za.co.mmagon.jwebswing.base.servlets.enumarations.ComponentTypes;
import za.co.mmagon.jwebswing.base.servlets.options.AngularDataServletInitData;
import za.co.mmagon.jwebswing.exceptions.InvalidRequestException;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.events.enumerations.EventTypes;
import za.co.mmagon.logger.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles angular data binding calls, registers variables for access. Can handle population, use event binding for call back.
 *
 * @author GedMarc
 * @since 20160607
 */
@Singleton
public class AngularDataServlet extends JWDefaultServlet
{

	public static final String LocalStorageSessionKey = "LocalStorage";
	public static final String SessionStorageSessionKey = "SessionStorage";
	private static final Logger LOG = LogFactory.getInstance().getLogger("AngularDataServlet");
	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request
	 * 		Servlet request
	 *
	 * @throws ServletException
	 * 		if a Servlet-specific error occurs
	 * @throws IOException
	 * 		if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request)
			throws ServletException, IOException, InvalidRequestException
	{
		LOG.log(Level.FINER, "[SessionID]-[{0}];[Connection]-[Data Call Connection Established]", request.getSession().getId());
		String componentId = "";
		StringBuilder jb = new StringBuilder(IOUtils.toString(request.getInputStream(), "UTF-8"));

		AngularDataServletInitData initData = new JavaScriptPart<>().From(jb.toString(), AngularDataServletInitData.class);
		if (initData == null)
		{
			throw new InvalidRequestException("Could not extract the initial data from the information sent in");
		}
		if (jb.length() > 0)
		{
			initData = new JavaScriptPart<>().From(jb.toString(), AngularDataServletInitData.class);
			GuiceContext.getInstance(SessionProperties.class).setLocalStorage(initData.getLocalStorage());
			GuiceContext.getInstance(SessionProperties.class).setSessionStorage(initData.getSessionStorage());
			componentId = initData.getParameters().get("objectId");
		}

		AjaxCall ajaxCall = GuiceContext.inject().getInstance(AjaxCall.class);
		ajaxCall.setParameters(initData.getParameters());
		ajaxCall.setComponentId(componentId);
		ajaxCall.setDatetime(Date.from(ZonedDateTime.now().toInstant()));
		ajaxCall.setEventType(EventTypes.data);
		if (componentId == null || componentId.isEmpty())
		{
			LOG.log(Level.FINER, "[SessionID]-[{0}];[Security]-[Component ID Incorrect]", request.getSession().getId());
		}

		Page page = GuiceContext.inject().getInstance(Page.class);
		ComponentHierarchyBase triggerComponent;
		if (ComponentTypes.Body.getComponentTag().equals(ajaxCall.getComponentId()))
		{
			triggerComponent = page.getBody();
		}
		else
		{
			triggerComponent = page.getCachedComponent(componentId);
		}
		ajaxCall.setComponent(triggerComponent);
		if (triggerComponent == null)
		{
			LOG.log(Level.SEVERE, "[SessionID]-[{0}];[Security]-[Invalid Component Specified]", request.getSession().getId());
			throw new ServletException("Component could not be found to process any events.");
		}
		AjaxResponse ajaxResponse = GuiceContext.inject().getInstance(AjaxResponse.class);
		try
		{
			intercept();
			page.onConnect(ajaxCall, ajaxResponse);
		}
		catch (Exception e)
		{
			ajaxResponse.addReaction(new AjaxResponseReaction("Error Performing Data Request", ExceptionUtils.getStackTrace(e), ReactionType.DialogDisplay, AjaxResponseType.Danger));
		}

		ajaxResponse.getComponents().forEach(a ->
		                                     {
			                                     ComponentHierarchyBase c = (ComponentHierarchyBase) a;
			                                     c.preConfigure();
		                                     });
		writeOutput(new StringBuilder(ajaxResponse.toString()), "application/json;charset=UTF-8", Charset.forName("UTF-8"));
	}

	@SiteInterception
	@DataCallInterception
	protected void intercept()
	{
		//Provides interception on the given annotations
	}

	/**
	 * Validates and sends the post
	 *
	 * @param request
	 * @param response
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			processRequest(request);
		}
		catch (IOException | ServletException | InvalidRequestException e)
		{
			LOG.log(Level.SEVERE, "Angular Data Servlet Do Post", e);
		}
	}
}
