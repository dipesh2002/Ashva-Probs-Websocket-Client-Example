package com.pzone.ashva.probs.client.service;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pzone.ashva.probs.client.model.LiveProbsDetails;
import com.pzone.ashva.probs.client.ws.config.APWsClient;
import com.pzone.ashva.probs.client.ws.config.APWsClient.MessageHandler;

/**
 * @author Ashva Probs Team
 *
 */
@Service
public class APWsClientService {

  private static final Logger LOG  = LogManager.getLogger(APWsClientService.class);

  @Value("${server.ws.url}")
  private String              serverUrl;

  @Value("${server.ws.token}")
  private String              accessToken;

  private APWsClient          socketClient;

  private Gson                gson = new GsonBuilder().setPrettyPrinting().setLenient().create();

  /**
   * Connects to ashva probs websocket server to listen for prob messages.
   * 
   */
  public void connectToServer() {
    try {

      final String wsUrl = serverUrl + accessToken;
      socketClient = new APWsClient(new URI(wsUrl));
      socketClient.connectToServer();

      socketClient.addMessageHandler(new MessageHandler() {

        @Override
        public void handleMessage(String message) {
          final LiveProbsDetails liveProbsDetails = gson.fromJson(message, LiveProbsDetails.class);
          LOG.info("Live Probs {}", gson.toJson(liveProbsDetails));
        }
      });

    } catch (Exception e) {
      LOG.error("Error connection server.", e);
      throw new RuntimeException(e.getLocalizedMessage());
    }
  }
}
