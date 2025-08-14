package br.com.thiagoDev.screenmatch.services;

public interface IconverteDados {
    <T> T obterdados(String json, Class<T> classe);
}
