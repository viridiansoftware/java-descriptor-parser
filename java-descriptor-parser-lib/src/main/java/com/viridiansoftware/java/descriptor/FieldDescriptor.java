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

import com.viridiansoftware.java.descriptor.antlr.DescriptorBaseListener;
import com.viridiansoftware.java.descriptor.antlr.DescriptorLexer;
import com.viridiansoftware.java.descriptor.antlr.DescriptorParser;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Objects;

public class FieldDescriptor extends DescriptorBaseListener {
	private final String descriptor;
	private DescriptorParser.FieldDescriptorContext fieldDescriptorContext;

	public FieldDescriptor(String descriptor) {
		super();
		this.descriptor = descriptor;

		if(descriptor == null || descriptor.isEmpty()) {
			return;
		}

		final DescriptorLexer lexer = new DescriptorLexer(CharStreams.fromString(descriptor));
		final DescriptorParser parser = new DescriptorParser(new BufferedTokenStream(lexer));

		final DescriptorParser.FieldDescriptorContext context = parser.fieldDescriptor();
		final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

		parseTreeWalker.walk(this, context);
	}

	@Override
	public void exitFieldDescriptor(DescriptorParser.FieldDescriptorContext ctx) {
		fieldDescriptorContext = ctx;
	}

	public DescriptorParser.FieldTypeContext getFieldTypeContext() {
		if(fieldDescriptorContext == null) {
			return null;
		}
		return fieldDescriptorContext.fieldType();
	}

	public String getDescriptorString() {
		return descriptor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FieldDescriptor)) return false;
		FieldDescriptor that = (FieldDescriptor) o;
		return Objects.equals(descriptor, that.descriptor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(descriptor);
	}
}
