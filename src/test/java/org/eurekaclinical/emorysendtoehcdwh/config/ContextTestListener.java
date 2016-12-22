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

import javax.servlet.ServletContextEvent;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.GuiceServletContextListener;
import org.eurekaclinical.common.config.InjectorSupport;

/**
 *
 * @author Andrew Post
 */
public class ContextTestListener extends GuiceServletContextListener {

    private PersistService persistService;
    private InjectorSupport injectorSupport;

    @Override
    public void contextInitialized(ServletContextEvent inServletContextEvent) {
        super.contextInitialized(inServletContextEvent);
        this.persistService = this.getInjector().getInstance(
                PersistService.class);
        this.persistService.start();
    }

    @Override
    protected Injector getInjector() {
        /**
         * This is called by super's contextInitialized.
         *
         * Must be created here in order for the modules to be initialized
         * correctly.
         */
        if (this.injectorSupport == null) {
            this.injectorSupport = new InjectorSupport(
                    new Module[]{
                        new AppTestModule(),
                        new ServletTestModule()
                    },
                    Stage.DEVELOPMENT);
        }
        return this.injectorSupport.getInjector();
    }

    @Override
    public void contextDestroyed(ServletContextEvent inServletContextEvent) {
        super.contextDestroyed(inServletContextEvent);
        if (this.persistService != null) {
            this.persistService.stop();
        }
    }
}
