## Exemplo de Uso

### Configuração
```java
ConfigurationManager.setConfig("api.baseUrl", "https://exemplo.com");
String baseUrl = ConfigurationManager.getConfig("api.baseUrl")
    .orElseThrow(() -> new RuntimeException("URL não configurada"));
```

### Carregamento de Fixtures
```java
UserData userData = TestDataLoader.loadJsonFixture(
    "src/test/resources/fixtures/user.json", 
    UserData.class
);
```

### Anotações de Teste
```java
@CapoeiraAnnotations.Priority(10)
@CapoeiraAnnotations.Category("IntegrationTest")
@PerformanceTest.PerformanceTest(maxExecutionTime = 500)
public void testUserCreation() {
    // Lógica do teste
}
```

## Próximos Passos
- Implementar mais extensões
- Melhorar geração de relatórios
- Adicionar suporte a mocks
*/
