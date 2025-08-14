package br.com.thiagoDev.screenmatch;

import br.com.thiagoDev.screenmatch.model.DadosSerie;
import br.com.thiagoDev.screenmatch.services.ConsumoApi;
import br.com.thiagoDev.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    }
}
