package angulo.sistemas.sat.codvinculacao;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class TelaPrincipal extends JFrame {
	JTextField txtCNPJContribuinte;
	JTextField txtCNPJSoftwarehouse;
	JPasswordField txtSenha;
	JTextArea txtCodVinculacao;
	
	public static void main(String[] args) {
		new TelaPrincipal();
	}
	
	public TelaPrincipal(){
		Container contentPane = this.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		JLabel lblSenha = new JLabel("Senha:");
		txtSenha = new JPasswordField(14);
		contentPane.add(lblSenha);
		contentPane.add(txtSenha);
		
		JLabel lblCNPJSoftwarehouse = new JLabel("CNPJ da Softwarehouse:");
		txtCNPJSoftwarehouse = new JTextField(10);
		contentPane.add(lblCNPJSoftwarehouse);
		contentPane.add(txtCNPJSoftwarehouse);
		
		JLabel lblCNPJContribuinte = new JLabel("CNPJ do Contribuinte:");
		txtCNPJContribuinte = new JTextField(10);
		contentPane.add(lblCNPJContribuinte);
		contentPane.add(txtCNPJContribuinte);
		
		JButton btnGerar = new JButton("Gerar Codigo");
		btnGerar.addActionListener(new MinhaActionListener());
		contentPane.add(btnGerar);
		
		JLabel lblCodigoGerado = new JLabel("Codigo Gerado:");
		txtCodVinculacao = new JTextArea(4,40);
		JScrollPane scrollPane = new JScrollPane(txtCodVinculacao); 
		contentPane.add(lblCodigoGerado);
		contentPane.add(scrollPane);
		
		//Arrumando o layout do SpringLayout
		layout.putConstraint(SpringLayout.WEST, lblSenha, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, lblSenha, 5, SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, txtSenha, 5, SpringLayout.EAST, lblSenha);
		layout.putConstraint(SpringLayout.NORTH, txtSenha, 5, SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, lblCNPJSoftwarehouse, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, lblCNPJSoftwarehouse, 5, SpringLayout.SOUTH, txtSenha);
		
		layout.putConstraint(SpringLayout.WEST, txtCNPJSoftwarehouse, 5, SpringLayout.EAST, lblCNPJSoftwarehouse);
		layout.putConstraint(SpringLayout.NORTH, txtCNPJSoftwarehouse, 5, SpringLayout.SOUTH, txtSenha);
		
		layout.putConstraint(SpringLayout.WEST, lblCNPJContribuinte, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, lblCNPJContribuinte, 5, SpringLayout.SOUTH, lblCNPJSoftwarehouse);
		
		layout.putConstraint(SpringLayout.WEST, txtCNPJContribuinte, 5, SpringLayout.EAST, lblCNPJSoftwarehouse);
		layout.putConstraint(SpringLayout.NORTH, txtCNPJContribuinte, 5, SpringLayout.SOUTH, txtCNPJSoftwarehouse);
		
		layout.putConstraint(SpringLayout.WEST, btnGerar, 20, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, btnGerar, 5, SpringLayout.SOUTH, txtCNPJContribuinte);
		
		layout.putConstraint(SpringLayout.WEST, lblCodigoGerado, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, lblCodigoGerado, 5, SpringLayout.SOUTH, btnGerar);
		
		layout.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.EAST, lblCodigoGerado);
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.SOUTH, btnGerar);
		
		//txtCNPJSoftwarehouse.setText("12345678912345");
		//txtCNPJContribuinte.setText("78945612345678");
		//txtSenha.setText("0134");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 250);
		
		//this.pack();
		this.setVisible(true);
		
	}
	
	private class MinhaActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			//limpando...
			txtCodVinculacao.setText("");
			
			String cnpjConcatenado = txtCNPJSoftwarehouse.getText() + txtCNPJContribuinte.getText();
			System.out.println("CNPJs Concatenados:" + cnpjConcatenado);
			String assinado = GenSig.assinar(cnpjConcatenado.getBytes(), txtSenha.getPassword());
			txtCodVinculacao.setText(assinado);
		}
	}

	
}
