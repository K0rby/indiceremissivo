package Principal.model;

import Principal.estruturas.ListaEncadeada;

public class Palavra implements Comparable<Palavra> {
    private String texto;
    private ListaEncadeada<Integer> ocorrencias;

    public Palavra(String texto) {
        this.texto = texto.toLowerCase();
        this.ocorrencias = new ListaEncadeada<>();
    }

    public void adicionarOcorrencia(int linha) {
        if (!ocorrencias.contem(linha)) {
            ocorrencias.adicionar(linha);
        }
    }

    public String getTexto() {
        return texto;
    }

    public ListaEncadeada<Integer> getOcorrencias() {
        return ocorrencias;
    }

    @Override
    public int compareTo(Palavra outra) {
        return this.texto.compareTo(outra.getTexto());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Palavra palavra = (Palavra) obj;
        return texto.equals(palavra.texto);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(texto).append(": ");
        sb.append(ocorrencias.toString());
        return sb.toString();
    }
}