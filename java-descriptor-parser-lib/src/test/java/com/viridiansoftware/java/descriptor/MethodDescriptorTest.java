/*******************************************************************************
 * Copyright 2019 Viridian Software Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.viridiansoftware.java.descriptor;

import org.junit.Assert;
import org.junit.Test;

public class MethodDescriptorTest {

	@Test
	public void testEquals() {
		final MethodDescriptor methodDescriptor1 = new MethodDescriptor("(ILjava/lang/Object;)V");
		final MethodDescriptor methodDescriptor2 = new MethodDescriptor("(II)B");
		final MethodDescriptor methodDescriptor3 = new MethodDescriptor("(II)B");

		Assert.assertEquals(false, methodDescriptor1.equals(methodDescriptor2));
		Assert.assertEquals(false, methodDescriptor1.equals(methodDescriptor3));
		Assert.assertEquals(methodDescriptor2, methodDescriptor3);
	}

	@Test
	public void testNoParametersVoidMethod() {
		final MethodDescriptor methodDescriptor = new MethodDescriptor("()V");
		Assert.assertEquals(true, methodDescriptor.isVoidMethod());
		Assert.assertEquals(0, methodDescriptor.getTotalMethodParameters());
		Assert.assertEquals("V", methodDescriptor.getReturnDescriptor().getText());
	}

	@Test
	public void testParametersVoidMethod() {
		final MethodDescriptor methodDescriptor = new MethodDescriptor("(ILjava/lang/Object;)V");
		Assert.assertEquals(true, methodDescriptor.isVoidMethod());
		Assert.assertEquals(2, methodDescriptor.getTotalMethodParameters());
		Assert.assertEquals("I", methodDescriptor.getMethodParameter(0).getText());
		Assert.assertEquals("java/lang/Object", methodDescriptor.getMethodParameter(1).objectType().identifier().getText());
		Assert.assertEquals("V", methodDescriptor.getReturnDescriptor().getText());
	}

	@Test
	public void testNoParametersObjectMethod() {
		final MethodDescriptor methodDescriptor = new MethodDescriptor("()Ljava/lang/Object;");
		Assert.assertEquals(false, methodDescriptor.isVoidMethod());
		Assert.assertEquals(0, methodDescriptor.getTotalMethodParameters());
		Assert.assertEquals("java/lang/Object", methodDescriptor.getReturnDescriptor().fieldType().objectType().identifier().getText());
	}

	@Test
	public void testParametersObjectMethod() {
		final MethodDescriptor methodDescriptor = new MethodDescriptor("(IBZ)Ljava/lang/Object;");
		Assert.assertEquals(false, methodDescriptor.isVoidMethod());
		Assert.assertEquals(3, methodDescriptor.getTotalMethodParameters());
		Assert.assertEquals("I", methodDescriptor.getMethodParameter(0).getText());
		Assert.assertEquals("B", methodDescriptor.getMethodParameter(1).getText());
		Assert.assertEquals("Z", methodDescriptor.getMethodParameter(2).getText());
		Assert.assertEquals("java/lang/Object", methodDescriptor.getReturnDescriptor().fieldType().objectType().identifier().getText());
	}

	@Test
	public void testParametersObjectMethod2() {
		final MethodDescriptor methodDescriptor = new MethodDescriptor("(Lsun/security/ec/point/Point;I)Lsun/security/ec/point/MutablePoint;");
		Assert.assertEquals(false, methodDescriptor.isVoidMethod());
		Assert.assertEquals(2, methodDescriptor.getTotalMethodParameters());
		Assert.assertEquals("Lsun/security/ec/point/Point;", methodDescriptor.getMethodParameter(0).getText());
		Assert.assertEquals("I", methodDescriptor.getMethodParameter(1).getText());
		Assert.assertEquals("sun/security/ec/point/MutablePoint", methodDescriptor.getReturnDescriptor().fieldType().objectType().identifier().getText());
	}

	@Test
	public void testParametersObjectMethodEnclosedClass() {
		final MethodDescriptor methodDescriptor = new MethodDescriptor("(Lsun/security/ec/point/Point;I)Lsun/security/ec/point/ProjectivePoint$Mutable;");
		Assert.assertEquals(false, methodDescriptor.isVoidMethod());
		Assert.assertEquals(2, methodDescriptor.getTotalMethodParameters());
		Assert.assertEquals("Lsun/security/ec/point/Point;", methodDescriptor.getMethodParameter(0).getText());
		Assert.assertEquals("I", methodDescriptor.getMethodParameter(1).getText());
		Assert.assertEquals("sun/security/ec/point/ProjectivePoint$Mutable", methodDescriptor.getReturnDescriptor().fieldType().objectType().identifier().getText());
	}
}
