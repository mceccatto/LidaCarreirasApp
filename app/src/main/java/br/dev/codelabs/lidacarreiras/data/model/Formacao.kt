package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "formacoes", primaryKeys = ["id", "docId", "candidatoId"])
data class Formacao(
    var id: String,
    var docId: String,
    var candidatoId: String,
    var instituicao: String,
    var curso: String,
    var dataInicio: String,
    var dataConclusao: String,
    var dataCadastro: String
)
{
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}