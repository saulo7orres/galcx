package com.example.galcx

/* Curso: Análise e Desenvolvimento de Sistemas
    Aluno: Saulo Torres de Oliveira Assis
    Professor: André Gustavo
    Projeto: GALC-X - A equação da eficiência
*/

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gasolina: EditText
    private lateinit var alcool: EditText
    private lateinit var resultadoTexto: TextView
    private lateinit var bt: Button

    private var tempoUltimoClique: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt = findViewById(R.id.idCalcula)
        gasolina = findViewById(R.id.idValorGasolina)
        alcool = findViewById(R.id.idValorAlcool)
        resultadoTexto = findViewById(R.id.txtResultado)

        val limpadorDeTexto = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                resultadoTexto.text = ""
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        gasolina.addTextChangedListener(limpadorDeTexto)
        alcool.addTextChangedListener(limpadorDeTexto)

        bt.setOnClickListener {
            executarCalculo()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (System.currentTimeMillis() - tempoUltimoClique < 2000) {
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "Pressione voltar novamente para sair", Toast.LENGTH_SHORT).show()
                    tempoUltimoClique = System.currentTimeMillis()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        gasolina.text.clear()
        alcool.text.clear()
        resultadoTexto.text = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_historico) {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun executarCalculo() {
        val strGasolina = gasolina.text.toString()
        val strAlcool = alcool.text.toString()

        if (strGasolina.isEmpty() || strAlcool.isEmpty()) {
            Toast.makeText(this, "Informe os valores corretamente", Toast.LENGTH_SHORT).show()
            return
        }

        val valorGasolina = strGasolina.toDouble()
        val valorAlcool = strAlcool.toDouble()
        val indice = valorAlcool / valorGasolina

        if (indice <= 0.7) {
            resultadoTexto.text = "O Álcool é mais vantajoso!"
        } else {
            resultadoTexto.text = "A Gasolina é mais vantajosa!"
        }
    }
}