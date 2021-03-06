package restaurante;

import java.util.*;

import excecoes.*;
import garcom.Garcom;

public class Mesa {
	private String numeroMesa;
	private HashMap<ItemCardapio, Integer> pedido;
	private Garcom garcomMesa;
	private boolean ocupada;
	private boolean encerrada;
	
	public boolean isEncerrada() {
		return encerrada;
	}

	public void setEncerrada(boolean encerrada) {
		this.encerrada = encerrada;
	}

	public Garcom getGarcomMesa() {
		return garcomMesa;
	}

	public void setGarcomMesa(Garcom garcomMesa) {
		this.garcomMesa = garcomMesa;
	}

	public HashMap<ItemCardapio, Integer> getPedido(){
		return this.pedido;
	}
	
	public void setPedido(ItemCardapio item, int quantidade) {
		this.pedido.replace(item, this.pedido.get(item)+quantidade);
	}
	
	
	public String getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(String numeroMesa) {
		this.numeroMesa = numeroMesa;
	}
	
	public boolean isOcupada() {
		return ocupada;
	}

	
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Mesa(String numeroMesa) throws NumeroMesaInvalidoException{
		for(byte b : numeroMesa.getBytes()) {
			if(b<48 || b>57) {
				throw new NumeroMesaInvalidoException();
			}
		}
		if(numeroMesa.length()!=2) {
			throw new NumeroMesaInvalidoException();
		}
		this.numeroMesa = numeroMesa;
		this.garcomMesa = null;
		this.pedido = new HashMap<ItemCardapio, Integer>();
		this.ocupada = false;
		this.encerrada = false;
	}
}