package com.app.ouvidoriaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ManifestacaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manifestacao)

        val etManifestacao = findViewById<EditText>(R.id.et_manifestacao)
        val btnFinalizar = findViewById<Button>(R.id.btn_finalizar)

        btnFinalizar.setOnClickListener {
            val descricao = etManifestacao.text.toString().trim()

            if (descricao.isEmpty()) {
                Toast.makeText(this, "Por favor, descreva sua manifestação.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            DadosManifestacao.descricaoManifestacao = descricao

            val intent = Intent(this, ComprovanteActivity::class.java)
            startActivity(intent)
        }
    }
}
