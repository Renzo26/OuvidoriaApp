package com.app.ouvidoriaapp

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.github.gcacace.signaturepad.views.SignaturePad
import java.io.File
import java.io.FileOutputStream

class ComprovanteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprovante)

        val resumo = findViewById<TextView>(R.id.tv_resumo)
        val signaturePad = findViewById<SignaturePad>(R.id.signature_pad)
        val btnGerar = findViewById<Button>(R.id.btn_gerar_pdf)

        val texto = """
            Tipo de manifestação: ${DadosManifestacao.tipoManifestacao}
            Área envolvida: ${DadosManifestacao.areaEnvolvida}
            Estado: ${DadosManifestacao.estado}
            Município: ${DadosManifestacao.municipio}

            Nome: ${DadosManifestacao.nomeCompleto}
            Email: ${DadosManifestacao.email}
            Telefone: ${DadosManifestacao.telefone}
            CPF: ${DadosManifestacao.cpf}
            Hygia: ${DadosManifestacao.hygia}

            Endereço: ${DadosManifestacao.endereco}, ${DadosManifestacao.numero}, ${DadosManifestacao.bairro}
            Cidade: ${DadosManifestacao.cidade} - CEP: ${DadosManifestacao.cep}

            Manifestação:
            ${DadosManifestacao.descricaoManifestacao}
        """.trimIndent()

        resumo.text = texto

        btnGerar.setOnClickListener {
            val assinatura = signaturePad.signatureBitmap

            if (signaturePad.isEmpty) {
                Toast.makeText(this, "Por favor, assine antes de gerar o PDF.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            gerarPdf(texto, assinatura)
        }
    }

    private fun gerarPdf(texto: String, assinatura: Bitmap) {
        val document = android.graphics.pdf.PdfDocument()
        val pageInfo = android.graphics.pdf.PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 12f

        val lines = texto.split("\n")
        var y = 30f
        for (line in lines) {
            canvas.drawText(line, 20f, y, paint)
            y += 20f
        }

        val scaled = Bitmap.createScaledBitmap(assinatura, 200, 100, false)
        canvas.drawText("Assinatura:", 20f, y + 20f, paint)
        canvas.drawBitmap(scaled, 120f, y + 10f, paint)

        document.finishPage(page)

        val dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(dir, "comprovante_manifestacao.pdf")

        try {
            document.writeTo(FileOutputStream(file))
            Toast.makeText(this, "PDF gerado!", Toast.LENGTH_SHORT).show()
            abrirOuCompartilharPdf(file)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erro ao gerar PDF: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            document.close()
        }
    }

    private fun abrirOuCompartilharPdf(file: File) {
        val uri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val chooser = Intent.createChooser(intent, "Compartilhar PDF via")
        startActivity(chooser)
    }
}