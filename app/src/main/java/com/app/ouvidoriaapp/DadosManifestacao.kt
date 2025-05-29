package com.app.ouvidoriaapp

object DadosManifestacao {
    // Dados de identificação
    var nomeCompleto: String = ""
    var email: String = ""
    var telefone: String = ""
    var cpf: String = ""        // Nova propriedade
    var hygia: String = ""      // Nova propriedade

    // Dados de localização
    var estado: String = ""
    var municipio: String = ""
    var endereco: String = ""
    var numero: String = ""
    var bairro: String = ""
    var cidade: String = ""
    var cep: String = ""

    // Dados da manifestação
    var tipoManifestacao: String = ""
    var areaEnvolvida: String = ""
    var descricaoManifestacao: String = ""

    // Função para limpar todos os dados
    fun limpar() {
        nomeCompleto = ""
        email = ""
        telefone = ""
        cpf = ""
        hygia = ""
        estado = ""
        municipio = ""
        endereco = ""
        numero = ""
        bairro = ""
        cidade = ""
        cep = ""
        tipoManifestacao = ""
        areaEnvolvida = ""
        descricaoManifestacao = ""
    }
}