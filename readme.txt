Firstly, I address EIP translater here.
It is applied in the final-Consumer folder and named Consumer.
It gets the current tic message, updates the stock's stats and send stats to its topic.
So subsriber only receives stats, combines them and shows it.
The processor is StockDataProcessor class. 

The following EIPs can be found in Producer class.
Message Broker (ActiveMQ), 
Endpoint (from/to), 
Message Channel (jms:queue:Stock_Data"),
Message (which encapsulates the data being passed between endpoints from ftp file to JMS queue)

Composite patten is implemented in Subscriber folder.
Portofolio class implements it and can report stock stats or Portofolio stats recrusively.

Template method is implemented in Subscriber foler and Component class is the abstract class.
PortofolioStats and stockstats classes inherit it. 
The updateStats method and add method are the implements of abstract methods.

Iterator patten is implemented in the Subsriber folder and traverses the components in Portofolio class.

Strategy patten is implemented in the Consumer class.
It is used to calculate different kinds of stats of stocks.
The application is in the StockDataProcessor.

Tradingqueue is for stock trading. 
It holds all ask and bid price and quantity in a priority queue.
If the lowest ask price is lower than highest bid price, it will make a deal. 