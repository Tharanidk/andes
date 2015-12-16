/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 */

package org.wso2.andes.subscription;

import org.wso2.andes.kernel.AndesException;
import org.wso2.andes.kernel.DestinationType;
import org.wso2.andes.kernel.ProtocolType;

/**
 * Builder class for {@link SubscriptionProcessor}.
 */
public class SubscriptionProcessorBuilder {

    public static SubscriptionProcessor getClusterSubscriptionProcessor() throws AndesException {
        SubscriptionProcessor subscriptionProcessor = new SubscriptionProcessor();

        // Add handles for AMQP
        subscriptionProcessor.addHandler(ProtocolType.AMQP, DestinationType.QUEUE, new QueueSubscriptionStore());
        subscriptionProcessor.addHandler(ProtocolType.AMQP, DestinationType.TOPIC,
                new TopicSubscriptionBitMapStore(ProtocolType.AMQP));
        subscriptionProcessor.addHandler(ProtocolType.AMQP, DestinationType.DURABLE_TOPIC,
                new TopicSubscriptionBitMapStore(ProtocolType.AMQP));

        // Add handles for MQTT
        subscriptionProcessor.addHandler(ProtocolType.MQTT, DestinationType.TOPIC,
                new TopicSubscriptionBitMapStore(ProtocolType.MQTT));
        subscriptionProcessor.addHandler(ProtocolType.MQTT, DestinationType.DURABLE_TOPIC,
                new TopicSubscriptionBitMapStore(ProtocolType.MQTT));

        return subscriptionProcessor;
    }

    public static SubscriptionProcessor getLocalSubscriptionProcessor() throws AndesException {
        SubscriptionProcessor subscriptionProcessor = new SubscriptionProcessor();

        // Add handles for AMQP
        subscriptionProcessor.addHandler(ProtocolType.AMQP, DestinationType.QUEUE,
                new QueueSubscriptionStore());
        subscriptionProcessor.addHandler(ProtocolType.AMQP, DestinationType.TOPIC,
                new TopicSubscriptionBitMapStore(ProtocolType.AMQP));
        subscriptionProcessor.addHandler(ProtocolType.AMQP, DestinationType.DURABLE_TOPIC,
                new LocalDurableTopicSubscriptionStore());

        // Add handles for MQTT
        subscriptionProcessor.addHandler(ProtocolType.MQTT, DestinationType.TOPIC,
                new TopicSubscriptionBitMapStore(ProtocolType.MQTT));
        subscriptionProcessor.addHandler(ProtocolType.MQTT, DestinationType.DURABLE_TOPIC,
                new LocalDurableTopicSubscriptionStore());

        return subscriptionProcessor;
    }
}
