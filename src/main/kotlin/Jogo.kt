class Jogo (var titulo:String, var capa : String ) {
    val  descricao = ""

    override fun toString(): String {
        return "Meu Jogo:\n" +
                "Titulo: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao \n"
    }


}