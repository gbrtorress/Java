package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dao.DAO;
import model.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldTelefone;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel_3;
	private JTextArea textAreaEndereco;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPrincipal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 30, 400, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CPF/CNPJ");
		lblNewLabel_1.setBounds(10, 61, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setBounds(255, 61, 60, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setColumns(10);
		textFieldCpfCnpj.setBounds(10, 75, 184, 20);
		contentPane.add(textFieldCpfCnpj);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(240, 75, 184, 20);
		contentPane.add(textFieldTelefone);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 106, 46, 14);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(10, 124, 400, 20);
		contentPane.add(textFieldEmail);
		
		lblNewLabel_3 = new JLabel("Endereco");
		lblNewLabel_3.setBounds(10, 150, 60, 14);
		contentPane.add(lblNewLabel_3);
		
		textAreaEndereco = new JTextArea();
		textAreaEndereco.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaEndereco.setBounds(10, 164, 400, 52);
		contentPane.add(textAreaEndereco);
		
		JButton btnNewButton = new JButton(clienteSelecionado == null?"Incluir":"Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente(null, textFieldNome.getText(), textFieldCpfCnpj.getText(), textFieldEmail.getText(), 
						textFieldTelefone.getText(), textAreaEndereco.getText());
				
				if(clienteSelecionado == null) {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())) {
						
						dao.cadastarCliente(cliente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
					}
				
				
				}else {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())) {
						
						dao.alterarCliente(clienteSelecionado.getId(), cliente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
					}
					
				}
			}
		});
		btnNewButton.setBounds(321, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(20, 227, 89, 23);
		btnNewButton_1.setVisible(false);
		contentPane.add(btnNewButton_1);
		
		if (clienteSelecionado != null) {
			preencherCampos(clienteSelecionado);
			btnNewButton_1.setVisible(true);
		}
	}
	
	private void preencherCampos(Cliente clienteSelecionado) {
		textFieldNome.setText(clienteSelecionado.getNome());
		textFieldCpfCnpj.setText(clienteSelecionado.getCpfCnpj());
		textFieldEmail.setText(clienteSelecionado.getEmail());
		textFieldTelefone.setText(clienteSelecionado.getTelefone());
		textAreaEndereco.setText(clienteSelecionado.getEndereco());
		
	}
	
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose();
		dispose();
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);
	}
}
