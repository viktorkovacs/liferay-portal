/*
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

package com.liferay.portal.kernel.workflow;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <a href="WorkflowRequest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowRequest implements Serializable {

	public WorkflowRequest(Method method, Object[] arguments)
		throws WorkflowException {

		_method = method;
		_arguments = arguments;
		_userCredential = inspectForCallingUserCredential(method);
	}

	public Object execute(Object object) throws WorkflowException {
		try {
			UserCredentialThreadLocal.setUserCredential(_userCredential);

			return _method.invoke(object, _arguments);
		}
		catch (InvocationTargetException e) {
			throw new WorkflowException(e.getTargetException());
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			UserCredentialThreadLocal.setUserCredential(null);
		}
	}

	public UserCredential getUserCredential() {
		return _userCredential;
	}

	public boolean hasReturnValue() {
		if (_method.getReturnType() != Void.TYPE) {
			return true;
		}
		else {
			return false;
		}
	}

	protected UserCredential inspectForCallingUserCredential(Method method)
		throws WorkflowException {

		Annotation[][] annotationsArray = method.getParameterAnnotations();

		if ((annotationsArray == null) || (annotationsArray.length == 0)) {
			return null;
		}

		for (int i = 0; i < annotationsArray.length; i++) {
			Annotation[] annotations = annotationsArray[i];

			if ((annotations == null) || (annotations.length == 0)) {
				continue;
			}

			for (Annotation annotation : annotations) {
				if (CallingUserId.class.isAssignableFrom(
						annotation.annotationType())) {

					return UserCredentialFactoryUtil.createCredential(
						(Long)_arguments[i]);
				}
			}
		}

		return null;
	}

	private Object[] _arguments;
	private Method _method;
	private UserCredential _userCredential;

}