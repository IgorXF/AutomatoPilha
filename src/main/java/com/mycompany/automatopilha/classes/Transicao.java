/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatopilha.classes;

/**
 *
 * @author igorxf
 */
public class Transicao {
    private final String estadoAtual;
    private final char simboloEntrada;
    private final char simboloDesempilhar;
    private final String estadoDestino;
    private final char simboloEmpilhar;

    public Transicao(String estadoAtual, char simboloEntrada, char simboloDesempilhar, String estadoDestino, char simboloEmpilhar) {
        this.estadoAtual = estadoAtual;
        this.simboloEntrada = simboloEntrada;
        this.simboloDesempilhar = simboloDesempilhar;
        this.estadoDestino = estadoDestino;
        this.simboloEmpilhar = simboloEmpilhar;
    }

    public String getEstadoAtual() { 
        return estadoAtual; 
    }
    public char getSimboloEntrada() { 
        return simboloEntrada; 
    }
    public char getSimboloDesempilhar() { 
        return simboloDesempilhar; 
    }
    public String getEstadoDestino() { 
        return estadoDestino; 
    }
    public char getSimboloEmpilhar() { 
        return simboloEmpilhar; 
    }
}
