/*
 * Copyright (c) 2010-2017. Axon Framework
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

package org.axonframework.test.aggregate;

import org.junit.Rule;
import org.junit.Test;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StubAggregateLifecycleRuleTest {

    @Rule
    public StubAggregateLifecycleRule testSubject = new StubAggregateLifecycleRule();

    @Test
    public void testAppliedEventsArePassedToActiveLifecycle() throws Exception {
        apply("test");

        assertEquals(1, testSubject.getAppliedEvents().size());
        assertEquals("test", testSubject.getAppliedEventPayloads().get(0));
        assertEquals("test", testSubject.getAppliedEvents().get(0).getPayload());
    }

    @Test
    public void testMarkDeletedIsRegisteredWithActiveLifecycle() throws Exception {
        markDeleted();

        assertEquals(0, testSubject.getAppliedEvents().size());
        assertTrue(testSubject.isMarkedDeleted());
    }

}
