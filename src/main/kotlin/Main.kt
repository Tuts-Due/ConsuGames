
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

fun main() {

    val leitor = Scanner(System.`in`)
    println("Digite o código do jogo para buscar: ")

    val busca = leitor.nextLine()

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
    val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)



    val meuJogo = Jogo(
        meuInfoJogo.info.title,
        meuInfoJogo.info.thumb)

    println(meuJogo)
}