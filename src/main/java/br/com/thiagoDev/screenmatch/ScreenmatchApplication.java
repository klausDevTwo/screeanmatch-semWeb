package br.com.thiagoDev.screenmatch;

import br.com.thiagoDev.screenmatch.model.DadosEpisodio;
import br.com.thiagoDev.screenmatch.model.DadosSerie;
import br.com.thiagoDev.screenmatch.model.DadosTemporada;
import br.com.thiagoDev.screenmatch.services.ConsumoApi;
import br.com.thiagoDev.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=eedb05eb");
        System.out.println(json);

        ConverteDados conversor = new  ConverteDados();
        DadosSerie dados = conversor.obterdados(json,DadosSerie.class);
        System.out.println(dados);
        json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=eedb05eb");
        DadosEpisodio dadosEpisodio = conversor.obterdados(json,DadosEpisodio.class);
        System.out.println(dadosEpisodio);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++){
            json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=eedb05eb");
            DadosTemporada dadosTemporada = conversor.obterdados(json,DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
