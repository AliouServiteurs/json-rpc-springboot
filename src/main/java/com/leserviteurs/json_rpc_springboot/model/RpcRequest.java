package com.leserviteurs.json_rpc_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente une requête JSON-RPC standard.
 * 
 * Exemple JSON :
 * {
 *   "jsonrpc": "2.0",
 *   "method": "add",
 *   "params": [5, 3],
 *   "id": 1
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcRequest {
    private String jsonrpc;      // Version du protocole, ex: "2.0"
    private String method;       // Nom de la méthode RPC à appeler
    private Object[] params;     // Paramètres de la méthode
    private int id;              // Identifiant unique de la requête
}
