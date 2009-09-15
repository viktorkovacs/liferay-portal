/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <a href="PublicRenderParametersPool.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class PublicRenderParametersPool {

	public static Map<String, String[]> get(
		HttpServletRequest request, long plid) {

		Map <String, String[]> publicRenderParameters = null;

		if (PropsValues.PORTLET_PUBLIC_RENDER_PARAMETER_DISTRIBUTION.equals(
			"LAYOUT_SET")) {

			HttpSession session = request.getSession();

			Map<Long, Map<String, String[]>> publicRenderParametersPool =
				(Map<Long, Map<String, String[]>>)session.getAttribute(
					WebKeys.PUBLIC_RENDER_PARAMETERS_POOL);

			if (publicRenderParametersPool == null) {
				publicRenderParametersPool = new ConcurrentHashMap
					<Long, Map<String, String[]>>();

				session.setAttribute(
					WebKeys.PUBLIC_RENDER_PARAMETERS_POOL,
					publicRenderParametersPool);
			}

			Layout layout = null;
			Long layoutSetId = 0L;

			try {
				layout = LayoutLocalServiceUtil.getLayout(plid);
				layoutSetId = layout.getLayoutSet().getLayoutSetId();
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unable to get layout for plid + " + plid);
				}
			}

			publicRenderParameters = publicRenderParametersPool.get(
				layoutSetId);

			if (publicRenderParameters == null) {
				publicRenderParameters = new HashMap<String, String[]>();

				publicRenderParametersPool.put(
					layoutSetId, publicRenderParameters);
			}
		}
		else {
			publicRenderParameters = RenderParametersPool.get(
				request, plid, PUBLIC_RENDER_PARAMETERS);
		}

		return publicRenderParameters;
	}

	private static Log _log =
		LogFactoryUtil.getLog(PublicRenderParametersPool.class);

	private static final String PUBLIC_RENDER_PARAMETERS =
		"PUBLIC_RENDER_PARAMETERS";

}