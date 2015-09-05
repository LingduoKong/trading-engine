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
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class Subscriber {

	public static void main(String args[]) throws Exception {

		StockStats msft = new StockStats("MSFT");
		StockStats orcl = new StockStats("ORCL");
		StockStats ibm = new StockStats("IBM");

		PortofolioStats MSFTandORCLPortfolio = new PortofolioStats("MSFT_ORCL");
		MSFTandORCLPortfolio.add(msft);
		MSFTandORCLPortfolio.add(orcl);

		PortofolioStats MSFTandIBMPortfolio = new PortofolioStats("MSFT_IBM");
		MSFTandIBMPortfolio.add(msft);
		MSFTandIBMPortfolio.add(ibm);

		PortofolioStats IBMandORCLPortfolio = new PortofolioStats("IBM_ORCL");
		IBMandORCLPortfolio.add(ibm);
		IBMandORCLPortfolio.add(orcl);

		PortofolioStats rootOfTokyo = new PortofolioStats("Tokyo_rootPortfolio");
		rootOfTokyo.add(MSFTandORCLPortfolio);
		rootOfTokyo.add(ibm);

		PortofolioStats rootOfLondon = new PortofolioStats("London_rootPortfolio");
		rootOfLondon.add(MSFTandIBMPortfolio);
		rootOfLondon.add(orcl);

		PortofolioStats rootOfNewYork = new PortofolioStats("Tokyo_rootPortfolio");
		rootOfNewYork.add(IBMandORCLPortfolio);
		rootOfNewYork.add(msft);

        ReportEngine report_Engine_Tokyo = new ReportEngine("Engine_Tokyo",rootOfTokyo);
        ReportEngine report_Engine_London = new ReportEngine("Engine_London",rootOfLondon);
        ReportEngine report_Engine_NewYork = new ReportEngine("Engine_NewYork",rootOfNewYork);

		// create CamelContext
		CamelContext TokyoChannal = new DefaultCamelContext();
		CamelContext LondonChannal = new DefaultCamelContext();
		CamelContext NewYorkChannal = new DefaultCamelContext();

		// connect to ActiveMQ JMS broker listening on localhost on port 61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		TokyoChannal.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		LondonChannal.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		NewYorkChannal.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext
		TokyoChannal.addRoutes(new Channal(report_Engine_Tokyo));
		LondonChannal.addRoutes(new Channal(report_Engine_London));
		NewYorkChannal.addRoutes(new Channal(report_Engine_NewYork));

		// start the route and let it do its work
		TokyoChannal.start();
		LondonChannal.start();
		NewYorkChannal.start();
		Thread.sleep(500000);

		// stop the CamelContext
		TokyoChannal.stop();
		LondonChannal.stop();
		NewYorkChannal.stop();
	}
}

class Channal extends RouteBuilder{
	
	ReportEngine report_Engine;
	
	public Channal(ReportEngine report_Engine){
		this.report_Engine = report_Engine;
	}
	
	public void configure() {
		from("jms:topic:Stock_TOPIC_MSFT")
				.process(new Processor() {
					public void process(Exchange e) throws Exception {
						String message = e.getIn().getBody(String.class);
						report_Engine.update(message);
						e.getIn().setBody(report_Engine.report());
					}
				})
				.to("jms:queue:Final_Report_" + report_Engine.getName());

		from("jms:topic:Stock_TOPIC_ORCL")
				.process(new Processor() {
					public void process(Exchange e) throws Exception {
						String message = e.getIn().getBody(String.class);
						report_Engine.update(message);
						e.getIn().setBody(report_Engine.report());
					}
				})
				.to("jms:queue:Final_Report_" + report_Engine.getName());

		from("jms:topic:Stock_TOPIC_IBM")
				.process(new Processor() {
					public void process(Exchange e) throws Exception {
						String message = e.getIn().getBody(String.class);
						report_Engine.update(message);
						e.getIn().setBody(report_Engine.report());
					}
				})
				.to("jms:queue:Final_Report_" + report_Engine.getName());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
