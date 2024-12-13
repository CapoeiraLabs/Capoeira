# Capoeira  - Test Automation Framework 🤸🏿‍♀️

## Visão Geral

Capoeira  é um framework brasileiro de automação de testes para Java

## Estrutura do Projeto
- `capoeira-core`: Componentes principais do framework
- `capoeira-plugins`: Extensões para IDEs (IntelliJ e VS Code)
- `capoeira-examples`: Exemplos de uso
- `capoeira-cli`: Interface de linha de comando

## Características
- Testes de API REST simplificados
- Carregamento de fixtures
- Validações poderosas
- Suporte a múltiplas fontes de dados
- Geração de relatórios

## Instalação
1. Clone o repositório
2. Compile com Maven: `mvn clean install`

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

## Desenvolvimento de Plugins

### IntelliJ IDEA
- Localização: `capoeira-plugins/intellij-plugin`
- Desenvolvido com Java e SDK do IntelliJ
- Adiciona suporte para:
  - Execução de testes
  - Geração de configurações
  - Visualização de resultados

### VS Code
- Localização: `capoeira-plugins/vscode-extension`
- Desenvolvido com TypeScript
- Recursos:
  - Execução de testes
  - Integração com explorador de testes
  - Configurações personalizadas

## Contribuição
1. Faça um fork do projeto
2. Crie uma branch para sua feature
3. Submeta um Pull Request

## Licença
MIT License

```
Michael Bullet
```
