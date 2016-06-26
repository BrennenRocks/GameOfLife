/*
 * Brennen Davis and Robert Davis
 * Spring 1410 Team Project
 */
package gameOfLife;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.Color;


public class GameOfLifeGui extends JFrame {

	private Game game;
	private JPanel contentPane;
	private JLabel lblGenerationCounter;
	private boolean goToggle = true;
	private JButton btnPlay;
	private JLabel lblAlive;
	private JLabel lblDead;
	private JPanel choicePnl;
	private JPanel gridPanel;
	private JPanel pnlNorth;
	private JPanel pnlSouth;
	private JPanel pnlWest;
	private JPanel pnlEast;
	private JLabel labelCustomError;
	int input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeGui frame = new GameOfLifeGui();
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
	public GameOfLifeGui() {
		setTitle("Game of Life Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnlNorth = createTopPanel();

		pnlSouth = createControlPanel();

		pnlWest = createWestPanel();

		pnlEast = createEastPanel();
		
		createChoicePnl();

	}

	private void createChoicePnl() {
		choicePnl = new JPanel();
		choicePnl.setLayout(new GridLayout(0, 1, 5, 5));
		
		JButton btn10 = createBtn10();
		choicePnl.add(btn10);
		
		JButton btn25 = createBtn25();
		choicePnl.add(btn25);
		
		JButton btn50 = createBtn50();
		choicePnl.add(btn50);
		
		
		JButton btnCustom = createBtnCustomSize();
		choicePnl.add(btnCustom);
		
		{
			JLabel labelSpacer = new JLabel("");
			choicePnl.add(labelSpacer);
		}
		JButton btnInstructions = createBtnInstructions();
		choicePnl.add(btnInstructions);
		
		contentPane.add(choicePnl, BorderLayout.CENTER);
		{
			JLabel labelSpacer2 = new JLabel("");
			choicePnl.add(labelSpacer2);
		}
	}

	private JButton createBtn10() {
		JButton btn10 = new JButton("10x10");
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewGame(10);
			}
		});
		btn10.setBackground(Color.BLUE);
		btn10.setForeground(Color.RED);
		btn10.setOpaque(true);
		btn10.setBorderPainted(false);
		return btn10;
	}
	
	private JButton createBtn25() {
		JButton btn25 = new JButton("25x25");
		btn25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewGame(25);
			}

		});
		btn25.setBackground(Color.BLUE);
		btn25.setForeground(Color.RED);
		btn25.setOpaque(true);
		btn25.setBorderPainted(false);
		return btn25;
	}
	
	private JButton createBtn50() {
		JButton btn50 = new JButton("50x50");
		btn50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewGame(50);
			}
		});
		btn50.setBackground(Color.BLUE);
		btn50.setForeground(Color.RED);
		btn50.setOpaque(true);
		btn50.setBorderPainted(false);
		return btn50;
	}
	
	private JPanel createPnlCustom(){
		JPanel pnlCustom = new JPanel();
		pnlCustom.setLayout(new GridLayout(4, 1, 5, 5));
		
		JLabel titleLabel = new JLabel("Enter a number betweetn 5-60");
		pnlCustom.add(titleLabel);
		
		JTextField customSize = createCustomSize();
		pnlCustom.add(customSize);
		
		labelCustomError = new JLabel();
		pnlCustom.add(labelCustomError);
		
		return pnlCustom;
		
	}
	private JButton createBtnCustomSize() {
		JButton btnCustomSize = new JButton("Custom");
		btnCustomSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				contentPane.removeAll();
				contentPane.add(createPnlCustom());
				revalidate();
				repaint();
				
			}
		});
		btnCustomSize.setBackground(Color.BLUE);
		btnCustomSize.setForeground(Color.RED);
		btnCustomSize.setOpaque(true);
		btnCustomSize.setBorderPainted(false);
		return btnCustomSize;
	}
	
	private JTextField createCustomSize() {
		JTextField customSize = new JTextField();
		customSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stringInput = customSize.getText();
				input = Integer.parseInt(stringInput);
				if(input < 5 || input > 60){
					labelCustomError.setText("The number must be between 5 and 60!");
				}else{
					createNewGame(input);
				}
			}
		});
		return customSize;
	}
	
	private void createNewGame(int size) {
		contentPane.removeAll();
		game = new Game(size);
		gridPanel = createGridPanel(size);
		setWindowSize(size);
		setTitle("Game of Life");
		contentPane.add(gridPanel, BorderLayout.CENTER);
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		contentPane.add(pnlWest, BorderLayout.WEST);
		contentPane.add(pnlEast, BorderLayout.EAST);
		revalidate();
		repaint();
	}
	
	private void setWindowSize(int size) {
		int btnSize = 40;
		if (size >= 25)
			btnSize = 14;
		if (size >= 50)
			btnSize = 12;
		if (size >= 75)
			btnSize = 10;
		setBounds(getX(), getY(), 275+(btnSize*size), 90+(btnSize*size));
	}
	

	private JPanel createControlPanel() {
		JPanel pnlSouth = new JPanel();

		createBtnPlay();
		pnlSouth.add(btnPlay);

		JButton btnReset = createBtnReset();
		pnlSouth.add(btnReset);		

		JButton btnRandom = createRandomBtn();
		pnlSouth.add(btnRandom);
		
		JButton btnMenu = createBtnMainMenu();
		pnlSouth.add(btnMenu);
		

		return pnlSouth;

	}

	private JButton createRandomBtn() {
		JButton btnRandom = new JButton("");
		btnRandom.setBorderPainted(false);
		btnRandom.setOpaque(true);
		btnRandom.setContentAreaFilled(false);
		btnRandom.setIcon(Images.RANDOM.getImage());
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.randomizeCells();
			}
		});
		return btnRandom;
	}

	private JButton createBtnInstructions() {
		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.setBorderPainted(false);
		btnInstructions.setOpaque(true);
		btnInstructions.setBackground(Color.RED);
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instructions instructions = new Instructions();
				instructions.setLocation(getX() + 100, getY() + 100);
				instructions.setVisible(true);
			}
		});
		return btnInstructions;
	}

	private JButton createBtnMainMenu() {
		JButton btnMenu = new JButton("");
		btnMenu.setBorderPainted(false);
		btnMenu.setOpaque(true);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setIcon(Images.MENU.getImage());
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				setTitle("Game of Life Menu");
				setBounds(getX(), getY(), 300, 350);
				contentPane.add(choicePnl, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		return btnMenu;
	}
	
	private JButton createBtnReset() {
		JButton btnReset = new JButton("");
		btnReset.setIcon(Images.RESET.getImage());
		btnReset.setBorderPainted(false);
		btnReset.setOpaque(false);
		btnReset.setContentAreaFilled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
				game.resetCells();
				updateLabels();
				revalidate();
				repaint();
			}
		});
		return btnReset;
	}

	private void createBtnPlay() {
		btnPlay = new JButton("");
		btnPlay.setIcon(Images.PLAY.getImage());
		btnPlay.setBorderPainted(false);
		btnPlay.setOpaque(true);
		btnPlay.setContentAreaFilled(false);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (goToggle) {
					start();
					btnPlay.setIcon(Images.PAUSE.getImage());
				} else {
					stop();
					btnPlay.setIcon(Images.PLAY.getImage());
				}
				goToggle = !goToggle;
			}
		});
	}
	
	

	private JPanel createTopPanel() {
		JPanel pnlNorth = new JPanel();
		pnlNorth.setLayout(new GridLayout(3, 1, 0, 0));

		lblGenerationCounter = new JLabel();
		pnlNorth.add(lblGenerationCounter);

		lblAlive = new JLabel();
		pnlNorth.add(lblAlive);
		
		lblDead = new JLabel();
		pnlNorth.add(lblDead);
		return pnlNorth;

	}


	private JPanel createGridPanel(int size) {
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(size, size, 1, 1));
		game.getCells(gridPanel);
		return gridPanel;
	}
	
	private JButton createBtnLoad() {
		JButton btnLoad = new JButton("");
		btnLoad.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLoad.setVerticalTextPosition(SwingConstants.TOP);
		btnLoad.setIcon(Images.LOAD.getImage());
		btnLoad.setOpaque(true);
		btnLoad.setBorderPainted(false);
		btnLoad.setContentAreaFilled(false);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.load();
			}
		});
		
		return btnLoad;
	}

	private JButton createBtnSave() {
		JButton btnSave = new JButton("");
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setVerticalTextPosition(SwingConstants.TOP);
		btnSave.setIcon(Images.SAVE.getImage());
		btnSave.setOpaque(true);
		btnSave.setBorderPainted(false);
		btnSave.setContentAreaFilled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.save();
			}
		});
		
		
		return btnSave;
	}
	
	
	private Timer timer = new Timer(50, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
			game.updateCells();
			updateLabels();
		}
	});
	
	public void start(){
		timer.start();
	}
	
	public void stop(){
		timer.stop();
	}
	
	private void updateLabels() {
		lblGenerationCounter.setText(String.format("Generation: %d", game.getGenCounter()));
		lblAlive.setText(String.format("Cells Alive: %d", game.getAlive()));
		lblDead.setText(String.format("Cells Dead: %d", game.getDead()));
	}
	
	private JPanel createWestPanel() {
		JPanel pnlWest = new JPanel();
		pnlWest.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnPreset1 = createBtnSave();
		pnlWest.add(btnPreset1);
		
		lblGenerationCounter = new JLabel();
		lblGenerationCounter.setText("Generation: ");
		pnlWest.add(lblGenerationCounter);
		
		return pnlWest;
	}
	
	private JPanel createEastPanel() {
		JPanel pnlEast = new JPanel();
		pnlEast.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnLoadPreset1 = createBtnLoad();
		pnlEast.add(btnLoadPreset1);
		
		lblAlive = new JLabel();
		lblAlive.setText("Cells Alive: " );
		pnlEast.add(lblAlive);
		
		lblDead = new JLabel();
		lblDead.setText("Cells Dead: " );
		pnlEast.add(lblDead);
		
		return pnlEast;
	}




}
