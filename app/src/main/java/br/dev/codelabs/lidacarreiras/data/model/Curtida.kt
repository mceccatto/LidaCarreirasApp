package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "curtidas", primaryKeys = ["id", "docId", "vagaId", "candidatoId"])
data class Curtida(
    var id: String,
    var docId: String,
    var vagaId: String,
    var candidatoId: String,
    var dataCadastro: String
)
{
    constructor():this(
        "",
        "",
        "",
        "",
        ""
    )
}