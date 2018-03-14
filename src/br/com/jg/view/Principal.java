package br.com.jg.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.jg.entities.Prato;
import br.com.jg.entities.PratoBuilder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Principal extends JFrame {
	private static final long serialVersionUID = 5994710431462309486L;
	
	private final PratoBuilder pratoMassa = new PratoBuilder();
	private final PratoBuilder pratoNaoMassa = new PratoBuilder();
	
	private final MeuContainer<Prato> pratosMassa = new MeuContainer<>();
	private final MeuContainer<Prato> pratosNaoMassa = new MeuContainer<>();
	
	private int resposta;

	private JPanel contentPane;
	private JButton btnOk;
	private JLabel lblPrincipal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		this.pratosMassa.add(this.pratoMassa.descricao("Lasanha")
				               .caracteristicas("")
				               .build());
		
		this.pratosNaoMassa.add(this.pratoNaoMassa.descricao("Bolo de chocolate")
				                  .caracteristicas("")
				                  .build()); 
		
		setTitle("Jogo Gourmet - FG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 103);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblPrincipal = new JLabel("Pense em um prato que gosta");
		lblPrincipal.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblPrincipal = new GridBagConstraints();
		gbc_lblPrincipal.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrincipal.gridx = 0;
		gbc_lblPrincipal.gridy = 0;
		contentPane.add(lblPrincipal, gbc_lblPrincipal);
		
		btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ok(e);
			}
		});
		
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 1;
		contentPane.add(btnOk, gbc_btnOk);
	}
	
	private void advinharPrato(MeuContainer<Prato> pratos) {
		int i;
		
		for (i = (pratos.lista.size() - 1); i > 0; i--) {
			resposta = perguntaPrato(pratos, i, false);
			
			if (resposta == JOptionPane.YES_OPTION) {
				acertei();
				break;
			} else if ((resposta == JOptionPane.NO_OPTION) && (i == 0)) {
				adicionarPrato(pratos, i);
				break;
			}
		}
		
		if (i == 0) {
			resposta = perguntaPrato(pratos, i, false);
			
			if (resposta == JOptionPane.YES_OPTION) {
				acertei();
				return;
			}
			
			adicionarPrato(pratos, i);
		}
	}
	
	private void adicionarPrato(MeuContainer<Prato> pratos, int index) {
		Prato item = montaNovoPrato(pratos, index);
		
		if (item != null)
			pratos.lista.add(item);
	}
		
	private Prato montaNovoPrato(MeuContainer<Prato> pratos, int index) {
		String descricao = JOptionPane.showInputDialog(rootPane, "Qual prato você pensou?", "Desisto", JOptionPane.QUESTION_MESSAGE);
		
		if (descricao == null)
			descricao = "";
		
		String caracteristicas = JOptionPane.showInputDialog(rootPane, descricao.concat(" é __________ mas ").concat(pratos.lista.get(index).getDescricao()).concat(" não."), "Complete", JOptionPane.QUESTION_MESSAGE);
		
		if (caracteristicas == null)
			caracteristicas = "";
		
		if ((!descricao.equals("")) && (!caracteristicas.equals(""))) {
			PratoBuilder prato = new PratoBuilder();
			return prato.descricao(descricao).caracteristicas(caracteristicas).build();
		} else {
			return null;
		}
	}
	
	private void acertei() {
		JOptionPane.showMessageDialog(rootPane, "Acertei de novo!", "Acertei", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private int perguntaPrato(MeuContainer<Prato> pratos, int index, boolean caracteristica) {
		String descricao = "";
		
		if (caracteristica) {
			descricao = pratos.lista.get(index).getCaracteristicas();
		} else {
			descricao = pratos.lista.get(index).getDescricao();
		}
		
		if (descricao == null)
			descricao = "";
		
		return JOptionPane.showConfirmDialog(rootPane, "O prato que pensou é ".concat(descricao).concat("?"), "Confirma", JOptionPane.YES_NO_OPTION);
	}
	
	private void iniciarJogo() {
		resposta = JOptionPane.showConfirmDialog(rootPane, "O prato que você pensou é massa?", "Confirma", JOptionPane.YES_NO_OPTION);
		
		if (resposta == JOptionPane.YES_OPTION) {
			advinharPrato(this.pratosMassa);
		} else {
			advinharPrato(this.pratosNaoMassa);			
		}
	}
	
	private void ok(ActionEvent e) {
		this.setVisible(false);
		iniciarJogo();
		this.setVisible(true);
	}
}
