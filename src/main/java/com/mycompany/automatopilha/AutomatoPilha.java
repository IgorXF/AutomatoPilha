package com.mycompany.automatopilha;

import com.mycompany.automatopilha.classes.AutomatoComPilha;
import com.mycompany.automatopilha.files.SerializadorCSV;
import java.io.IOException;
import java.util.Scanner;

public class AutomatoPilha {
    public static void main(String[] args) {
        AutomatoComPilha automato = new AutomatoComPilha();

        try {
            
            //o automato.csv é o anbn
            //o automato1.csv é o anbkcn-k
            //o automato2.csv é o anbkcn+k
            SerializadorCSV serializador = new SerializadorCSV();
            serializador.carregar("automato.csv");
            automato.setEstadosFinais(serializador.getEstadosFinais());
            automato.setTransicoes(serializador.getTransicoes());
            System.out.println("Autômato configurado com sucesso para a linguagem a^n b^n!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar e configurar o autômato: " + e.getMessage());
            return;
        }

        System.out.println("\n--- Teste interativo de sentenças ---");
        System.out.println("Digite uma palavra para testar ou digite 'sair' para encerrar.");

        Scanner scanner = new Scanner(System.in);
        String entrada;

        while (true) {
            System.out.print("Palavra: ");
            entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando testes.");
                break;
            }

            boolean resultado = automato.verificaSentenca(entrada);
            if (resultado) {
                System.out.println("A palavra \"" + entrada + "\" foi: ACEITA\n");
            } else {
                System.out.println("A palavra \"" + entrada + "\" foi: REJEITADA\n");
            }
        }

        scanner.close();
    }
}
