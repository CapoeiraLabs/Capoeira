  public static class ExampleIntegrationTest {
        @Test
        public void testUserApiFlow() {
             
            ConfigManager.setConfig("baseUrl", "https://api.exemplo.com");
            
            HttpClient client = new HttpClient((String) ConfigManager.getConfig("baseUrl"))
                .addHeader("Content-Type", "application/json");
            
            TestDataLoader dataLoader = new TestDataLoader();
            Map<String, Object> userData = dataLoader.loadJsonFixture("/fixtures/user.json", Map.class);
            
            Response createResponse = client.post("/users", userData);
            Assertions.assertStatusCode(createResponse, 201);
            
            String userId = createResponse.jsonPath().getString("id");
            Response getResponse = client.get("/users/" + userId);
            Assertions.assertStatusCode(getResponse, 200);
            Assertions.assertJsonPath(getResponse, "name", userData.get("name"));
            
            Map<String, Object> updateData = new HashMap<>(userData);
            updateData.put("name", "Nome Atualizado");
            Response updateResponse = client.put("/users/" + userId, updateData);
            Assertions.assertStatusCode(updateResponse, 200);
            
            Response deleteResponse = client.delete("/users/" + userId);
            Assertions.assertStatusCode(deleteResponse, 204);
        }
    }
}
