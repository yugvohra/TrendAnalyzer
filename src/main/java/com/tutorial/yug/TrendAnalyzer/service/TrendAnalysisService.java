package com.tutorial.yug.TrendAnalyzer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.yug.TrendAnalyzer.model.KafkaFacebookMessage;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.List;

import java.io.IOException;

/**
 * Created by vohray on 6/24/18.
 */
@Service
@Slf4j
public class TrendAnalysisService {


  @Autowired
  ObjectMapper mapper;

  @Autowired
  StanfordCoreNLP stanfordCoreNLP;

  @KafkaListener(topics = "${app.topic.fcb}")
  public void receiveMessage(@Payload String message) throws IOException {
    log.info("received message " + message);
    KafkaFacebookMessage fcbPostAsMessage = mapper.readValue(message, KafkaFacebookMessage.class);
    printSentiment(fcbPostAsMessage);
  }

  private void printSentiment(KafkaFacebookMessage aMessage) {
    Annotation annotation = stanfordCoreNLP.process(aMessage.getMessage());
    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
    for (CoreMap sentence : sentences) {
      String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
      log.info(sentiment + "\t" + sentence);
    }
  }

}
