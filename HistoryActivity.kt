package com.example.galcx

/* Curso: Análise e Desenvolvimento de Sistemas
    Aluno: Saulo Torres de Oliveira Assis
    Professor: André Gustavo
    Projeto: GALC-X - A equação da eficiência
*/

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var listHistorico: ListView
    private lateinit var btnLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Histórico"

        listHistorico = findViewById(R.id.listViewHistorico)
        btnLimpar = findViewById(R.id.btnLimpar)

        val dadosEstatica = arrayListOf(
            RegistroGalc(5.60, 3.80, "Álcool"),
            RegistroGalc(6.10, 4.50, "Gasolina"),
            RegistroGalc(5.50, 3.90, "Gasolina"),
            RegistroGalc(5.80, 3.70, "Álcool")
        )

        val adaptador = ArrayAdapter(
            this,
            R.layout.item_lista,
            dadosEstatica
        )

        listHistorico.adapter = adaptador

        btnLimpar.setOnClickListener {
            if (dadosEstatica.isEmpty()) {
                Toast.makeText(this, "O histórico já está vazio", Toast.LENGTH_SHORT).show()
            } else {
                dadosEstatica.clear()
                adaptador.notifyDataSetChanged()
                Toast.makeText(this, "Histórico apagado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}