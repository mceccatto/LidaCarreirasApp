package br.dev.codelabs.lidacarreiras

import android.app.Application
import android.content.Context
import br.dev.codelabs.lidacarreiras.data.*
import br.dev.codelabs.lidacarreiras.data.dao.CandidatoDao
import br.dev.codelabs.lidacarreiras.data.dao.CertificadoDao
import br.dev.codelabs.lidacarreiras.data.dao.CurtidaDao
import br.dev.codelabs.lidacarreiras.data.dao.EmpresaDao
import br.dev.codelabs.lidacarreiras.data.dao.ExperienciaDao
import br.dev.codelabs.lidacarreiras.data.dao.FormacaoDao
import br.dev.codelabs.lidacarreiras.data.dao.MensagemDao
import br.dev.codelabs.lidacarreiras.data.dao.SeguirDao
import br.dev.codelabs.lidacarreiras.data.dao.VagaDao
import br.dev.codelabs.lidacarreiras.data.database.BancoSQLite
import br.dev.codelabs.lidacarreiras.data.repository.CandidatoRepository
import br.dev.codelabs.lidacarreiras.data.repository.CandidatoRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.CandidatoRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.CertificadoRepository
import br.dev.codelabs.lidacarreiras.data.repository.CertificadoRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.CertificadoRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.CurtidaRepository
import br.dev.codelabs.lidacarreiras.data.repository.CurtidaRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.CurtidaRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.EmpresaRepository
import br.dev.codelabs.lidacarreiras.data.repository.EmpresaRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.EmpresaRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.ExperienciaRepository
import br.dev.codelabs.lidacarreiras.data.repository.ExperienciaRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.ExperienciaRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.FormacaoRepository
import br.dev.codelabs.lidacarreiras.data.repository.FormacaoRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.FormacaoRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.MensagemRepository
import br.dev.codelabs.lidacarreiras.data.repository.MensagemRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.MensagemRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.SeguirRepository
import br.dev.codelabs.lidacarreiras.data.repository.SeguirRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.SeguirRepositorySQLite
import br.dev.codelabs.lidacarreiras.data.repository.VagaRepository
import br.dev.codelabs.lidacarreiras.data.repository.VagaRepositoryFirebase
import br.dev.codelabs.lidacarreiras.data.repository.VagaRepositorySQLite
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class LidaCarreirasApp : Application() {
    @Provides
    fun provideCandidatoReposity(candidatoDao: CandidatoDao) : CandidatoRepositorySQLite {
        return CandidatoRepositorySQLite(candidatoDao)
    }

    @Provides
    fun provideCandidatoDao(banco: BancoSQLite) : CandidatoDao {
        return banco.candidatoDao()
    }

    @Provides
    @Singleton
    fun provideCandidatoRepositoryFirebase(@Named("candidatos") candidatosRef: CollectionReference) : CandidatoRepository {
        return CandidatoRepositoryFirebase(candidatosRef)
    }

    @Provides
    @Singleton
    @Named("candidatos")
    fun provideCandidatosRef() : CollectionReference {
        return Firebase.firestore.collection("candidatos")
    }








    @Provides
    fun provideCertificadoReposity(certificadoDao: CertificadoDao) : CertificadoRepositorySQLite {
        return CertificadoRepositorySQLite(certificadoDao)
    }

    @Provides
    fun provideCertificadoDao(banco: BancoSQLite) : CertificadoDao {
        return banco.certificadoDao()
    }

    @Provides
    @Singleton
    fun provideCertificadoRepositoryFirebase(@Named("certificados") certificadosRef: CollectionReference) : CertificadoRepository {
        return CertificadoRepositoryFirebase(certificadosRef)
    }

    @Provides
    @Singleton
    @Named("certificados")
    fun provideCertificadosRef() : CollectionReference {
        return Firebase.firestore.collection("certificados")
    }







    @Provides
    fun provideCurtidaReposity(curtidaDao: CurtidaDao) : CurtidaRepositorySQLite {
        return CurtidaRepositorySQLite(curtidaDao)
    }

    @Provides
    fun provideCurtidaDao(banco: BancoSQLite) : CurtidaDao {
        return banco.curtidaDao()
    }

    @Provides
    @Singleton
    fun provideCurtidaRepositoryFirebase(@Named("curtidas") curtidasRef: CollectionReference) : CurtidaRepository {
        return CurtidaRepositoryFirebase(curtidasRef)
    }

    @Provides
    @Singleton
    @Named("curtidas")
    fun provideCurtidasRef() : CollectionReference {
        return Firebase.firestore.collection("curtidas")
    }

    @Provides
    fun provideEmpresaReposity(empresaDao: EmpresaDao) : EmpresaRepositorySQLite {
        return EmpresaRepositorySQLite(empresaDao)
    }

    @Provides
    fun provideEmpresaDao(banco: BancoSQLite) : EmpresaDao {
        return banco.empresaDao()
    }

    @Provides
    @Singleton
    fun provideEmpresaRepositoryFirebase(@Named("empresas") empresasRef: CollectionReference) : EmpresaRepository {
        return EmpresaRepositoryFirebase(empresasRef)
    }

    @Provides
    @Singleton
    @Named("empresas")
    fun provideEmpresasRef() : CollectionReference {
        return Firebase.firestore.collection("empresas")
    }

    @Provides
    fun provideExperienciaReposity(experienciaDao: ExperienciaDao) : ExperienciaRepositorySQLite {
        return ExperienciaRepositorySQLite(experienciaDao)
    }

    @Provides
    fun provideExperienciaDao(banco: BancoSQLite) : ExperienciaDao {
        return banco.experienciaDao()
    }

    @Provides
    @Singleton
    fun provideExperienciaRepositoryFirebase(@Named("experiencias") experienciasRef: CollectionReference) : ExperienciaRepository {
        return ExperienciaRepositoryFirebase(experienciasRef)
    }

    @Provides
    @Singleton
    @Named("experiencias")
    fun provideExperienciasRef() : CollectionReference {
        return Firebase.firestore.collection("experiencias")
    }

    @Provides
    fun provideFormacaoReposity(formacaoDao: FormacaoDao) : FormacaoRepositorySQLite {
        return FormacaoRepositorySQLite(formacaoDao)
    }

    @Provides
    fun provideFormacaoDao(banco: BancoSQLite) : FormacaoDao {
        return banco.formacaoDao()
    }

    @Provides
    @Singleton
    fun provideFormacaoRepositoryFirebase(@Named("formacoes") formacoesRef: CollectionReference) : FormacaoRepository {
        return FormacaoRepositoryFirebase(formacoesRef)
    }

    @Provides
    @Singleton
    @Named("formacoes")
    fun provideFormacoesRef() : CollectionReference {
        return Firebase.firestore.collection("formacoes")
    }

    @Provides
    fun provideMensagemReposity(mensagemDao: MensagemDao) : MensagemRepositorySQLite {
        return MensagemRepositorySQLite(mensagemDao)
    }

    @Provides
    fun provideMensagemDao(banco: BancoSQLite) : MensagemDao {
        return banco.mensagemDao()
    }

    @Provides
    @Singleton
    fun provideMensagemRepositoryFirebase(@Named("mensagens") mensagensRef: CollectionReference) : MensagemRepository {
        return MensagemRepositoryFirebase(mensagensRef)
    }

    @Provides
    @Singleton
    @Named("mensagens")
    fun provideMensagensRef() : CollectionReference {
        return Firebase.firestore.collection("mensagens")
    }

    @Provides
    fun provideSeguirReposity(seguirDao: SeguirDao) : SeguirRepositorySQLite {
        return SeguirRepositorySQLite(seguirDao)
    }

    @Provides
    fun provideSeguirDao(banco: BancoSQLite) : SeguirDao {
        return banco.seguirDao()
    }

    @Provides
    @Singleton
    fun provideSeguirRepositoryFirebase(@Named("seguindo") seguindoRef: CollectionReference) : SeguirRepository {
        return SeguirRepositoryFirebase(seguindoRef)
    }

    @Provides
    @Singleton
    @Named("seguindo")
    fun provideSeguindoRef() : CollectionReference {
        return Firebase.firestore.collection("seguindo")
    }

    @Provides
    fun provideVagaReposity(vagaDao: VagaDao) : VagaRepositorySQLite {
        return VagaRepositorySQLite(vagaDao)
    }

    @Provides
    fun provideVagaDao(banco: BancoSQLite) : VagaDao {
        return banco.vagaDao()
    }

    @Provides
    @Singleton
    fun provideVagaRepositoryFirebase(@Named("vagas") vagasRef: CollectionReference) : VagaRepository {
        return VagaRepositoryFirebase(vagasRef)
    }

    @Provides
    @Singleton
    @Named("vagas")
    fun provideVagasRef() : CollectionReference {
        return Firebase.firestore.collection("vagas")
    }

    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx: Context) : BancoSQLite {
        return BancoSQLite.get(ctx)
    }
}