package quan.dev.stockservice.component;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@Component
public class MySampleClient extends TextWebSocketHandler  {

    @Getter
    private final WebSocketSession clientSession;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public MySampleClient () throws ExecutionException, InterruptedException, IOException {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        this.clientSession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(), URI.create("wss://ws.finnhub.io?token=c09njdn48v6o0n9eqnmg")).get();
        this.clientSession.sendMessage(new TextMessage("{\"type\":\"subscribe\",\"symbol\":\"BINANCE:BTCUSDT\"}"));
        this.clientSession.sendMessage(new TextMessage("{\"type\":\"subscribe\",\"symbol\":\"IC MARKETS:1\"}"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        kafkaTemplate.send("stock" , message.getPayload());
    }

}
