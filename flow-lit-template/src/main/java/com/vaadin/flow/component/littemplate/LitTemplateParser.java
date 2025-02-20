/*
 * Copyright 2000-2022 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.littemplate;

import org.jsoup.nodes.Element;

import com.vaadin.flow.component.littemplate.internal.LitTemplateParserImpl;
import com.vaadin.flow.di.Instantiator;
import com.vaadin.flow.server.VaadinService;

/**
 * Lit template content parser.
 * <p>
 * It returns a JSOUP element representing the content of template for the given
 * template class.
 * <p>
 * For internal use only. May be renamed or removed in a future release.
 *
 * @see LitTemplateParserImpl
 *
 * @author Vaadin Ltd
 * @since
 *
 */
@FunctionalInterface
public interface LitTemplateParser {

    /**
     * Lit template parser factory.
     * <p>
     * To be able to create a parser which can be provided as SPI use
     * {@link Instantiator} to create the factory and then get a parser from it:
     *
     * <pre>
     * <code>
     * Instantiator instantiator = ...;
     * LitTemplateParserFactory factory = instantiator.getOrCreate(LitTemplateParserFactory.class);
     * LitTemplateParser parser = factory.createParser();
     * </code>
     * </pre>
     * <p>
     *
     * @author Vaadin Ltd
     * @see LitTemplateParser
     * @since
     *
     */
    class LitTemplateParserFactory {

        /**
         * Creates a Lit template parser instance.
         *
         * @return a lit template parser instance
         */
        public LitTemplateParser createParser() {
            return LitTemplateParserImpl.getInstance();
        }
    }

    /**
     * Wrapper for the parsing result.
     * <p>
     * The data contains path uri where the template is declared and its content
     * as an {@link Element} instance.
     *
     * @author Vaadin Ltd
     * @since
     *
     */
    class TemplateData {

        private final String modulePath;
        private final Element templateElement;

        public TemplateData(String uri, Element element) {
            modulePath = uri;
            templateElement = element;
        }

        /**
         * Gets the uri where the template is declared.
         *
         * @return template uri
         */
        public String getModulePath() {
            return modulePath;
        }

        /**
         * Gets the content of the template.
         *
         * @return the content of the template
         */
        public Element getTemplateElement() {
            return templateElement;
        }
    }

    /**
     * Gets the template data which contains a JSOUP {@link Element}
     * representing the template content and the template uri.
     *
     * @param clazz
     *            the template class
     * @param tag
     *            the template tag name
     * @param service
     *            the related Vaadin service
     *
     * @return the template data, may be {@code null}
     */
    TemplateData getTemplateContent(Class<? extends LitTemplate> clazz,
            String tag, VaadinService service);
}
