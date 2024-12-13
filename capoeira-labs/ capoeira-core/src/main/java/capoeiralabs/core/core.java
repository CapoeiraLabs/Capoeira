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

/**
 * Classe principal do framework Capoeira Labs para automação de testes
 */
public class CapoeiraTest {
    
    // Gerenciador de configurações
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
     * Classe para execução de requisições HTTP
     */
    public static class HttpClient {
        private String baseUrl;
        private Map<String, String> headers = new HashMap<>();
        
        public HttpClient(String baseUrl) {
            this.baseUrl = baseUrl;
        }
        
        public HttpClient addHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }
        
        public Response get(String path) {
            return RestAssured.given()
                .headers(headers)
                .when()
                .get(baseUrl + path);
        }
        
        public Response post(String path, Object body) {
            return RestAssured.given()
                .headers(headers)
                .body(body)
                .when()
                .post(baseUrl + path);
        }
        
        public Response put(String path, Object body) {
            return RestAssured.given()
                .headers(headers)
                .body(body)
                .when()
                .put(baseUrl + path);
        }
        
        public Response delete(String path) {
            return RestAssured.given()
                .headers(headers)
                .when()
                .delete(baseUrl + path);
        }
    }
    
    /**
     * Classe de utilitários para validações
     */
    public static class Assertions {
        public static void assertStatusCode(Response response, int expectedStatusCode) {
            assertEquals(expectedStatusCode, response.getStatusCode(), 
                "Status code não corresponde ao esperado");
        }
        
        public static void assertBodyContains(Response response, String expectedContent) {
            assertTrue(response.getBody().asString().contains(expectedContent), 
                "Corpo da resposta não contém o conteúdo esperado");
        }
        
        public static void assertJsonPath(Response response, String jsonPath, Object expectedValue) {
            Object actualValue = response.jsonPath().get(jsonPath);
            assertEquals(expectedValue, actualValue, 
                "Valor no JSON path não corresponde ao esperado");
        }
    }
    
    /**
     * Classe para manipulação de dados de teste
     */
    public static class TestDataLoader {
        private ObjectMapper mapper = new ObjectMapper();
        
        public <T> T loadJsonFixture(String filePath, Class<T> clazz) {
            try {
                return mapper.readValue(
                    getClass().getResourceAsStream(filePath), 
                    clazz
                );
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar fixture", e);
            }
        }
        
        public List<Map<String, Object>> loadJsonArrayFixture(String filePath) {
            try {
                return mapper.readValue(
                    getClass().getResourceAsStream(filePath), 
                    mapper.getTypeFactory().constructCollectionType(List.class, Map.class)
                );
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar array de fixtures", e);
            }
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
