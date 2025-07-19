/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatopilha.files;

/**
 *
 * @author igorxf
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FilePersistence {

    public void saveToFile(String texto, String filePath) throws IOException {
        try (FileWriter arq = new FileWriter(filePath);
             PrintWriter gravarArq = new PrintWriter(arq)) {
            gravarArq.print(texto);
        }
    }

    public String loadFromFile(String filePath) throws IOException {
        StringBuilder conteudoLido = new StringBuilder();
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("\\Z");
            if (scanner.hasNext()) {
                conteudoLido.append(scanner.next());
            }
        }
        return conteudoLido.toString();
    }
}