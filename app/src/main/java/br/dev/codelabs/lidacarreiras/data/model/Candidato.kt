package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "candidatos", primaryKeys = ["id", "docId"])
data class Candidato(
    var id: String,
    var docId: String,
    var cpf: String,
    var nome: String,
    var dataNascimento: String,
    var email: String,
    var senha: String,
    var imagem: String,
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
        "semfoto.jpg",
        ""
    )
}