package com.leserviteurs.json_rpc_springboot.exception;

/**
 * Exception spécifique pour les erreurs JSON-RPC.
 * Permet de gérer les erreurs métier (ex: division par zéro) ou méthode inconnue.
 */
public class RpcException extends RuntimeException {

    private int code;

    public RpcException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
