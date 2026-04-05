package com.example.galcx

/* Curso: Análise e Desenvolvimento de Sistemas
    Aluno: Saulo Torres de Oliveira Assis
    Professor: André Gustavo
    Projeto: GALC-X - A equação da eficiência
*/

class RegistroGalc(var gasolina: Double, var alcool: Double, var vantajoso: String) {
    override fun toString(): String {
        return "Gasolina: $gasolina | Álcool: $alcool\n✓ Vantagem: $vantajoso"
    }
}