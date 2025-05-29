package Principal.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorArquivo {
    public static void escreverArquivo(String caminhoArquivo, String conteudo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            bw.write(conteudo);
        }
    }
}