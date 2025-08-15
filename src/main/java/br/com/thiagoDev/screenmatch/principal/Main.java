package br.com.thiagoDev.screenmatch.principal;

import br.com.thiagoDev.screenmatch.model.DadosEpisodio;
import br.com.thiagoDev.screenmatch.model.DadosSerie;
import br.com.thiagoDev.screenmatch.model.DadosTemporada;
import br.com.thiagoDev.screenmatch.services.ConsumoApi;
import br.com.thiagoDev.screenmatch.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=eedb05eb";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibirMenu() {
        System.out.println("---Menu---");
        System.out.println("Digite o nome da serie: ");
        var nomeSerie = sc.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterdados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterdados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);


        temporadas.forEach(t ->t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
