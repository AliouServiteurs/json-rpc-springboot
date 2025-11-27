package com.leserviteurs.json_rpc_springboot.service;

import org.springframework.stereotype.Service;

import com.leserviteurs.json_rpc_springboot.exception.RpcException;

/**
 * Service qui implémente les méthodes JSON-RPC de type calculatrice.
 */
@Service
public class CalculatorService {

    /**
     * Additionne deux nombres.
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Soustrait deux nombres.
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplie deux nombres.
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divise deux nombres.
     * Lance une RpcException si division par zéro.
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new RpcException(-32000, "Division par zéro interdite");
        }
        return a / b;
    }
}
