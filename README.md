# TrendAnalyzer

## Analyzes the mood of the trending data on Facebook.
## It parses the status updates and uses MIT library to determine the mood of the person that posted the update

### Kafka integration 
#### This application consumes the messages on the kafka stream and extracts the status updates from the kafka messages 
#### A typical message consist of 

userId;
userName;
postedDate;
message;
