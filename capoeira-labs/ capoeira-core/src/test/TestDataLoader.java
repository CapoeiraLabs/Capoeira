
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
    
