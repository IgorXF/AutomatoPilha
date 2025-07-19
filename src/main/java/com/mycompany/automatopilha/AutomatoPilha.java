/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.automatopilha;

import com.mycompany.automatopilha.classes.AutomatoComPilha;
import com.mycompany.automatopilha.files.SerializadorCSV;
import java.io.IOException;

/**
 *
 * @author igorxf
 */
public class AutomatoPilha {
   public static void main(String[] args) {
        AutomatoComPilha automato = new AutomatoComPilha();

        try {
            SerializadorCSV serializador = new SerializadorCSV();
            serializador.carregar("automato.csv");
            automato.setEstadosFinais(serializador.getEstadosFinais());
            automato.setTransicoes(serializador.getTransicoes());
            System.out.println("Automato configurado com sucesso para a linguagem a^n b^n!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar e configurar o automato: " + e.getMessage());
            return;
        }

        System.out.println("\n--- Iniciando testes de sentenças ---\n");
        
        String[] palavrasParaTestar = {
            "ab",           // Deve aceitar
            "aabb",         // Deve aceitar
            "aaabbb",       // Deve aceitar
            "a",            // Deve rejeitar
            "b",            // Deve rejeitar
            "",             // Deve aceitar
            "aab",          // Deve rejeitar
            "abb",          // Deve rejeitar
            "ba",           // Deve rejeitar
            "baba"          // Deve rejeitar
        };

        for (String palavra : palavrasParaTestar) {
            if (automato.verificaSentenca(palavra)) {
                System.out.printf("A palavra " + palavra +" foi: ACEITA\n");
            } else {
                System.out.printf("A palavra " + palavra +" foi: REJEITADA\n");
            }
        }
    }
    
}