package Principal;

import Principal.estruturas.ListaEncadeada;
import Principal.estruturas.TabelaHash;
import Principal.model.Palavra;
import Principal.util.LeitorArquivo;
import Principal.util.EscritorArquivo;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndiceRemissivo {
    private TabelaHash tabelaHash;
    private ListaEncadeada<String> palavrasChave;

    public IndiceRemissivo() {
        this.tabelaHash = new TabelaHash();
        this.palavrasChave = new ListaEncadeada<>();
    }

    public void processarTexto(String caminhoArquivoTexto) throws IOException {
        ListaEncadeada<String> linhas = LeitorArquivo.lerArquivo(caminhoArquivoTexto);
        Pattern padraoPalavra = Pattern.compile("\\b[\\p{L}]+\\b");

        for (int numeroLinha = 0; numeroLinha < linhas.tamanho(); numeroLinha++) {
            String linha = linhas.obter(numeroLinha);
            Matcher matcher = padraoPalavra.matcher(linha.toLowerCase());
            while (matcher.find()) {
                String textoParavra = matcher.group();
                Palavra palavraExistente = tabelaHash.buscar(textoParavra);

                if (palavraExistente == null) {
                    Palavra novaPalavra = new Palavra(textoParavra);
                    novaPalavra.adicionarOcorrencia(numeroLinha + 1);
                    tabelaHash.inserir(novaPalavra);
                } else {
                    palavraExistente.adicionarOcorrencia(numeroLinha + 1);
                }
            }
        }
    }

    public void carregarPalavrasChave(String caminhoArquivoPalavrasChave) throws IOException {
        this.palavrasChave = LeitorArquivo.lerPalavrasChave(caminhoArquivoPalavrasChave);
    }

    public void gerarIndice(String caminhoArquivoSaida) throws IOException {
        StringBuilder indice = new StringBuilder();
        indice.append("ÍNDICE REMISSIVO\n");
        indice.append("================\n\n");
        for (String palavraChave : palavrasChave) {
            Palavra palavra = tabelaHash.buscar(palavraChave);
            if (palavra != null) {
                indice.append(palavra.toString()).append("\n");
            } else {
                indice.append(palavraChave).append(": Não encontrada no texto\n");
            }
        }
        EscritorArquivo.escreverArquivo(caminhoArquivoSaida, indice.toString());
    }
}