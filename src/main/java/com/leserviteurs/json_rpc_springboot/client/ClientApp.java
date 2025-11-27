package com.leserviteurs.json_rpc_springboot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Client Java pour tester le serveur JSON-RPC.
 * 
 * Utilise HTTP POST pour envoyer des requêtes JSON-RPC et lire les réponses.
 */
public class ClientApp {

    // URL du serveur JSON-RPC
    private static final String SERVER_URL = "http://localhost:8080/jsonrpc";

    public static void main(String[] args) {
        try {
            // Exemple d'appel à toutes les méthodes
            callRpcMethod("add", new Object[]{5, 3}, 1);
            callRpcMethod("subtract", new Object[]{10, 4}, 2);
            callRpcMethod("multiply", new Object[]{6, 7}, 3);
            callRpcMethod("divide", new Object[]{20, 4}, 4);

            // Exemple division par zéro pour tester les erreurs
            callRpcMethod("divide", new Object[]{5, 0}, 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Envoie une requête JSON-RPC au serveur et affiche la réponse.
     *
     * @param method Nom de la méthode RPC
     * @param params Paramètres de la méthode
     * @param id Identifiant unique de la requête
     */
    private static void callRpcMethod(String method, Object[] params, int id) throws Exception {
        // Créer l'objet JSON-RPC
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(new RpcRequest("2.0", method, params, id));

        // Connexion HTTP POST
        URL url = new URL(SERVER_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Envoyer la requête
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonRequest.getBytes(StandardCharsets.UTF_8));
        }

        // Lire la réponse
        InputStream is = conn.getInputStream();
        RpcResponse response = mapper.readValue(is, RpcResponse.class);

        // Affichage du résultat ou erreur
        if (response.error != null) {
            System.out.println("Erreur RPC [" + response.id + "]: " + response.error.message);
        } else {
            System.out.println("Résultat RPC [" + response.id + "]: " + response.result);
        }
    }

    // Classes internes pour sérialiser/désérialiser JSON-RPC
    static class RpcRequest {
        public String jsonrpc;
        public String method;
        public Object[] params;
        public int id;

        public RpcRequest() {}
        public RpcRequest(String jsonrpc, String method, Object[] params, int id) {
            this.jsonrpc = jsonrpc;
            this.method = method;
            this.params = params;
            this.id = id;
        }
    }

    static class RpcResponse {
        public String jsonrpc;
        public Object result;
        public RpcError error;
        public int id;

        static class RpcError {
            public int code;
            public String message;
        }
    }
}
