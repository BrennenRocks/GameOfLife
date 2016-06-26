/*
 * Brennen Davis and Robert Davis
 * Spring 1410 Team Project
 */
package gameOfLife;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

public class Instructions extends JDialog {

	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Instructions dialog = new Instructions();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Instructions() {
		setBounds(100, 100, 400, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInstructions = createTitleLbl();
		contentPanel.add(lblInstructions, BorderLayout.NORTH);
		
		JTextPane txtPnl = createTextPanel();
		contentPanel.add(txtPnl, BorderLayout.CENTER);
		
		JLabel label = new JLabel("");
		label.setIcon(Images.DEMO.getImage());
		contentPanel.add(label, BorderLayout.SOUTH);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.LIGHT_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

	private JTextPane createTextPanel() {
		JTextPane txtPnl = new JTextPane();
		txtPnl.setBackground(Color.LIGHT_GRAY);
		txtPnl.setBorder(new EmptyBorder(0, 0, 0, 0));
		String instructions = "\n\n" 
				+ "Select which cells you would like to have alive\n"
				+ "A cell will only stay alive when there are only 2 living cells next to it\n"
				+ "A cell will become alive if there are 3 living cells next to it\n"
				+ "A live cell will die when it is Overpopulated or Underpopulated\n"
				+ "Overpopulated: When there are 4 or more live cells next to current cell\n"
				+ "Underpopulated: When there are 0-1 living cells next to it\n"
				+ "";

		txtPnl.setText(instructions);
		txtPnl.setEditable(false);
		txtPnl.setOpaque(true);
		return txtPnl;
	}

	private JLabel createTitleLbl() {
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		return lblInstructions;
	}

}
