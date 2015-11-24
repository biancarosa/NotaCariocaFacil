package io.github.notacariocafacil.gerador

import io.github.notacariocafacil.Empresa
import io.github.notacariocafacil.NotaCarioca

interface Gerador {

	def geraLote(List<NotaCarioca> notas, Empresa empresa)
}