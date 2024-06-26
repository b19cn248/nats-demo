package org.example.natsdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;


@Service
public class Subscriber {

  @Value("${job.nats.topic}")
  String topic;

  @Autowired
  Connection connection;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @PostConstruct
  public void subscriberTopic() {
    Dispatcher dispatcher = connection.createDispatcher();
    dispatcher.subscribe(topic, message -> {
      try {
        String userJson = new String(message.getData(), StandardCharsets.UTF_8);
        User user = objectMapper.readValue(userJson, User.class);
        System.out.println("Message Received -> " + user);
      } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception
      }
    });
  }
}
