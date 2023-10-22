package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "experiencias", primaryKeys = ["id", "docId", "candidatoId"])
data class Experiencia(
    var id: String,
    var docId: String,
    var candidatoId: String,
    var empresa: String,
    var cargo: String,
    var atribuicoes: String,
    var dataContratacao: String,
    var dataDesligamento: String,
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
        "",
        ""
    )
}