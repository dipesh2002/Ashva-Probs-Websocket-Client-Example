package com.pzone.ashva.probs.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.pzone.ashva.probs.client.service.APWsClientService;

/**
 * @author Ashva Probs Team
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class AshvaProbsClient {

  public static void main(String[] args) {
    final ConfigurableApplicationContext context = SpringApplication.run(AshvaProbsClient.class, args);
    final APWsClientService apWsClientService = context.getBean(APWsClientService.class);
    apWsClientService.connectToServer();
  }
}
