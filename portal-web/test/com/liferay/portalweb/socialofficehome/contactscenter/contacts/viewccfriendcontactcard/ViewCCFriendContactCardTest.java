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

package com.liferay.portalweb.socialofficehome.contactscenter.contacts.viewccfriendcontactcard;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewCCFriendContactCardTest extends BaseTestCase {
	public void testViewCCFriendContactCard() throws Exception {
		selenium.open("/user/joebloggs/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//nav/ul/li[1]/a/span")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.saveScreenShotAndSource();

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//div[2]/div/ul/li[2]/a")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.saveScreenShotAndSource();
		selenium.clickAt("//div[2]/div/ul/li[2]/a",
			RuntimeVariables.replace("Contacts Center"));
		selenium.waitForPageToLoad("30000");
		selenium.saveScreenShotAndSource();
		assertTrue(selenium.isVisible("//div[1]/a/img"));
		assertEquals(RuntimeVariables.replace("View 1 friends."),
			selenium.getText("//div[1]/div/div/div/div[2]/a"));
		selenium.mouseOver("//div[1]/a/img");
		assertEquals(RuntimeVariables.replace(
				"socialofficefriendfn socialofficefriendmn socialofficefriendln"),
			selenium.getText("//li/div[2]/div[1]"));
		assertEquals(RuntimeVariables.replace(
				"socialofficefriendea@liferay.com"),
			selenium.getText("//div[3]/span"));
		selenium.clickAt("//div[1]/a/img", RuntimeVariables.replace("Thumbnail"));
		selenium.waitForPageToLoad("30000");
		selenium.saveScreenShotAndSource();
		assertEquals(RuntimeVariables.replace(
				"socialofficefriendfn socialofficefriendmn socialofficefriendln"),
			selenium.getText("//div[2]/h1/span"));
		assertEquals(RuntimeVariables.replace(
				"socialofficefriendea@liferay.com"),
			selenium.getText("//div[3]/div/div[1]/div/div[1]/div/a"));
		assertEquals(RuntimeVariables.replace("Friend"),
			selenium.getText("//div[3]/div/div[1]/div/div[2]/div"));
		assertTrue(selenium.isPartialText("//div[3]/div[3]", "Recent Activity"));
		assertEquals(RuntimeVariables.replace("Whats Happening Content"),
			selenium.getText("//td[2]/div[1]"));
	}
}