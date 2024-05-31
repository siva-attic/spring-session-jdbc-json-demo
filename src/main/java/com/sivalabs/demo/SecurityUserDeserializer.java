package com.sivalabs.demo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public class SecurityUserDeserializer extends JsonDeserializer<SecurityUser> {

    private static final TypeReference<Collection<SimpleGrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<Collection<SimpleGrantedAuthority>>() {
    };

    @Override
    public SecurityUser deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);

        String username = readJsonNode(jsonNode, "username").asText();

        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        String password = passwordNode.asText("");
        Collection<? extends GrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"),
                SIMPLE_GRANTED_AUTHORITY_SET);
        String role = authorities.stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);

        SecurityUser securityUser = new SecurityUser(username, password, role);
        securityUser.eraseCredentials();
        return securityUser;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}
