package io.github.notacariocafacil.gerador.impl

import io.github.notacariocafacil.Empresa
import io.github.notacariocafacil.NotaCarioca
import io.github.notacariocafacil.gerador.Gerador;

class GeradorXML implements Gerador {

	def geraLote(List<NotaCarioca> notas, Empresa empresa) {
		def markupBuilder = new groovy.xml.StreamingMarkupBuilder()
		def id = new Date().getTime()
		def xml = markupBuilder.bind { builder ->
			"EnviarLoteRpsEnvio" (xmlns: "http://www.abrasf.org.br/ABRASF/arquivos/nfse.xsd") {
				"LoteRps"("Id": id) {
					"NumeroLote"(id)
					"Cnpj"(empresa.cnpj)
					"InscricaoMunicipal"(empresa.inscricaoMunicipal)
					"QuantidadeRps"(notas.size())
					"ListaRPS" {
						for (nota in notas) {
							geraRPS(builder, nota)
						}
					}
				}
			}
		}
	}

	def geraRPS = { builder, nota ->
		builder."RPS" {
			"InfRps"("Id", nota.dados.codigoRps) {
				"IdentificacaoRps" {
					"Numero"(nota.dados.codigoRps)
					"Serie"()
					"Tipo"()
				}
				"DataEmissao"(new Date())
				"NaturezaOperacao"()
				"OptanteSimplesNacional"()
				"IncentivadorCultural"()
				"Status"()
				"Servico" {
					"Valores" {
						"ValorServicos"()
						"ValorDeducoes"()
						"ValorPis"()
						"ValorCofins"()
						"ValorInss"()
						"ValorIr"()
						"ValorCsll"()
						"IssRetido"()
						"ValorIss"()
						"OutrasRetencoes"()
						"Aliquota"()
						"DescontoIncondicionado"()
						"DescontoCondicionado"()
					}
					"ItemListaServico"()
					"CodigoTributacaoMunicipio"()
					"Discriminacao"()
					"CodigoMunicipio"()
				}
				"Prestador" {
					"Cnpj" (nota.empresa.cnpj)
					"InscricaoMunicipal" (nota.empresa.inscricaoMunicipal)
				}
				"Tomador" {
					"IdentificacaoTomador" { "CpfCnpj"() }
					"RazaoSocial"()
					"Endereco" {
						"Endereco"(nota.cliente.endereco)
						"Numero"(nota.cliente.numero)
						"Complemento"(nota.cliente.complemento)
						"Bairro"(nota.cliente.bairro)
						"CodigoMunicipio"(nota.cliente.codigoMunicipio)
						"Uf"(nota.cliente.unidadeFederativa)
						"Cep"(nota.cliente.cep)
					}
				}
				"Contato" {
					"Email"(nota.cliente.email)
				}
			}
		}
	}
}
