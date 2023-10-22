package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "mensagens", primaryKeys = ["id", "docId", "empresaId", "candidatoId"])
data class Mensagem(
    var id: String,
    var docId: String,
    var empresaId: String,
    var candidatoId: String,
    var conteudo: String,
    var dataCadastro: String
)
{
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        ""
    )
}