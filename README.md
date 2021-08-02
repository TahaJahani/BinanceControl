# BinanceControl
An application to watch every minute changes of several currencies.
This application consists of four main components which will be fully described in next sections.

## Data Collector Module
This module mainly focuses on sending requests to different APIs and receiving candles at a one-minute-rate.
There are two different classes in this module:
- APIClient: This class is responsible for sending `GET` requests to APIs. Currently, only one API is supported but
several APIs can be added by just adding their URLs and some minor changes in `SendGetRequest` function.
- SynchronousProvider: In this class there is a `KafkaProvider` which connects to the kafka broker and pushes
each received candle to the only partition in the topic.

## Rules Evaluator Module
At the start, this module reads Rules defined in `Rules.json` file and saves them in memory. Every time a new candle is
sent, all the rules are evaluated and if necessary, new notifications are inserted in database.
There are four different classes in this module:
- SynchronousListener: This class is the kafka consumer. it checks for new candles every second and inserts it into
`Candlecontroller`'s queue.
- CandleController: This class holds a queue of candles of last 2 hours. There are a few helper functions in this class
such as `calculateSMA` that calculates SMA for a given Item and Length.
- RulesEvaluator: A class that read rules from a file and evaluates them whenever a new candle is inserted.
- DatabaseHelper: Connects to database and inserts new records for notifications, if needed.

## Models Module
This module hold three classes which are just simple model data used all over the application.

## Database Module
This module serves as a server which provides API for users to see recent notification through the route `/notifications/all`.
This module uses Spring and Hibernate frameworks to connect to database and routing.