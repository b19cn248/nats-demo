package org.example.natsdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Publisher {

  @Value("${job.nats.topic}")
  String topic;

  @Autowired
  Connection connection;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public void sendMessage(User user) {
    try {
      String userJson = objectMapper.writeValueAsString(user);
      connection.publish(topic, userJson.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception
    }
  }
}
