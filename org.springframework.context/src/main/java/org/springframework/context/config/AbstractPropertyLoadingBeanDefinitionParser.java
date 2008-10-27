/*
 * Copyright 2002-2008 the original author or authors.
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

package org.springframework.context.config;

import org.w3c.dom.Element;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;

/**
 * Abstract parser for &lt;context:property-.../&gt; elements.
 *
 * @author Juergen Hoeller
 * @author Arjen Poutsma
 * @since 2.5.2
 */
abstract class AbstractPropertyLoadingBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected boolean shouldGenerateId() {
		return true;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String location = element.getAttribute("location");
		if (StringUtils.hasLength(location)) {
			String[] locations = StringUtils.commaDelimitedListToStringArray(location);
			builder.addPropertyValue("locations", locations);
		}
        String propertiesRef = element.getAttribute("properties-ref");
        if (StringUtils.hasLength(propertiesRef)) {
            builder.addPropertyReference("properties", propertiesRef);
        }
        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
	}

}