package quan.dev.stockservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import quan.dev.stockservice.model.MessageEntity;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    List<FluxSink<ServerSentEvent<MessageEntity>>> subscribers() {
        return new CopyOnWriteArrayList<>();
    }

    @Bean
    public Sinks.Many<MessageEntity> sink(){
        return Sinks.many().replay().latest();
    }

    @Bean("newFlux")
    public Flux<MessageEntity> flux1(@Autowired Sinks.Many<MessageEntity> sink){
        return sink.asFlux();
    }

    @Bean
    public EmitterProcessor<MessageEntity> entityEmitterProcessor() {
        return EmitterProcessor.create();
    }

    @Bean("oldFlux")
    public Flux<MessageEntity> publisher() {
      return entityEmitterProcessor().distinct();
    }


}
