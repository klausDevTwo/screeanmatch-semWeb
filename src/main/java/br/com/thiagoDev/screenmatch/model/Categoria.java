package br.com.thiagoDev.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    FICCAO("Fiction"),
    AVENTURA("Adventure");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
