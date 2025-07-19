package com.mycompany.automatopilha.classes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author igorxf
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AutomatoComPilha {

    private String estadoInicial = "q0";
    private String estadoAtual;
    private List<Transicao> transicoes;
    private List<String> estadosFinais;
    private final Stack<Character> pilha;

    public AutomatoComPilha() {
        this.transicoes = new ArrayList<>();
        this.estadosFinais = new ArrayList<>();
        this.pilha = new Stack<>();
    }

    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public void setEstadosFinais(List<String> estadosFinais) {
        this.estadosFinais = estadosFinais;
    }

    private boolean pilhaCorresponde(char regraDesempilhar, Stack<Character> pilha) {
        if (regraDesempilhar == '-') {
            return true;
        }
        if (regraDesempilhar == '_') {
            return pilha.isEmpty();
        }
        if (pilha.isEmpty()) {
            return false;
        }
        return regraDesempilhar == pilha.peek();
    }

    private Transicao encontrarTransicao(String estado, char simboloEntrada, Stack<Character> pilha) {
        // Prioridade 1: Tenta encontrar uma transição que consome o símbolo da entrada.
        for (Transicao t : this.transicoes) {
            if (t.getEstadoAtual().equals(estado) && t.getSimboloEntrada() == simboloEntrada) {
                if (pilhaCorresponde(t.getSimboloDesempilhar(), pilha)) {
                    return t;
                }
            }
        }
        // Prioridade 2: Tenta encontrar uma transição épsilon (que não consome a entrada).
        for (Transicao t : this.transicoes) {
            if (t.getEstadoAtual().equals(estado) && t.getSimboloEntrada() == '-') {
                if (pilhaCorresponde(t.getSimboloDesempilhar(), pilha)) {
                    return t;
                }
            }
        }
        return null;
    }

    public boolean verificaSentenca(String palavra) {
        this.estadoAtual = this.estadoInicial;
        this.pilha.clear();
        int indicePalavra = 0;

        Transicao transicaoEncontrada;

        do {
            char simboloAtualEntrada;
            if (indicePalavra < palavra.length()) {
                simboloAtualEntrada = palavra.charAt(indicePalavra);
            } else {
                simboloAtualEntrada = '-';
            }

            transicaoEncontrada = encontrarTransicao(this.estadoAtual, simboloAtualEntrada, this.pilha);

            if (transicaoEncontrada != null) {
                this.estadoAtual = transicaoEncontrada.getEstadoDestino();

                char regraDesempilhar = transicaoEncontrada.getSimboloDesempilhar();
                if (regraDesempilhar != '-' && regraDesempilhar != '_') {
                    if (!this.pilha.isEmpty()) {
                        this.pilha.pop();
                    }
                }

                char simboloParaEmpilhar = transicaoEncontrada.getSimboloEmpilhar();
                if (simboloParaEmpilhar != '-') {
                    this.pilha.push(simboloParaEmpilhar);
                }

                if (transicaoEncontrada.getSimboloEntrada() != '-') {
                    indicePalavra++;
                }
            }

        } while (transicaoEncontrada != null);

        boolean palavraConsumida = (indicePalavra == palavra.length());
        boolean ehEstadoFinal = this.estadosFinais.contains(this.estadoAtual);
        boolean pilhaVazia = this.pilha.isEmpty();

        return palavraConsumida && ehEstadoFinal && pilhaVazia;
    }

}
