package quan.dev.stockservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quan.dev.stockservice.component.KafkaConsumer;
import quan.dev.stockservice.model.MessageEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;


@RestController
public class SSEController {

    @Autowired
    @Qualifier("newFlux")
    private Flux<MessageEntity> newFlux;

    @Autowired
    @Qualifier("oldFlux")
    private Flux<MessageEntity> oldFlux;



    @GetMapping(value = "/flux1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageEntity> getMessNew() {
        return newFlux;
    }

    @GetMapping(value = "/flux2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageEntity> getMessOld() {
        return oldFlux;
    }

}
