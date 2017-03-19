package de.goldmann.tienda.memory.db;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class InMemoryDB extends TextWebSocketHandler {
    Logger                     logger = LoggerFactory.getLogger(InMemoryDB.class);
    WebSocketSession           session;

    private final List<String> orders = new ArrayList<>();

    public List<String> getOrders() {
        return orders;
    }

    public void addOrder(final String order) throws Exception {
        orders.add(order);

        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(order));
            }
            catch (final Exception e) {
                logger.error("Fehler beim Senden der Nachricht: " + order, e);
                throw e;
            }
        } else {
            logger.warn("Don't have open session to send:" + order);
        }
    }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) {
        logger.info("Connection established");
        this.session = session;
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            if (session != null && session.isOpen()) {
                session.close();
            }
        } else {
            logger.info("Received:" + message.getPayload());
        }
    }
}
