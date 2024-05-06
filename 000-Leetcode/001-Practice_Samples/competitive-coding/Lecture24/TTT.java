package Lecture24;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	public static final int BOARD_SIZE = 3;
	public static JButton[][] board = new JButton[BOARD_SIZE][BOARD_SIZE];

	private enum GameStatus {
		Xwins, Zwins, tie, incomplete
	}

	private boolean crossTurn = true;

	public TTT() {
		super.setSize(800, 800);
		super.setTitle("Tic Tac Toe!");
		super.setResizable(false);
		GridLayout layout = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(layout);
		Font font = new Font("Algerian", 1, 250);
		for (int i = 0; i < layout.getRows(); i++) {
			for (int j = 0; j < layout.getColumns(); j++) {
				JButton button = new JButton("");
				board[i][j] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setVisible(true);
	}

	// e is the context
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		makeMove(clickedButton);
		GameStatus gs = getGameStatus();
		if (gs != GameStatus.incomplete) {
			declareWinner(gs);
			int option = JOptionPane.showConfirmDialog(this, "Do you want to restart?");
			if (option == JOptionPane.YES_OPTION) {
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						board[i][j].setText("");
					}
				}
				crossTurn = true;
			} else {
				super.dispose();
			}

		}
	}

	public void makeMove(JButton button) {
		if (button.getText().length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");
			return;
		}
		if (crossTurn) {
			button.setText("X");
		} else {
			button.setText("0");
		}
		crossTurn = !crossTurn;
	}

	public GameStatus getGameStatus() {
		int row = 0, col = 0;
		String text1 = "", text2 = "";
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = board[row][col].getText();
				text2 = board[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				col++;
			}
			if (col == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return GameStatus.Xwins;
				} else {
					return GameStatus.Zwins;
				}
			}
			row++;
		}
		row = 0;
		col = 0;
		text1 = "";
		text2 = "";
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = board[row][col].getText();
				text2 = board[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				row++;
			}
			if (row == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return GameStatus.Xwins;
				} else {
					return GameStatus.Zwins;
				}
			}
			col++;
		}
		row = 0;
		while (row < BOARD_SIZE - 1) {
			text1 = board[row][row].getText();
			text2 = board[row + 1][row + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row++;
		}
		if (row == BOARD_SIZE - 1) {
			if (text1.equals("X")) {
				return GameStatus.Xwins;
			} else {
				return GameStatus.Zwins;
			}
		}
		row = 0;
		while (row < BOARD_SIZE - 1) {
			text1 = board[row][BOARD_SIZE - 1 - row].getText();
			text2 = board[row + 1][BOARD_SIZE - 2 - row].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row++;
		}
		if (row == BOARD_SIZE - 1) {
			if (text1.equals("X")) {
				return GameStatus.Xwins;
			} else {
				return GameStatus.Zwins;
			}
		}
		for (row = 0; row < BOARD_SIZE; row++) {
			for (col = 0; col < BOARD_SIZE; col++) {
				if (board[row][col].getText().length() == 0) {
					return GameStatus.incomplete;
				}
			}
		}
		return GameStatus.tie;
	}

	public void declareWinner(GameStatus gs) {
		if (gs == GameStatus.Xwins) {
			JOptionPane.showMessageDialog(this, "X wins");
		} else if (gs == GameStatus.Zwins) {
			JOptionPane.showMessageDialog(this, "0 wins");
		} else {
			JOptionPane.showMessageDialog(this, "It's a tie");
		}
	}
}
