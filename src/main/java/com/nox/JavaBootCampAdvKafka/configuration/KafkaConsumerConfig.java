package com.nox.JavaBootCampAdvKafka.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerConfig {
//    @Value("${spring.kafka.consumer.group-id}")
//    private String PING_PONG_CONSUMER_GROUP;
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String KAFKA_BOOTSTRAP_SERVERS;
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, PING_PONG_CONSUMER_GROUP);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//   //     props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
}
