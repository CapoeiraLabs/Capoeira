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
    
