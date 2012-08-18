/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.ebean;


import com.avaje.ebean.EbeanServer;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * A simple provider that provides a singleton EbeansServer instance.
 *
 */
@Singleton
public class NinjaEbeanServerProvider implements Provider<EbeanServer> {

    private NinjaEbeanServerLifecycle ninjaEbeanServerLifecycle;

    @Inject
    public NinjaEbeanServerProvider(NinjaEbeanServerLifecycle ninjaEbeanServerLifecycle) {

        this.ninjaEbeanServerLifecycle = ninjaEbeanServerLifecycle;
        
    }

    @Override
    public EbeanServer get() {
        return ninjaEbeanServerLifecycle.getEbeanServer();
    }

}
