/*
 * Brennen Davis and Robert Davis
 * Spring 1410 Team Project
 */

//THIS CODE IS NOT BEING USED
package gameOfLife;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RandomDialog extends JDialog {
	
	// NOT BEING USED

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private int xStart;
	private int xEnd;
	private int yStart;
	private int yEnd;
	private int cells;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RandomDialog dialog = new RandomDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RandomDialog() {
		setTitle("Random");
		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		createAreaSelection();
		
		createCellSelection();
				
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cells = Integer.parseInt(textField.getText());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void createCellSelection() {
		JLabel lblNumberOfCells = new JLabel("Number of Cells");
		lblNumberOfCells.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPanel.add(lblNumberOfCells);

			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBorder(null);
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(2, 0, 0, 0));
			
				textField = new JTextField();
				
				panel.add(textField);
				textField.setColumns(10);
				
				JSlider slider = new JSlider();
				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						textField.setText(String.format("%d", slider.getValue()));
					}
				});
				
				panel.add(slider);
				
				textField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						slider.setValue(Integer.parseInt(textField.getText()));
					}
				});
	}

	private void createAreaSelection() {
		JLabel lblArea = new JLabel("Area");
		lblArea.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPanel.add(lblArea);


		JComboBox comboBox = new JComboBox();
		
		contentPanel.add(comboBox);
	}

	/**
	 * @return the xStart
	 */
	public int getxStart() {
		return xStart;
	}

	/**
	 * @return the xEnd
	 */
	public int getxEnd() {
		return xEnd;
	}

	/**
	 * @return the yStart
	 */
	public int getyStart() {
		return yStart;
	}

	/**
	 * @return the yEnd
	 */
	public int getyEnd() {
		return yEnd;
	}
	
	/**
	 * @return the cells
	 */
	public int getCells() {
		return cells;
	}

}
