package io.github.notacariocafacil.gerador.impl

import groovy.xml.XmlUtil;
import io.github.notacariocafacil.Empresa
import io.github.notacariocafacil.NotaCarioca
import io.github.notacariocafacil.gerador.Gerador

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
					"ListaRps" {
						for (nota in notas) {
							geraRPS(builder, nota, empresa)
						}
					}
				}
			}
		}
		
		return XmlUtil.serialize(xml)
	}

	def geraRPS = { builder, NotaCarioca nota, Empresa empresa->
		builder."Rps" {
			"InfRps"("Id" :  nota.dados.idRps) {
				"IdentificacaoRps" {
					"Numero"(nota.dados.numeroRps)
					"Serie"(nota.dados.serieRps)
					"Tipo"(nota.dados.tipoRps)
				}
				"DataEmissao"(nota.dataEmissao.format("yyyy-MM-dd'T'HH:mm:ss"))
				"NaturezaOperacao"(nota.dados.naturezaOperacao)
				"OptanteSimplesNacional"(nota.dados.optanteSimplesNacional)
				"IncentivadorCultural"(nota.dados.incentivadorCultural)
				"Status"(nota.dados.status)
				"Servico" {
					"Valores" {
						"ValorServicos"(nota.dados.valorServicos)
						"ValorDeducoes"(nota.dados.valorDeducoes)
						"ValorPis"(nota.dados.valorPis)
						"ValorCofins"(nota.dados.valorCofins)
						"ValorInss"(nota.dados.valorInss)
						"ValorIr"(nota.dados.valorIr)
						"ValorCsll"(nota.dados.valorCsll)
						"IssRetido"(nota.dados.issRetido)
						"ValorIss"(nota.dados.valorIss)
						"OutrasRetencoes"(nota.dados.outrasRetencoes)
						"Aliquota"(nota.dados.aliquota)
						"DescontoIncondicionado"(nota.dados.descontoIncondicionado)
						"DescontoCondicionado"(nota.dados.descontoCondicionado)
					}
					"ItemListaServico"(nota.dados.itemListaServico)
					"CodigoTributacaoMunicipio"(nota.dados.codigoTributacaoMunicipio)
					"Discriminacao"(nota.dados.discriminacao)
					"CodigoMunicipio"(nota.dados.codigoMunicipio)
				}
				"Prestador" {
					"Cnpj" (empresa.cnpj)
					"InscricaoMunicipal" (empresa.inscricaoMunicipal)
				}
				"Tomador" {
					"IdentificacaoTomador" { 
						"CpfCnpj" {
							if (nota.cliente.cpf) {
								"Cpf"(nota.cliente.cpf)
							} else {
								"Cnpj"(nota.cliente.cnpj)
							}
						}
					}
					"RazaoSocial"(nota.cliente.razaoSocial)
					"Endereco" {
						"Endereco"(nota.cliente.endereco)
						"Numero"(nota.cliente.numero)
						"Complemento"(nota.cliente.complemento)
						"Bairro"(nota.cliente.bairro)
						"CodigoMunicipio"(nota.cliente.codigoMunicipio)
						"Uf"(nota.cliente.unidadeFederativa)
						"Cep"(nota.cliente.cep)
					}
					"Contato" {
						"Email"(nota.cliente.email)
					}
				}
			}
		}
	}
}
