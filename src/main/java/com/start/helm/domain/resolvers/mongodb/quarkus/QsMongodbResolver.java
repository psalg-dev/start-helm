package com.start.helm.domain.resolvers.mongodb.quarkus;

import com.start.helm.domain.helm.HelmContext;
import com.start.helm.domain.resolvers.mongodb.MongodbResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.start.helm.util.HelmUtil.makeSecretKeyRef;

@Component
public class QsMongodbResolver implements MongodbResolver {

	@Override
	public List<String> matchOn() {
		return List.of("quarkus-mongodb-panache", "quarkus-mongodb-client");
	}

	public List<Map<String, Object>> getEnvironmentEntries(HelmContext context) {
		return List.of(
				makeSecretKeyRef("QUARKUS_MONGODB_CREDENTIALS_USERNAME", "mongodb-username", context.getAppName()),
				makeSecretKeyRef("QUARKUS_MONGODB_CREDENTIALS_PASSWORD", "mongodb-password", context.getAppName()));
	}

	public Map<String, Object> getSecretEntries() {
		return Map.of("mongodb-username", "{{ (first .Values.mongodb.auth.usernames) | b64enc | quote }}",
				"mongodb-password", "{{ (first .Values.mongodb.auth.passwords) | b64enc | quote }}");
	}

	public Map<String, String> getDefaultConfig() {
		return Map.of("quarkus.mongodb.connection-string",
				"mongodb://{{ .Values.global.hosts.mongodb }}:{{ .Values.global.ports.mongodb }}",
				"quarkus.mongodb.database", "{{ .Values.mongodb.database }}");
	}

}
