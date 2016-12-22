package org.eurekaclinical.emorysendtoehcdwh.config;

/*-
 * #%L
 * Eureka! Clinical User Agreement Service
 * %%
 * Copyright (C) 2016 Emory University
 * %%
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
 * #L%
 */

import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 *
 * @author Andrew Post
 */
public class ServletTestModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {

        Map<String, String> params = new HashMap<>();
        params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES,
                "org.eurekaclinical.emorysendtoehcdwh.resource");
        params.put(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                RolesAllowedResourceFilterFactory.class.getName());

        serve("/api/*").with(GuiceContainer.class, params);
    }
}
