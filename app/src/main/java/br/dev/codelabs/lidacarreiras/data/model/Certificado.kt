package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "certificados", primaryKeys = ["id", "docId", "candidatoId"])
data class Certificado(
    var id: String,
    var docId: String,
    var candidatoId: String,
    var instituicao: String,
    var titulo: String,
    var arquivo: String,
    var dataCadastro: String
)
{
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        "semfoto.jpg",
        "",
    )
}