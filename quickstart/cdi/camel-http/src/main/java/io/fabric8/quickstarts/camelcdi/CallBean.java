/**
 *  Copyright 2005-2015 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.quickstarts.camelcdi;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.Uri;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This bean bootstrap and keep calling the Camel route forever in a forever while loop
 */
public class CallBean {

    private static final Logger LOG = LoggerFactory.getLogger(CallBean.class);

    @Inject
    private CamelContext camel;

    @Inject @Uri("direct:start")
    private ProducerTemplate producer;

    public void bootstrap(@Observes ContainerInitialized event) {
       LOG.info("Bootstrapping Camel {} with producer {}", camel, producer);

        // keep sending forever
        do {
            producer.sendBody(null);
        } while (true);
    }

}
