package quan.dev.stockservice.config;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

public class MyEmitterProcessor {
    EmitterProcessor<String> emitterProcessor;

    public static void main(String args[]) {

        MyEmitterProcessor myEmitterProcessor = new MyEmitterProcessor();
        Flux<String> publisher = myEmitterProcessor.getPublisher();
        myEmitterProcessor.onNext("A");
        myEmitterProcessor.onNext("B");
        myEmitterProcessor.onNext("C");
        myEmitterProcessor.complete();

        publisher.subscribe(x -> System.out.println(x));

    }

    public Flux<String> getPublisher() {
        emitterProcessor = EmitterProcessor.create();
        return emitterProcessor.map(x -> "consume: " + x);
    }

    public  void onNext(String nextString) {
        emitterProcessor.onNext(nextString);
    }

    public  void complete() {
        emitterProcessor.onComplete();
    }
}
