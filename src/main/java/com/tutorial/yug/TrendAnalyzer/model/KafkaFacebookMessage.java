package com.tutorial.yug.TrendAnalyzer.model;

import lombok.Value;

import java.util.Date;

/**
 * Created by vohray on 6/23/18.
 */
@Value
public class KafkaFacebookMessage {

  private String userId;
  private String userName;
  private Date postedDate;
  private String message;
}
