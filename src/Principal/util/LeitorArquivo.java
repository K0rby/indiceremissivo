package Principal.util;

import Principal.estruturas.ListaEncadeada;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {
    public static ListaEncadeada<String> lerArquivo(String caminhoArquivo) throws IOException {
        ListaEncadeada<String> linhas = new ListaEncadeada<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.adicionar(linha);
            }
        }
        return linhas;
    }

    public static ListaEncadeada<String> lerPalavrasChave(String caminhoArquivo) throws IOException {
        ListaEncadeada<String> palavrasChave = new ListaEncadeada<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.trim().split("\\s+");
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        palavrasChave.adicionar(palavra.toLowerCase());
                    }
                }
            }
        }
        return palavrasChave;
    }
}