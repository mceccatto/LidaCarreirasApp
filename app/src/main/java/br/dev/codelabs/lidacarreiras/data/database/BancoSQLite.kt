package br.dev.codelabs.lidacarreiras.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.dev.codelabs.lidacarreiras.data.dao.CandidatoDao
import br.dev.codelabs.lidacarreiras.data.dao.CurtidaDao
import br.dev.codelabs.lidacarreiras.data.dao.EmpresaDao
import br.dev.codelabs.lidacarreiras.data.dao.ExperienciaDao
import br.dev.codelabs.lidacarreiras.data.dao.FormacaoDao
import br.dev.codelabs.lidacarreiras.data.dao.MensagemDao
import br.dev.codelabs.lidacarreiras.data.dao.SeguirDao
import br.dev.codelabs.lidacarreiras.data.dao.VagaDao
import br.dev.codelabs.lidacarreiras.data.model.Candidato
import br.dev.codelabs.lidacarreiras.data.model.Curtida
import br.dev.codelabs.lidacarreiras.data.model.Empresa
import br.dev.codelabs.lidacarreiras.data.model.Experiencia
import br.dev.codelabs.lidacarreiras.data.model.Formacao
import br.dev.codelabs.lidacarreiras.data.model.Mensagem
import br.dev.codelabs.lidacarreiras.data.model.Seguir
import br.dev.codelabs.lidacarreiras.data.model.Vaga
import kotlin.concurrent.Volatile

@Database(entities = [Candidato::class, Curtida::class, Empresa::class, Experiencia::class, Formacao::class, Mensagem::class, Seguir::class, Vaga::class], version = 1)
abstract class BancoSQLite : RoomDatabase() {
    abstract fun candidatoDao(): CandidatoDao
    abstract fun curtidaDao(): CurtidaDao
    abstract fun empresaDao(): EmpresaDao
    abstract fun experienciaDao(): ExperienciaDao
    abstract fun formacaoDao(): FormacaoDao
    abstract fun mensagemDao(): MensagemDao
    abstract fun seguirDao(): SeguirDao
    abstract fun vagaDao(): VagaDao
    companion object {
        @Volatile
        private var INSTANCE: BancoSQLite? = null
        fun get(context: Context): BancoSQLite {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, BancoSQLite::class.java, "banco_local.db").build()
                }
            }
            return INSTANCE!!
        }
    }
}