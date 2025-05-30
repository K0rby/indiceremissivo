package Principal.estruturas;

import Principal.model.Palavra;
import java.util.function.Consumer;

public class TabelaHash {
    private static final int TAMANHO = 26;
    private ArvoreBinariaBusca[] tabela;

    public TabelaHash() {
        this.tabela = new ArvoreBinariaBusca[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
    }

    private int calcularHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) return 0;

        char primeiraLetra = Character.toLowerCase(palavra.charAt(0));

        // Mapeamento de letras acentuadas para não-acentuadas
        if (primeiraLetra == 'á' || primeiraLetra == 'à' || primeiraLetra == 'ã' || primeiraLetra == 'â') {
            primeiraLetra = 'a';
        } else if (primeiraLetra == 'é' || primeiraLetra == 'ê') {
            primeiraLetra = 'e';
        } else if (primeiraLetra == 'í') {
            primeiraLetra = 'i';
        } else if (primeiraLetra == 'ó' || primeiraLetra == 'ô' || primeiraLetra == 'õ') {
            primeiraLetra = 'o';
        } else if (primeiraLetra == 'ú' || primeiraLetra == 'ü') {
            primeiraLetra = 'u';
        } else if (primeiraLetra == 'ç') {
            primeiraLetra = 'c';
        }

        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        } else {
            return 0;
        }
    }

    public void inserir(Palavra palavra) {
        int indice = calcularHash(palavra.getTexto());
        tabela[indice].inserir(palavra);
    }

    public Palavra buscar(String texto) {
        int indice = calcularHash(texto);
        return tabela[indice].buscar(texto);
    }

    public void percorrerEmOrdem(Consumer<Palavra> consumer) {
        for (int i = 0; i < TAMANHO; i++) {
            tabela[i].percorrerEmOrdem(consumer);
        }
    }
}