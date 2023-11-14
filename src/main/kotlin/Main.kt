
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

fun main() {

    val leitor = Scanner(System.`in`)
    println("Digite o código do jogo para buscar ou 'X' para sair:  ")

    while (true) {

        val busca = leitor.nextLine()

        if (busca == "X" || busca == "x"){
            println("Fechando o programa")
            break
        }

        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()
        println(json)

        val gson = Gson()
        try {
            val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

            val meuJogo = Jogo(
                meuInfoJogo.info.title,
                meuInfoJogo.info.thumb
            )

            println(meuJogo)
        } catch (ex: JsonSyntaxException) {
            println("Jogo Inexistente.tente outro id")
        }
        println("Se desejar continuar digite outro ID, se deseja sair aperte X no seu teclado")
    }
}
