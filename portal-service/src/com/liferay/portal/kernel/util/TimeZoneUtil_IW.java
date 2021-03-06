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

package com.liferay.portal.kernel.util;

/**
 * @author Brian Wing Shun Chan
 */
public class TimeZoneUtil_IW {
	public static TimeZoneUtil_IW getInstance() {
		return _instance;
	}

	public java.util.TimeZone getDefault() {
		return TimeZoneUtil.getDefault();
	}

	public java.util.TimeZone getTimeZone(java.lang.String timeZoneId) {
		return TimeZoneUtil.getTimeZone(timeZoneId);
	}

	public void setDefault(java.lang.String timeZoneId) {
		TimeZoneUtil.setDefault(timeZoneId);
	}

	private TimeZoneUtil_IW() {
	}

	private static TimeZoneUtil_IW _instance = new TimeZoneUtil_IW();
}