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

package com.liferay.portalweb.portlet.directory.users.advancedsearchuser;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class AdvancedSearchUserScreenNameQuotesTest extends BaseTestCase {
	public void testAdvancedSearchUserScreenNameQuotes()
		throws Exception {
		int label = 1;

		while (label >= 1) {
			switch (label) {
			case 1:
				selenium.open("/web/guest/home/");

				for (int second = 0;; second++) {
					if (second >= 60) {
						fail("timeout");
					}

					try {
						if (selenium.isVisible("link=Directory Test Page")) {
							break;
						}
					}
					catch (Exception e) {
					}

					Thread.sleep(1000);
				}

				selenium.saveScreenShotAndSource();
				selenium.clickAt("link=Directory Test Page",
					RuntimeVariables.replace("Directory Test Page"));
				selenium.waitForPageToLoad("30000");
				selenium.saveScreenShotAndSource();

				boolean advancedVisible = selenium.isVisible(
						"link=Advanced \u00bb");

				if (!advancedVisible) {
					label = 2;

					continue;
				}

				selenium.clickAt("link=Advanced \u00bb",
					RuntimeVariables.replace("Advanced \u00bb"));

			case 2:

				for (int second = 0;; second++) {
					if (second >= 60) {
						fail("timeout");
					}

					try {
						if (selenium.isVisible(
									"//select[@id='_11_andOperator']")) {
							break;
						}
					}
					catch (Exception e) {
					}

					Thread.sleep(1000);
				}

				selenium.saveScreenShotAndSource();
				selenium.select("//select[@id='_11_andOperator']",
					RuntimeVariables.replace("label=Any"));
				selenium.type("//input[@id='_11_screenName']",
					RuntimeVariables.replace("\"TestSN\""));
				selenium.saveScreenShotAndSource();
				selenium.click(RuntimeVariables.replace(
						"//div[2]/span[2]/span/input"));
				selenium.waitForPageToLoad("30000");
				selenium.saveScreenShotAndSource();
				selenium.type("//input[@id='_11_screenName']",
					RuntimeVariables.replace(""));
				selenium.saveScreenShotAndSource();
				assertTrue(selenium.isElementPresent("link=TestFirst1"));
				selenium.type("//input[@id='_11_screenName']",
					RuntimeVariables.replace("\"TestSNA\""));
				selenium.saveScreenShotAndSource();
				selenium.click(RuntimeVariables.replace(
						"//div[2]/span[2]/span/input"));
				selenium.waitForPageToLoad("30000");
				selenium.saveScreenShotAndSource();
				selenium.select("//select[@id='_11_andOperator']",
					RuntimeVariables.replace("label=All"));
				selenium.type("//input[@id='_11_screenName']",
					RuntimeVariables.replace(""));
				selenium.saveScreenShotAndSource();
				selenium.clickAt("link=\u00ab Basic",
					RuntimeVariables.replace("\u00ab Basic"));
				assertFalse(selenium.isTextPresent("TestFirst1"));

			case 100:
				label = -1;
			}
		}
	}
}