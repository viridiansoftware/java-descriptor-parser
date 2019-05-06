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

public class MethodDescriptor extends DescriptorBaseListener {
	private DescriptorParser.MethodDescriptorContext methodDescriptorContext;

	public MethodDescriptor(String descriptor) {
		super();

		if(descriptor == null || descriptor.isEmpty()) {
			return;
		}
		final DescriptorLexer lexer = new DescriptorLexer(CharStreams.fromString(descriptor));
		final DescriptorParser parser = new DescriptorParser(new BufferedTokenStream(lexer));

		final DescriptorParser.MethodDescriptorContext context = parser.methodDescriptor();
		final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

		parseTreeWalker.walk(this, context);
	}

	@Override
	public void exitMethodDescriptor(DescriptorParser.MethodDescriptorContext ctx) {
		methodDescriptorContext = ctx;
	}

	public boolean isVoidMethod() {
		if(methodDescriptorContext == null) {
			return false;
		}
		return getReturnDescriptor().VoidDescriptor() != null;
	}

	public int getTotalMethodParameters() {
		if(methodDescriptorContext == null) {
			return 0;
		}
		if(methodDescriptorContext.parameterDescriptor() == null) {
			return 0;
		}
		return methodDescriptorContext.parameterDescriptor().size();
	}

	public DescriptorParser.FieldTypeContext getMethodParameter(int index) {
		if(methodDescriptorContext == null) {
			return null;
		}
		if(methodDescriptorContext.parameterDescriptor() == null) {
			return null;
		}
		if(methodDescriptorContext.parameterDescriptor().isEmpty()) {
			return null;
		}
		return methodDescriptorContext.parameterDescriptor(index).fieldType();
	}

	public DescriptorParser.ReturnDescriptorContext getReturnDescriptor() {
		if(methodDescriptorContext == null) {
			return null;
		}
		return methodDescriptorContext.returnDescriptor();
	}
}
