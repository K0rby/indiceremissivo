package Principal.estruturas;

import Principal.model.Palavra;
import java.util.function.Consumer;

public class ArvoreBinariaBusca {
    private NoArvore raiz;

    private class NoArvore {
        Palavra palavra;
        NoArvore esquerda;
        NoArvore direita;

        public NoArvore(Palavra palavra) {
            this.palavra = palavra;
            this.esquerda = null;
            this.direita = null;
        }
    }

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(Palavra palavra) {
        raiz = inserirRecursivo(raiz, palavra);
    }

    private NoArvore inserirRecursivo(NoArvore no, Palavra palavra) {
        if (no == null) {
            return new NoArvore(palavra);
        }

        int comparacao = palavra.getTexto().compareTo(no.palavra.getTexto());

        if (comparacao < 0) {
            no.esquerda = inserirRecursivo(no.esquerda, palavra);
        } else if (comparacao > 0) {
            no.direita = inserirRecursivo(no.direita, palavra);
        }

        return no;
    }

    public Palavra buscar(String texto) {
        return buscarRecursivo(raiz, texto);
    }

    private Palavra buscarRecursivo(NoArvore no, String texto) {
        if (no == null) {
            return null;
        }

        int comparacao = texto.compareTo(no.palavra.getTexto());
        if (comparacao == 0) {
            return no.palavra;
        } else if (comparacao < 0) {
            return buscarRecursivo(no.esquerda, texto);
        } else {
            return buscarRecursivo(no.direita, texto);
        }
    }

    public void percorrerEmOrdem(Consumer<Palavra> consumer) {
        percorrerEmOrdemRecursivo(raiz, consumer);
    }

    private void percorrerEmOrdemRecursivo(NoArvore no, Consumer<Palavra> consumer) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.esquerda, consumer);
            consumer.accept(no.palavra);
            percorrerEmOrdemRecursivo(no.direita, consumer);
        }
    }

    public boolean estaVazia() {
        return raiz == null;
    }
}