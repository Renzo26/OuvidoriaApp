package com.app.ouvidoriaapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rgTipo = findViewById<RadioGroup>(R.id.rg_tipo_manifestacao)
        val etArea = findViewById<EditText>(R.id.et_area_envolvida)
        val spEstado = findViewById<Spinner>(R.id.sp_estado)
        val spMunicipio = findViewById<Spinner>(R.id.sp_municipio)
        val btnProximo = findViewById<Button>(R.id.btn_proximo)

        // Dados fictícios para os Spinners
        val estados = arrayOf("SP", "RJ", "MG")
        val municipios = arrayOf("São Paulo", "Santo André", "Campinas", "São Bernardo do Campo")

        spEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estados)
        spMunicipio.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, municipios)

        btnProximo.setOnClickListener {
            val tipoId = rgTipo.checkedRadioButtonId
            if (tipoId == -1 || etArea.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tipoSelecionado = findViewById<RadioButton>(tipoId).text.toString()
            val area = etArea.text.toString().trim()
            val estado = spEstado.selectedItem.toString()
            val municipio = spMunicipio.selectedItem.toString()

            // Salvando nos dados temporários (como já fazia)
            DadosManifestacao.tipoManifestacao = tipoSelecionado
            DadosManifestacao.areaEnvolvida = area
            DadosManifestacao.estado = estado
            DadosManifestacao.municipio = municipio

            // 🔥 Salvando no Firebase Firestore
            val db = Firebase.firestore
            val dados = hashMapOf(
                "tipo_manifestacao" to tipoSelecionado,
                "area_envolvida" to area,
                "estado" to estado,
                "municipio" to municipio
            )

            db.collection("manifestacoes")
                .add(dados)
                .addOnSuccessListener {
                    Toast.makeText(this, "Informações salvas no Firebase!", Toast.LENGTH_SHORT).show()

                    // Indo para a próxima tela após salvar
                    val intent = Intent(this, IdentificacaoActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao salvar no Firebase: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}
