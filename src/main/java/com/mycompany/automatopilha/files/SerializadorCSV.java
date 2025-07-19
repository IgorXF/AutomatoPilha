/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatopilha.files;

import com.mycompany.automatopilha.classes.Transicao;
/**
 *
 * @author igorxf
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializadorCSV {
    private List<String> estadosFinais;
    private List<Transicao> transicoes;

    public SerializadorCSV() {
        this.estadosFinais = new ArrayList<>();
        this.transicoes = new ArrayList<>();
    }

    public void carregar(String filePath) throws IOException {
        FilePersistence fp = new FilePersistence();
        String conteudo = fp.loadFromFile(filePath);
        String[] linhas = conteudo.split("\\R");

        this.estadosFinais.clear();
        this.transicoes.clear();

        this.estadosFinais.addAll(Arrays.asList(linhas[0].split(",")));

        for (int i = 2; i < linhas.length; i++) {
            String[] partes = linhas[i].split(",");
            if (partes.length == 5) {
                Transicao t = new Transicao(
                    partes[0],
                    partes[1].charAt(0),
                    partes[2].charAt(0),
                    partes[3],
                    partes[4].charAt(0)
                );
                this.transicoes.add(t);
            }
        }
    }

    public List<String> getEstadosFinais() {
        return this.estadosFinais;
    }

    public List<Transicao> getTransicoes() {
        return this.transicoes;
    }
}