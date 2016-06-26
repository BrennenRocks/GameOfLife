/*
 * Brennen Davis and Robert Davis
 * Spring 1410 Team Project
 */
package gameOfLife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JPanel;

public class Game {
	private Cell[][] cells;
	private Boolean[][] tempCells;
	private Boolean[][] tempSaveCells; 
	private int genCounter;
	private Random rand = new Random();
	private int alive = 0;
	private int dead = 0;
	private String filePath;
	


	public Game(int size) {
		cells = new Cell[size][size];
		tempCells = new Boolean[size][size];
		tempSaveCells = new Boolean[size][size];
		filePath = String.format("savedGrid%dx%d.csv", size, size);
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell();
			}
		}
	}
	
	public void getCells(JPanel pnl) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				pnl.add(cells[i][j]);
			}
		}
	}
	
	public void randomizeCells() {
		resetCells();
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (rand.nextInt(4) == 1) {
					cells[i][j].setAlive();
				} else {
					cells[i][j].setDead();
				}
			}
		}
	}
	
	public void resetCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].reset();
			}
		}
		dead = 0;
		alive = 0;
		genCounter = 0;
	}
	

	public int getHowManyAlive() {
		alive = 0;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}
	
	private int getHowManyDead() {
		dead = 0;
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				if(!cells[i][j].isAlive()){
					dead++;
				}
			}
		}
		return dead;
	}
	
	protected void updateCells() {
		updateTempCells();
		updateNewCells();

	}
	
	private void updateNewCells() {
		for (int row = 0; row < tempCells.length; row++) {
			for (int column = 0; column < tempCells[row].length; column++) {
				if (row == 0) {
					if (column == 0) {
						int count = 0;
						for (int i = row; i <= row + 1; i++) {
							for (int j = column; j <= column + 1; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}

					} else if (column != 0
							&& column != tempCells[row].length - 1) {
						int count = 0;
						for (int i = row; i <= row + 1; i++) {
							for (int j = column - 1; j <= column + 1; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					} else if (column == tempCells[row].length - 1) {
						int count = 0;
						for (int i = row; i <= row + 1; i++) {
							for (int j = column - 1; j <= column; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					}
				}
				if (row != 0 && row != tempCells.length - 1) {
					if (column == 0) {
						int count = 0;
						for (int i = row - 1; i <= row + 1; i++) {
							for (int j = column; j <= column + 1; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					} else if (column != 0
							&& column != tempCells[row].length - 1) {
						int count = 0;
						for (int i = row - 1; i <= row + 1; i++) {
							for (int j = column - 1; j <= column + 1; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					} else if (column == tempCells[row].length - 1) {
						int count = 0;
						for (int i = row - 1; i <= row + 1; i++) {
							for (int j = column - 1; j <= column; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					}
				}
				if (row == tempCells.length - 1) {
					if (column == 0) {
						int count = 0;
						for (int i = row - 1; i <= row; i++) {
							for (int j = column; j <= column + 1; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					} else if (column != 0
							&& column != tempCells[row].length - 1) {
						int count = 0;
						for (int i = row - 1; i <= row; i++) {
							for (int j = column - 1; j <= column + 1; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					} else if (column == tempCells[row].length - 1) {
						int count = 0;
						for (int i = row - 1; i <= row; i++) {
							for (int j = column - 1; j <= column; j++) {
								if (tempCells[i][j] == true) {
									count++;
								}
							}
						}
						if (tempCells[row][column] == true) {
							if (count != 3 && count != 4) {
								cells[row][column].setDead();
							} else {
								cells[row][column].setAlive();
							}
						} else if (tempCells[row][column] == false) {
							if (count == 3) {
								cells[row][column].setAlive();
							} else {
								cells[row][column].setDead();
							}
						}
					}
				}
			}
		}

	}

	private void updateTempCells() {
		for (int i = 0; i < tempCells.length; i++) {
			for (int j = 0; j < tempCells[i].length; j++) {
				if (cells[i][j].isAlive() == true) {
					tempCells[i][j] = true;
				} else {
					tempCells[i][j] = false;
				}
			}
		}
	}

	private void updateTempSaveCells() {
		for (int i = 0; i < tempCells.length; i++) {
			for (int j = 0; j < tempCells[i].length; j++) {
				if (cells[i][j].isAlive() == true) {
					tempSaveCells[i][j] = true;
				} else {
					tempSaveCells[i][j] = false;
				}
			}
		}
	}

	/**
	 * @return the genCounter
	 */
	public int getGenCounter() {
		genCounter++;
		return genCounter;
	}

	/**
	 * @return the alive
	 */
	public int getAlive() {
		getHowManyAlive();
		return alive;
	}

	/**
	 * @return the dead
	 */
	public int getDead() {
		getHowManyDead();
		return dead;
	}

	public void load() {
		resetCells();
		try (BufferedReader reader = new BufferedReader(new FileReader("src/gameOfLife/"+filePath))){
			String line = null;
			String[] row = null;
			
			boolean parsedBoolean;
			int i = 0;
			while((line = reader.readLine()) != null){
				row = line.split(",");
				for(int j = 0; j < tempCells.length; j++){
					parsedBoolean = Boolean.parseBoolean(row[j]);
					tempCells[i][j] = parsedBoolean;
				}
				i++;
			}
			for (int k = 0; k < tempCells.length; k++) {
				for (int j = 0; j < tempCells[k].length; j++) {
					if (tempCells[k][j] == true) {
						cells[k][j].setAlive();
					} else {
						cells[k][j].setDead();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try (PrintWriter writer = new PrintWriter("src/gameOfLife/"+filePath)){
			for(int i = 0; i < tempSaveCells.length; i++){
				for(int j = 0; j < tempSaveCells[i].length; j++){
					updateTempSaveCells();
					writer.printf((tempSaveCells[i][j] ? "true," : "false,"));
				}
				writer.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
