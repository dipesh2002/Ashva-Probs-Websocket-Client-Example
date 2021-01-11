package com.pzone.ashva.probs.client.ws.config;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Ashva Probs Team
 *
 */
@ClientEndpoint
public class APWsClient {

  private static final Logger LOG                    = LogManager.getLogger(APWsClient.class);

  private Session             userSession            = null;
  private MessageHandler      messageHandler;
  private WebSocketContainer  container;
  private URI                 uri;
  private Boolean             isUserSessionAvailable = false;

  private Integer             bufferSize             = (1024 * 1024) * 10;
  private Boolean             showLog                = true;

  public APWsClient(URI endpointURI) {
    this.uri = endpointURI;
    this.container = ContainerProvider.getWebSocketContainer();
    container.setDefaultMaxBinaryMessageBufferSize(bufferSize);
    container.setDefaultMaxTextMessageBufferSize(bufferSize);
  }

  public void connectToServer() {
    try {
      if (userSession != null) {
        userSession.close();
        userSession = null;
      }

      if (userSession == null) {
        if (showLog) {
          LOG.info("Try Connecting with server at {}", getServerUrl());
        }
        userSession = container.connectToServer(this, this.uri);

      }
    } catch (Exception e) {
      if (showLog) {
        LOG.warn("Error connecting server. Error is {}", e.getMessage());
      }
    } finally {
      this.showLog = false;
    }
  }

  /**
   * Callback hook for Connection open events.
   *
   * @param userSession
   *          the userSession which is opened.
   */
  @OnOpen
  public void onOpen(Session session) {
    userSession = session;
    this.isUserSessionAvailable = true;
    this.showLog = true;
    if (showLog) {
      LOG.info("Client connected with server at {}", getServerUrl());
      this.showLog = false;
    }

  }

  /**
   * Callback hook for Connection close events.
   *
   * @param userSession
   *          the userSession which is getting closed.
   * @param reason
   *          the reason for connection close
   */
  @OnClose
  public void onClose(Session userSession, CloseReason reason) {
    userSession = null;
    this.isUserSessionAvailable = false;
    this.showLog = true;
    LOG.info("Client connections with server is close for {}", reason.getReasonPhrase());
  }

  /**
   * Callback hook for Message Events. This method will be invoked when a client send a
   * message.
   *
   * @param message
   *          The text message
   */
  @OnMessage
  public void onMessage(String message) {
    if (this.messageHandler != null) {
      this.messageHandler.handleMessage(message);
    }
  }

  @OnError
  public void onError(Throwable error) {
    if (this.showLog)
      LOG.warn("Error connectiong controller at {}. Error is {}", getServerUrl(), error.getMessage());
  }

  /**
   * register message handler
   *
   * @param msgHandler
   */
  public void addMessageHandler(MessageHandler msgHandler) {
    this.messageHandler = msgHandler;
  }

  /**
   * Send a message.
   *
   * @param message
   * @throws IOException
   */
  public void sendMessage(String message) {
    synchronized (userSession) {
      userSession.getAsyncRemote().sendText(message);
    }
  }

  /**
   * Get websocket server url
   * 
   * @return String
   */
  private String getServerUrl() {
    return this.uri.getHost() + ":" + this.uri.getPort() + this.uri.getPath();
  }

  /**
   * Check if websocket session is open.
   * 
   * @return Boolean
   */
  public Boolean isSessionAvailable() {
    if (userSession != null && userSession.isOpen()) {
      this.isUserSessionAvailable = true;
    } else {
      this.isUserSessionAvailable = false;
    }
    return isUserSessionAvailable;
  }

  /**
   * Message handler.
   *
   */
  public static interface MessageHandler {

    public void handleMessage(String message);
  }
}