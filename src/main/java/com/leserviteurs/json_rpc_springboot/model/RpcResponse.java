package com.leserviteurs.json_rpc_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente une réponse JSON-RPC standard.
 * Contient soit un résultat, soit une erreur.
 *
 * Exemple JSON-RPC succès :
 * {
 *   "jsonrpc": "2.0",
 *   "result": 8,
 *   "id": 1
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse {
    private String jsonrpc = "2.0";
    private Object result;       // Résultat de la méthode RPC
    private RpcError error;      // Objet erreur (null si succès)
    private int id;              // Identifiant de la requête correspondante

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RpcError {
        private int code;        // Code d’erreur standard JSON-RPC
        private String message;  // Message descriptif de l’erreur
    }
}
