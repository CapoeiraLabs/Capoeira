package com.capoeiralabs.framework;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CapoeiraTest {
    
    private static class ConfigManager {
        private static Map<String, Object> globalConfig = new HashMap<>();
        
        public static void setConfig(String key, Object value) {
            globalConfig.put(key, value);
        }
        
        public static Object getConfig(String key) {
            return globalConfig.get(key);
        }
    }
    
   

    /**
     * Exemplo de teste de integração usando Capoeira Labs
     */
    public static class ExampleIntegrationTest {
        @Test
        public void testUserApiFlow() {
            // Configuração
            ConfigManager.setConfig("baseUrl", "https://api.exemplo.com");
            
            // Criação do cliente HTTP
            HttpClient client = new HttpClient((String) ConfigManager.getConfig("baseUrl"))
                .addHeader("Content-Type", "application/json");
            
            // Carregar dados de teste
            TestDataLoader dataLoader = new TestDataLoader();
            Map<String, Object> userData = dataLoader.loadJsonFixture("/fixtures/user.json", Map.class);
            
            // Criar usuário
            Response createResponse = client.post("/users", userData);
            Assertions.assertStatusCode(createResponse, 201);
            
            // Recuperar usuário criado
            String userId = createResponse.jsonPath().getString("id");
            Response getResponse = client.get("/users/" + userId);
            Assertions.assertStatusCode(getResponse, 200);
            Assertions.assertJsonPath(getResponse, "name", userData.get("name"));
            
            // Atualizar usuário
            Map<String, Object> updateData = new HashMap<>(userData);
            updateData.put("name", "Nome Atualizado");
            Response updateResponse = client.put("/users/" + userId, updateData);
            Assertions.assertStatusCode(updateResponse, 200);
            
            // Deletar usuário
            Response deleteResponse = client.delete("/users/" + userId);
            Assertions.assertStatusCode(deleteResponse, 204);
        }
    }
}
