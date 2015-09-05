/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class Consumer {

	public static void main(String args[]) throws Exception {
		
		final Stock Microsoft = new Stock("MSFT");
		final Stock Orcale = new Stock("ORCL");
		final Stock IBM = new Stock("IBM");
		
		// create CamelContext
		CamelContext context = new DefaultCamelContext();

		// connect to ActiveMQ JMS broker listening on localhost on port 61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			"tcp://localhost:61616");
		context.addComponent("jms",
			JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("jms:queue:Stock_Data")
				.log("RECEIVED:jms queue: ${body} from file: ${header.CamelFileNameOnly}")
				.choice()
				.when(body().regex(".*MSFT.*"))
					.process(new StockDataProcessor(Microsoft))
					.log("Send to\n ${body}")					
					.to("jms:topic:Stock_TOPIC_MSFT")
				.when(body().regex(".*ORCL.*"))
					.process(new StockDataProcessor(Orcale))
					.log("Send to\n ${body}")
					.to("jms:topic:Stock_TOPIC_ORCL")
				.when(body().regex(".*IBM.*"))
					.process(new StockDataProcessor(IBM))
					.log("Send to\n ${body}")					
					.to("jms:topic:Stock_TOPIC_IBM");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// start the route and let it do its work
		context.start();
		Thread.sleep(500000);
	
			// stop the CamelContext
		context.stop();
	}
}
