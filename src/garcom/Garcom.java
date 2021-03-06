package garcom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import excecoes.CodigoGarcomInvalidoException;
import excecoes.CodigoItemInvalidoException;
import excecoes.GarcomNaoPossuiMesaException;
import excecoes.ItemNaoExistenteException;
import excecoes.NomeInvalidoException;
import excecoes.PedidoVazioException;
import excecoes.QuantidadeInvalidaException;
import restaurante.Cardapio;
import restaurante.ItemCardapio;
import restaurante.Mesa;

public class Garcom {
	private String nome;
	private String codigo;
	private ArrayList<Mesa> mesas;
	
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public ArrayList<Mesa> getMesasGarcom() {
		return this.mesas;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Mesa getMesa(String numeroMesa) throws GarcomNaoPossuiMesaException{
		Mesa resp = null;
		for (int i=0; i<mesas.size(); i++) {
			if (mesas.get(i).getNumeroMesa().equals(numeroMesa)) {
				resp = mesas.get(i);
				break;
			}
		}
		if(resp == null) {
			throw new GarcomNaoPossuiMesaException();
		}else {
			return resp;
		}
		
	}
	
	public void adicionarPedido(String numeroMesa, String codigoItem, int quantidade) throws
	GarcomNaoPossuiMesaException, ItemNaoExistenteException, QuantidadeInvalidaException {
		if(quantidade <=0) {
			throw new QuantidadeInvalidaException();
		}
		Mesa mesa = getMesa(numeroMesa);
		if(mesa.isOcupada()==false) {
			mesa.setOcupada(true);
		}
		ItemCardapio item = Cardapio.getItemCardapio(codigoItem);
			if(mesa.getPedido().containsKey(item)) {
				mesa.setPedido(item, quantidade);
			} else {
				mesa.getPedido().put(item, quantidade);
			} 
	}
	
	public void encerrarPedido(String numeroMesa) throws GarcomNaoPossuiMesaException, PedidoVazioException, IOException {
		Mesa m = getMesa(numeroMesa);
		if (m.getPedido()==null) {
			throw new PedidoVazioException();
		}
		m.setEncerrada(true);
	}
	
	
	public void removerPedido(String numeroMesa, String codigoItem, int quantidade) throws 
	GarcomNaoPossuiMesaException, ItemNaoExistenteException, QuantidadeInvalidaException {
		if(quantidade <=0) {
			throw new QuantidadeInvalidaException();
		}
		Mesa mesa = getMesa(numeroMesa);
		ItemCardapio item = Cardapio.getItemCardapio(codigoItem);
				if((mesa.getPedido().containsKey(item)) && (mesa.getPedido().get(item)>1) && 
						(mesa.getPedido().get(item) > quantidade)) {
							mesa.setPedido(item, (-quantidade));
				} else if((mesa.getPedido().containsKey(item)) && (mesa.getPedido().get(item)>1) && 
						(mesa.getPedido().get(item) == quantidade)) {
							mesa.getPedido().remove(item);
				} else {
					throw new QuantidadeInvalidaException();
				}
	}
	
		
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Garcom(String nome, String codigo) throws CodigoGarcomInvalidoException, NomeInvalidoException {
		for(byte b : codigo.getBytes()) {
			if(b<48 || b>57) {
				throw new CodigoGarcomInvalidoException();
			}
		}
		if(codigo.length()!=4) {
			throw new CodigoGarcomInvalidoException();
		}
		
		for(byte b : nome.getBytes()) {
			if((b<65 && b!=32) || (b>90 && b<97) || (b>122)) {
				throw new NomeInvalidoException();
			}
		}
		
	
		this.codigo = codigo;
		this.nome = nome;
		mesas = new ArrayList<Mesa>();
	}

}