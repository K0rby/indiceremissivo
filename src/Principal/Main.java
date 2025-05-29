package Principal;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: java Main <arquivo_texto> <arquivo_palavras_chave> <arquivo_saida>");
            return;
        }
        String arquivoTexto = args[0];
        String arquivoPalavrasChave = args[1];
        String arquivoSaida = args[2];

        try {
            IndiceRemissivo indiceRemissivo = new IndiceRemissivo();
            System.out.println("Processando o texto...");
            indiceRemissivo.processarTexto(arquivoTexto);
            System.out.println("Carregando as palavras-chave...");
            indiceRemissivo.carregarPalavrasChave(arquivoPalavrasChave);
            System.out.println("Gerando o índice remissivo...");
            indiceRemissivo.gerarIndice(arquivoSaida);
            System.out.println("Índice remissivo gerado com sucesso em: " + arquivoSaida);
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}