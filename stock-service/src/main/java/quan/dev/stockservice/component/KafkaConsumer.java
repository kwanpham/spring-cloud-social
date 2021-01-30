package quan.dev.stockservice.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import quan.dev.stockservice.model.MessageEntity;
import quan.dev.stockservice.repo.MsgRepo;
import reactor.core.publisher.Flux;

@Component
public class KafkaConsumer {

    @Autowired
    MsgRepo msgRepo;

    @KafkaListener(topics = "stock", groupId = "group-id")
    public void listen(String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMsgContent(message);
        messageEntity = msgRepo.save(messageEntity);
        Flux<MessageEntity> flux = Flux.just(messageEntity);

    }
}
