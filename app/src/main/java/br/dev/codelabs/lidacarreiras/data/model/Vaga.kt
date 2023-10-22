package br.dev.codelabs.lidacarreiras.data.model

import androidx.room.Entity

@Entity(tableName = "vagas", primaryKeys = ["id", "docId", "empresaId"])
data class Vaga(
    var id: String,
    var docId: String,
    var empresaId: String,
    var titulo: String,
    var descricao: String,
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
        "semfoto.jpg",
        ""
    )
}