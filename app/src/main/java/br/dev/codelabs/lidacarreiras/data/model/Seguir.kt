package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "seguindo", primaryKeys = ["id", "docId", "empresaId", "candidatoId"])
data class Seguir(
    var id: String,
    var docId: String,
    var empresaId: String,
    var candidatoId: String,
    var dataCadastro: String
)
{
    constructor():this(
        "",
        "",
        "0",
        "",
        ""
    )
}