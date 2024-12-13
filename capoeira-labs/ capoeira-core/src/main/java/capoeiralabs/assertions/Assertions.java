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
    
