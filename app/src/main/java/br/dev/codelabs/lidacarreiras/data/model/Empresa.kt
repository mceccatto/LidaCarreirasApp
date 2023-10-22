package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empresas", primaryKeys = ["id", "docId"])
data class Empresa(
    var id: String,
    var docId: String,
    var cnpj: String,
    var rasaoSocial: String,
    var nomeFantasia: String,
    var telefone: String,
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
        "",
        "semfoto.jpg",
        ""
    )
}