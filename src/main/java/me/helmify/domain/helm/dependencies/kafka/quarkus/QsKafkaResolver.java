package me.helmify.domain.helm.dependencies.kafka.quarkus;

import me.helmify.domain.helm.dependencies.kafka.KafkaResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class QsKafkaResolver implements KafkaResolver {

	@Override
	public List<String> matchOn() {
		return List.of("quarkus-smallrye-reactive-messaging-kafka");
	}

	public Map<String, String> getDefaultConfig() {
		return Map.of("MP_MESSAGING_INCOMING_KAFKA_BOOTSTRAP_SERVERS",
				"{{ .Values.global.hosts.kafka }}:{{ .Values.global.ports.kafka }}");
	}

}
