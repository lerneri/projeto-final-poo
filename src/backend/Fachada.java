package backend;

import java.io.IOException;
import java.util.ArrayList;

import excecoes.CodigoItemInvalidoException;
import excecoes.ConjuntoGarcomVazioException;
import excecoes.ConjuntoMesasVazioException;
import excecoes.GarcomInexistenteException;
import excecoes.CodigoGarcomJaExistenteException;
import excecoes.MesaInexistenteException;
import excecoes.MesaJaExistenteException;

public class Fachada {
	private static Fachada instancia = null;
	private Gerente gerente;
	private Cardapio cardapio;
	private ConjuntoMesas conjuntoMesas;
	private ConjuntoGarcons conjuntoGarcons;
	
	
	public Gerente getGerente() {
		return gerente;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public ConjuntoMesas getConjuntoMesas() throws ConjuntoMesasVazioException{
		if (conjuntoMesas == null) {
			throw new ConjuntoMesasVazioException();
		}
		return conjuntoMesas;
	}

	public ConjuntoGarcons getConjuntoGarcons() throws ConjuntoGarcomVazioException{
		if(conjuntoGarcons==null) {
			throw new ConjuntoGarcomVazioException();
		}
		return conjuntoGarcons;
	}

	public static Fachada getInstancia() throws IOException, CodigoItemInvalidoException {
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	Fachada() throws IOException, CodigoItemInvalidoException {
		gerente = new Gerente("Fernando Trinta");
		cardapio = new Cardapio();
		conjuntoMesas = new ConjuntoMesas();
		conjuntoGarcons = new ConjuntoGarcons();
	}
	
}