package Principal.estruturas;

import Principal.model.No;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEncadeada<T> implements Iterable<T> {
    private No<T> inicio;
    private int tamanho;

    public ListaEncadeada() {
        this.inicio = null;
        this.tamanho = 0;
    }

    public void adicionar(T valor) {
        No<T> novoNo = new No<>(valor);

        if (inicio == null) {
            inicio = novoNo;
        } else {
            No<T> atual = inicio;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novoNo);
        }

        tamanho++;
    }

    public boolean contem(T valor) {
        No<T> atual = inicio;

        while (atual != null) {
            if (atual.getValor().equals(valor)) {
                return true;
            }
            atual = atual.getProximo();
        }

        return false;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public T obter(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }

        No<T> atual = inicio;
        for (int i = 0; i < indice; i++) {
            atual = atual.getProximo();
        }

        return atual.getValor();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private No<T> atual = inicio;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = atual.getValor();
                atual = atual.getProximo();
                return valor;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        No<T> atual = inicio;
        boolean primeiro = true;
        while (atual != null) {
            if (!primeiro) {
                sb.append(", ");
            } else {
                primeiro = false;
            }
            sb.append(atual.getValor());
            atual = atual.getProximo();
        }
        return sb.toString();
    }
}