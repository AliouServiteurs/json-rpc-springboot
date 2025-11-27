# 📝 JSON-RPC Spring Boot TP

![License](https://img.shields.io/badge/license-MIT-blue)

## 📄 Description

Ce projet est une implémentation pédagogique d’un **serveur JSON-RPC en Java avec Spring Boot**.  
Il inclut :

- Serveur Spring Boot exposant des méthodes via JSON-RPC
- Client Java pour tester les requêtes
- Tests unitaires avec JUnit
- Exemples de requêtes JSON-RPC pour Postman et curl
- Diagramme UML simplifié
- CI/CD via GitHub Actions

---
## Exemple de capture avec postman et Test avec ClientApp

---
## ⚡ Fonctionnalités

- Calculatrice simple : `add`, `subtract`, `multiply`, `divide`
- Gestion des erreurs JSON-RPC standard (ex : division par zéro, méthode inconnue)
- Client Java pour tester facilement le serveur
- Tests unitaires pour toutes les méthodes
- Collection Postman prête à l’emploi
- Extensible pour ajouter d’autres méthodes RPC

---

## 🗂️ Structure du projet

```
json-rpc-springboot/
├── src/
│   ├── main/java/com/example/jsonrpc/
│   │   ├── JsonRpcSpringbootApplication.java
│   │   ├── controller/JsonRpcController.java
│   │   ├── model/RpcRequest.java
│   │   ├── model/RpcResponse.java
│   │   ├── service/CalculatorService.java
│   │   └── exception/RpcException.java
│   └── test/java/com/example/jsonrpc/
│       └── JsonRpcControllerTest.java
├── client/
│   └── ClientApp.java
├── .github/workflows/ci.yml
├── pom.xml
└── README.md
```

---

## 🚀 Installation et exécution

### 1️⃣ Cloner le projet

```bash
git clone https://github.com/votre-utilisateur/json-rpc-springboot.git
cd json-rpc-springboot
```

### 2️⃣ Lancer le serveur

```bash
mvn clean spring-boot:run
```

Le serveur est accessible sur :  
```
http://localhost:8080/jsonrpc
```

---

## 📦 Build et exécution

### 1️⃣ Build Maven

```bash
mvn clean package
```

### 2️⃣ Lancer le jar

```bash
java -jar target/json-rpc-springboot-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Tests unitaires

```bash
mvn test
```

Tous les tests pour le controller et le service sont inclus et doivent passer.

---

## 📡 Tester avec curl

**Exemple : addition**

```bash
curl -X POST http://localhost:8080/jsonrpc \
-H "Content-Type: application/json" \
-d '{"jsonrpc": "2.0", "method": "add", "params": [5, 3], "id": 1}'
```

Réponse attendue :

```json
{
  "jsonrpc": "2.0",
  "result": 8.0,
  "error": null,
  "id": 1
}
```

---

## 🖥️ Tester avec Postman

1. Crée une requête **POST** vers :  
```
http://localhost:8080/jsonrpc
```

2. Dans **Body → raw → JSON**, envoie par exemple :

```json
{
  "jsonrpc": "2.0",
  "method": "divide",
  "params": [5, 0],
  "id": 5
}
```

3. Réponse attendue :

```json
{
  "jsonrpc": "2.0",
  "result": null,
  "error": {
    "code": -32000,
    "message": "Division par zéro interdite"
  },
  "id": 5
}
```

💡 Astuce : crée une **collection Postman** pour toutes les méthodes et exporte-la pour partager sur GitHub.

---

## 🧰 Client Java

Le client Java (`ClientApp.java`) permet d’envoyer toutes les requêtes RPC et d’afficher les résultats directement depuis la console.  

**Exécution du client** :

```bash
cd client
mvn compile exec:java -Dexec.mainClass="com.example.jsonrpc.client.ClientApp"
```

*(ou compiler manuellement si tu n’utilises pas Maven)*

---

## 🌐 Architecture et diagramme

```
Client JSON-RPC <----> Serveur JSON-RPC (Spring Boot)
+---------------------+       calls        +--------------------+
|     ClientApp       |  ---------------->|  JsonRpcController |
+---------------------+                   +--------------------+
        |                                         |
        | sends JSON-RPC request                  |
        |                                         |
        |                                         v
        |                                +--------------------+
        |                                |  CalculatorService |
        |                                +--------------------+
        |                                         |
        |                                         |
        |<------------------------ result --------|
        |
        v
+---------------------+
| JSON-RPC Response   |
+---------------------+
```

- **Controller** : reçoit la requête et appelle le service.  
- **Service** : logique métier (calculs).  
- **Models** : `RpcRequest` / `RpcResponse`.  
- **Exception** : `RpcException` pour gérer les erreurs.  

---

## 📦 Dépendances Maven

```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- JSON Parsing -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Tests -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 🤖 Intégration continue (CI) avec GitHub Actions

`.github/workflows/ci.yml` :

```yaml
name: Java CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    - name: Build with Maven
      run: mvn clean package
    - name: Run tests
      run: mvn test
```

---

