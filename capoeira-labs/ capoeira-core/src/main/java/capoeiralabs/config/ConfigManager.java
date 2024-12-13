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
