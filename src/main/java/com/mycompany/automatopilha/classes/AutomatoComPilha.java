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

    // Verifica se o topo da pilha corresponde ao símbolo que a transição deseja desempilhar
    private boolean pilhaCorresponde(char regraDesempilhar, Stack<Character> pilha) {
        if (regraDesempilhar == '-') {
            // '-' indica que não é necessário desempilhar (ignora o conteúdo da pilha)
            return true;
        }
        if (regraDesempilhar == '_') {
            // '_' indica que essa transição SÓ é válida se a pilha estiver completamente vazia.
            // Isso é útil, por exemplo, para exigir que a pilha esteja vazia antes de ir para um estado final.
            return pilha.isEmpty();
        }
        if (pilha.isEmpty()) {
            // Se a pilha está vazia e a regra exige um símbolo específico, a transição não é válida
            return false;
        }
        // Verifica se o topo da pilha corresponde exatamente ao símbolo exigido pela transição
        return regraDesempilhar == pilha.peek();
    }

    // Encontra uma transição válida a partir do estado atual, símbolo da entrada e topo da pilha
    private Transicao encontrarTransicao(String estado, char simboloEntrada, Stack<Character> pilha) {
        // Primeira prioridade: transições que consomem símbolo da palavra
        for (Transicao t : this.transicoes) {
            if (t.getEstadoAtual().equals(estado) && t.getSimboloEntrada() == simboloEntrada) {
                if (pilhaCorresponde(t.getSimboloDesempilhar(), pilha)) {
                    return t;
                }
            }
        }
        // Segunda prioridade: transições épsilon (entrada '-')
        for (Transicao t : this.transicoes) {
            if (t.getEstadoAtual().equals(estado) && t.getSimboloEntrada() == '-') {
                if (pilhaCorresponde(t.getSimboloDesempilhar(), pilha)) {
                    return t;
                }
            }
        }
        // Nenhuma transição encontrada
        return null;
    }

    // Função principal que verifica se uma palavra é aceita pelo autômato com pilha
    public boolean verificaSentenca(String palavra) {
        this.estadoAtual = this.estadoInicial; // Começa sempre no estado inicial "q0"
        this.pilha.clear(); // Limpa a pilha para garantir que está vazia no início
        int indicePalavra = 0; // Índice que controla qual letra da palavra está sendo lida

        Transicao transicaoEncontrada;

        // Executa enquanto encontrar transições válidas
        do {
            char simboloAtualEntrada;
            
            // Se ainda não terminou de ler a palavra, pega o símbolo atual dela
            if (indicePalavra < palavra.length()) {
                simboloAtualEntrada = palavra.charAt(indicePalavra);
            } else {
                // Se a palavra terminou, tentamos transições "épsilon" (sem consumir letra)
                simboloAtualEntrada = '-';
            }

            // Busca uma transição válida a partir do estado atual, símbolo de entrada e topo da pilha
            transicaoEncontrada = encontrarTransicao(this.estadoAtual, simboloAtualEntrada, this.pilha);

            if (transicaoEncontrada != null) {
                // Atualiza o estado atual para o destino da transição encontrada
                this.estadoAtual = transicaoEncontrada.getEstadoDestino();

                // Verifica se precisa desempilhar algo
                char regraDesempilhar = transicaoEncontrada.getSimboloDesempilhar();
                if (regraDesempilhar != '-' && regraDesempilhar != '_') {
                    if (!this.pilha.isEmpty()) {
                        this.pilha.pop(); // Desempilha o topo se for necessário
                    }
                }

                // Verifica se precisa empilhar algum símbolo
                char simboloParaEmpilhar = transicaoEncontrada.getSimboloEmpilhar();
                if (simboloParaEmpilhar != '-') {
                    this.pilha.push(simboloParaEmpilhar); // Empilha o símbolo
                }

                // Se a transição consumiu um símbolo da palavra, avança o índice
                if (transicaoEncontrada.getSimboloEntrada() != '-') {
                    indicePalavra++;
                }
            }

        } while (transicaoEncontrada != null); // Enquanto houver transições válidas, continua processando

        // Após processar, verifica se a palavra foi totalmente lida,
        // o estado final é aceitável e a pilha está vazia
        boolean palavraConsumida = (indicePalavra == palavra.length());
        boolean ehEstadoFinal = this.estadosFinais.contains(this.estadoAtual);
        boolean pilhaVazia = this.pilha.isEmpty();

        // A palavra é aceita se todas as condições forem verdadeiras
        return palavraConsumida && ehEstadoFinal && pilhaVazia;
    }
}
