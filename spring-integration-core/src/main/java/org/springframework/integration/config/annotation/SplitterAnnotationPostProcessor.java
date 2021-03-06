/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.config.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.splitter.MethodInvokingSplitter;
import org.springframework.messaging.MessageHandler;

/**
 * Post-processor for Methods annotated with {@link Splitter @Splitter}.
 *
 * @author Mark Fisher
 * @author Gary Russell
 */
public class SplitterAnnotationPostProcessor extends AbstractMethodAnnotationPostProcessor<Splitter> {

	public SplitterAnnotationPostProcessor(ListableBeanFactory beanFactory, Environment environment) {
		super(beanFactory, environment);
	}


	@Override
	protected MessageHandler createHandler(Object bean, Method method, List<Annotation> annotations) {
		MethodInvokingSplitter splitter = new MethodInvokingSplitter(bean, method);
		this.setOutputChannelIfPresent(annotations, splitter);
		return splitter;
	}

}
