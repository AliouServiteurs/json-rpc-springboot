package com.leserviteurs.json_rpc_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.leserviteurs.json_rpc_springboot.exception.RpcException;
import com.leserviteurs.json_rpc_springboot.model.RpcRequest;
import com.leserviteurs.json_rpc_springboot.model.RpcResponse;
import com.leserviteurs.json_rpc_springboot.service.CalculatorService;

/**
 * Controller principal pour gérer toutes les requêtes JSON-RPC.
 * Expose l'endpoint POST : /jsonrpc
 */
@RestController
@RequestMapping("/jsonrpc")
public class JsonRpcController {

    @Autowired
    private CalculatorService calculatorService;

    /**
     * Endpoint principal JSON-RPC.
     * 
     * @param request La requête JSON-RPC
     * @return RpcResponse
     */

    @GetMapping
    public String getInfo() {
        return "JSON-RPC Server is running. Use POST /jsonrpc to send requests.";
    }
    
    @PostMapping
    public RpcResponse handleRpc(@RequestBody RpcRequest request) {
        RpcResponse response = new RpcResponse();
        response.setId(request.getId());
        response.setJsonrpc("2.0");

        try {
            double result;

            // Dispatch de la méthode RPC
            switch (request.getMethod()) {
                case "add":
                    result = calculatorService.add(
                        toDouble(request.getParams()[0]), 
                        toDouble(request.getParams()[1])
                    );
                    response.setResult(result);
                    break;
                case "subtract":
                    result = calculatorService.subtract(
                        toDouble(request.getParams()[0]), 
                        toDouble(request.getParams()[1])
                    );
                    response.setResult(result);
                    break;
                case "multiply":
                    result = calculatorService.multiply(
                        toDouble(request.getParams()[0]), 
                        toDouble(request.getParams()[1])
                    );
                    response.setResult(result);
                    break;
                case "divide":
                    result = calculatorService.divide(
                        toDouble(request.getParams()[0]), 
                        toDouble(request.getParams()[1])
                    );
                    response.setResult(result);
                    break;
                default:
                    throw new RpcException(-32601, "Méthode non trouvée");
            }

        } catch (RpcException e) {
            // Gestion des erreurs métier
            response.setError(new RpcResponse.RpcError(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            // Erreurs inattendues
            response.setError(new RpcResponse.RpcError(-32603, "Erreur interne du serveur"));
        }

        return response;
    }

    // Méthode utilitaire pour convertir un paramètre en double
    private double toDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        } else {
            throw new RpcException(-32602, "Paramètre invalide");
        }
    }
}