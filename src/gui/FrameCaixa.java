package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import backend.*;
import excecoes.CodigoItemInvalidoException;
import excecoes.ConjuntoMesasVazioException;
import excecoes.MesaInexistenteException;

public class FrameCaixa extends JFrame {

	// Tela referente ao caixa do restaurante

	private JPanel contentPane;

	public FrameCaixa() {
		setTitle("Gerenciamento de Restaurante - Caixa");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][][][][]"));

		JLabel mensagemBoasVindas = new JLabel("Bem-vindo, CAIXA!");
		getContentPane().add(mensagemBoasVindas, "cell 2 0");

		JLabel lblEncerrarMesa = new JLabel("Pagamento");
		getContentPane().add(lblEncerrarMesa, "cell 4 1,alignx center,aligny baseline");

		// Seleção de mesa para emissão de pagamento

		JLabel lblMesa = new JLabel("Mesa:");
		getContentPane().add(lblMesa, "flowx,cell 4 2,alignx center");

		JTextField txtMesas = new JTextField();
		getContentPane().add(txtMesas, "cell 4 2,alignx center");
		txtMesas.setColumns(3);

		// Formas de pagamento

		String[] pagamentos = { "Crédito", "Débito", "Espécie", "PIX", "Cheque" };

		JLabel lblPagamentos = new JLabel("Forma de pagamento:");
		getContentPane().add(lblPagamentos, "flowx,cell 4 3,alignx center");

		JComboBox comboBoxPagamento = new JComboBox(pagamentos);
		getContentPane().add(comboBoxPagamento, "cell 4 3,alignx center");

		// Pagamento

		JLabel lblValor = new JLabel("Valor recebido (R$):");
		getContentPane().add(lblValor, "flowx,cell 4 4,alignx center");

		JTextField valor = new JTextField();
		valor.setText("000.00");
		getContentPane().add(valor, "cell 4 4");
		valor.setColumns(7);

		// Emissão de comprovante

		JButton btnComprovante = new JButton("Emitir comprovante");
		getContentPane().add(btnComprovante, "cell 4 5,alignx center");
		btnComprovante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					FileWriter fw = new FileWriter("cupomfiscal.txt");
					BufferedWriter bw = new BufferedWriter(fw);
					String mesaPagamento = txtMesas.getText();
					bw.write("*CUPOM FISCAL*");
					bw.write(System.lineSeparator());
					double soma = 0;
					// TODO: gerar começo do cupom a partir da conta da mesaPagamento
					bw.write("Valor total do pedido: R$" + soma);
					bw.write(System.lineSeparator());
					String metodo = comboBoxPagamento.getSelectedItem().toString();
					bw.write("Método de pagamento: " + metodo);
					bw.write(System.lineSeparator());
					String pagamento = valor.getText();
					bw.write("Valor pago: R$" + pagamento);
					double pago = Double.parseDouble(pagamento);
					double troco = pago - soma;
					bw.write(System.lineSeparator());
					bw.write("Troco: R$" + troco);
					Fachada.getInstancia().getCaixa().encerrarMesa(Fachada.getInstancia().getConjuntoMesas().getMesa(mesaPagamento));
					bw.close();
				} catch (CodigoItemInvalidoException e1) {
					JOptionPane.showMessageDialog(contentPane, "Codigo de um item invalido no arquivo cardapio.txt");
				} catch (MesaInexistenteException e1) {
					JOptionPane.showMessageDialog(contentPane, "Mesa inexistente");
				} catch (ConjuntoMesasVazioException e1) {
					JOptionPane.showMessageDialog(contentPane, "Conjunto Mesas Vazio");					
				} catch (IOException excep) {
					JOptionPane.showMessageDialog(contentPane, "IO exception");
				}
			}
		});

	}

}
