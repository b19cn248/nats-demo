package org.example.natsdemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class DemoController {

  private final Publisher publisher;

  public DemoController(Publisher publisher) {
    this.publisher = publisher;
  }

  @PostMapping
  public String testSendMessage(@RequestBody User user) {
    publisher.sendMessage(user);
    return "Message sent";
  }

}
