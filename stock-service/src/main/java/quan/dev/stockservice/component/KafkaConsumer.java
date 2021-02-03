package quan.dev.stockservice.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import quan.dev.stockservice.model.MessageEntity;
import quan.dev.stockservice.repo.MsgRepo;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;

@Component
public class KafkaConsumer {

    @Autowired
    MsgRepo msgRepo;

    @Autowired
    private Sinks.Many<MessageEntity> sink;

    @Autowired
    private EmitterProcessor<MessageEntity> emitterProcessor;


    @KafkaListener(topics = "stock", groupId = "group-id")
    public void listen(String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMsgContent(message);
        messageEntity = msgRepo.save(messageEntity);
//        Flux<MessageEntity> flux =
        sink.tryEmitNext(messageEntity);
        emitterProcessor.onNext(messageEntity);
        //emitterProcessor.onComplete();

    }
}
