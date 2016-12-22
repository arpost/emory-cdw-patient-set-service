/*
 * #%L
 * Eureka Protempa ETL
 * %%
 * Copyright (C) 2012 - 2013 Emory University
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
package org.eurekaclinical.emorysendtoehcdwh.test;


import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 *
 * @author hrathod
 */
public class Setup implements TestDataProvider {

    private final Provider<EntityManager> managerProvider;

    /**
     * Sets up necessary data for testing.
     *
     * @param inManagerProvider the entity manager provider.
     */
    @Inject
    public Setup(Provider<EntityManager> inManagerProvider) {
        this.managerProvider = inManagerProvider;
    }

    @Override
    public void setUp() throws TestDataException {
    }

    @Override
    public void tearDown() throws TestDataException {
        EntityManager entityManager = this.getEntityManager();
        entityManager.getTransaction().begin();
        
        entityManager.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return this.managerProvider.get();
    }
}
