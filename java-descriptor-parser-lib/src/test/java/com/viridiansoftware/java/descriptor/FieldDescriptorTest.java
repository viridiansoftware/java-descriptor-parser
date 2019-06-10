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

public class FieldDescriptorTest {

	@Test
	public void testEquals() {
		final FieldDescriptor fieldDescriptor1 = new FieldDescriptor("Ljava/lang/Object;");
		final FieldDescriptor fieldDescriptor2 = new FieldDescriptor("B");
		final FieldDescriptor fieldDescriptor3 = new FieldDescriptor("Ljava/lang/Object;");

		Assert.assertEquals(fieldDescriptor1, fieldDescriptor3);
		Assert.assertEquals(fieldDescriptor3, fieldDescriptor1);
		Assert.assertEquals(false, fieldDescriptor1.equals(fieldDescriptor2));
	}

	@Test
	public void testObjectType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("Ljava/lang/Object;");
		Assert.assertEquals("java/lang/Object", fieldDescriptor.getFieldTypeContext().objectType().identifier().getText());
	}

	@Test
	public void testArrayType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("[B");
		Assert.assertEquals("B", fieldDescriptor.getFieldTypeContext().arrayType().fieldType().getText());
	}

	@Test
	public void test2DArrayType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("[[B");
		Assert.assertEquals("B", fieldDescriptor.getFieldTypeContext().arrayType().fieldType().arrayType().fieldType().getText());
	}

	@Test
	public void testByteBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("B");
		Assert.assertEquals("B", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}

	@Test
	public void testCharBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("C");
		Assert.assertEquals("C", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}

	@Test
	public void testDoubleBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("D");
		Assert.assertEquals("D", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}

	@Test
	public void testFloatBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("F");
		Assert.assertEquals("F", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}

	@Test
	public void testIntBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("I");
		Assert.assertEquals("I", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}

	@Test
	public void testLongBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("J");
		Assert.assertEquals("J", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}

	@Test
	public void testShortBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("S");
		Assert.assertEquals("S", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}

	@Test
	public void testBooleanBaseType() {
		final FieldDescriptor fieldDescriptor = new FieldDescriptor("Z");
		Assert.assertEquals("Z", fieldDescriptor.getFieldTypeContext().BaseType().getText());
	}
}
