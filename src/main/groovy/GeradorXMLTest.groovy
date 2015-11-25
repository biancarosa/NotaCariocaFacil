import static org.junit.Assert.*
import io.github.notacariocafacil.Empresa
import io.github.notacariocafacil.NotaCarioca
import io.github.notacariocafacil.gerador.impl.GeradorXML

import org.junit.Test

class GeradorXMLTest {

	/**
	 * Teste com dados de xml de exemplo:
	 * https://notacarioca.rio.gov.br/files/manuais/rps.xm
	 */
	@Test
	public void testGeraLote() {
		Empresa empresa = new Empresa()
		List<NotaCarioca> notas = []
		NotaCarioca nota = new NotaCarioca()

		empresa.cnpj = "00000000000000"
		empresa.inscricaoMunicipal = "00000000"

		nota.dados.idRps = "rps1serieT1"
		nota.dados.numeroRps = "1"
		nota.dados.serieRps = "A"
		nota.dados.tipoRps = "1"

		nota.dados.naturezaOperacao = "1"
		nota.dados.optanteSimplesNacional = "1"
		nota.dados.incentivadorCultural = "2"
		nota.dados.status = "1"

		nota.dados.valorServicos = 100
		nota.dados.issRetido = 2
		nota.dados.valorIss = 5
		nota.dados.aliquota = 0.05

		nota.dados.itemListaServico = "1405"
		nota.dados.codigoTributacaoMunicipio = "140520"
		nota.dados.discriminacao = "Exemplo de Discriminação de RPS"
		nota.dados.codigoMunicipio = "3304557"

		
		nota.cliente.identificador = "00000000000"
		nota.cliente.razaoSocial = "Nome do Tomador"
		nota.cliente.endereco = "Av. Rio Branco"
		nota.cliente.numero = "123"
		nota.cliente.complemento = "Andar 1"
		nota.cliente.bairro = "Centro"
		nota.cliente.codigoMunicipio = "3304557"
		nota.cliente.unidadeFederativa = "RJ"
		nota.cliente.cep = "20040001"
		nota.cliente.email = "meuemail@web.com.br"
		
		nota.dataEmissao = new Date()
		
		notas+= nota 
		
		def xml = new GeradorXML().geraLote(notas, empresa)
		
		assert xml
	}
}
