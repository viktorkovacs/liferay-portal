/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.struts;

import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mika Koivisto
 */
public class StrutsPortletActionAdapter extends BaseStrutsPortletAction {

	public StrutsPortletActionAdapter(
		PortletAction portletAction, ActionMapping actionMapping,
		ActionForm actionForm) {

		_portletAction = portletAction;
		_actionMapping = actionMapping;
		_actionForm = actionForm;
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		_portletAction.processAction(
			_actionMapping, _actionForm, portletConfig, actionRequest,
			actionResponse);
	}

	@Override
	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		ActionForward actionForward = _portletAction.render(
			_actionMapping, _actionForm, portletConfig, renderRequest,
			renderResponse);

		if (actionForward != null) {
			return actionForward.getPath();
		}

		return null;
	}

	@Override
	public void serveResource(
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		_portletAction.serveResource(
			_actionMapping, _actionForm, portletConfig, resourceRequest,
			resourceResponse);
	}

	private ActionForm _actionForm;
	private ActionMapping _actionMapping;
	private PortletAction _portletAction;

}