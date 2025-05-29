package com.app.ouvidoriaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EnderecoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endereco)

        val etEndereco = findViewById<EditText>(R.id.et_endereco)
        val etNumero = findViewById<EditText>(R.id.et_numero)
        val etBairro = findViewById<EditText>(R.id.et_bairro)
        val etCidade = findViewById<EditText>(R.id.et_cidade)
        val etCep = findViewById<EditText>(R.id.et_cep)
        val btnProximo = findViewById<Button>(R.id.btn_proximo_endereco)

        btnProximo.setOnClickListener {
            val endereco = etEndereco.text.toString().trim()
            val numero = etNumero.text.toString().trim()
            val bairro = etBairro.text.toString().trim()
            val cidade = etCidade.text.toString().trim()
            val cep = etCep.text.toString().trim()

            if (endereco.isEmpty() || numero.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || cep.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            DadosManifestacao.endereco = endereco
            DadosManifestacao.numero = numero
            DadosManifestacao.bairro = bairro
            DadosManifestacao.cidade = cidade
            DadosManifestacao.cep = cep

            val intent = Intent(this, ManifestacaoActivity::class.java)
            startActivity(intent)
        }
    }
}
