import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {

        // fazer uma conexão HTTP e bucar os top 250 filmes
        ExtratorDeConteudo extrator = new ExtratorIMDB();
        String url = "https://raphael-linguagens.fly.dev/linguagens";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Titulo: " + conteudo.getTitulo());
            System.out.println();
        }

    }

}
