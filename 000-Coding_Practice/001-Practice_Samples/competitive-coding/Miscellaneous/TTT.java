package Miscellaneous;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	public static final int BOARD_SIZE = 3;
	public static final JButton[][] board = new JButton[BOARD_SIZE][BOARD_SIZE];

	public static final String XMOVE = "X";
	public static final String ZMOVE = "0";

	public static final int XWins = 0;
	public static final int ZWins = 1;
	public static final int Tie = 2;
	public static final int Incomplete = 3;

	public boolean crossTurn = true;

	public TTT() {
		super.setTitle("Tic Tac Toe");
		super.setSize(800, 800);

		GridLayout layout = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(layout);

		Font font = new Font("Times New Roman", 1, 225);
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				JButton button = new JButton("");

				button.setFont(font);
				button.addActionListener(this);

				this.board[row][col] = button;
				super.add(button);
			}
		}

		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		makeMove(btn);
		int gs = getGameStatus();

		if (gs != Incomplete) {
			declareWinner(gs);

			int res = JOptionPane.showConfirmDialog(null, "Restart?");

			if (res == JOptionPane.YES_OPTION) {
				for (int row = 0; row < BOARD_SIZE; row++) {
					for (int col = 0; col < BOARD_SIZE; col++) {
						this.board[row][col].setText("");
					}
				}

				crossTurn = true;
			} else {
				super.dispose();
			}
		}
	}

	private void makeMove(JButton btn) {
		if (btn.getText().length() == 0) {
			if (crossTurn) {
				btn.setText(XMOVE);
			} else {
				btn.setText(ZMOVE);
			}

			crossTurn = !crossTurn;
		} else {
			JOptionPane.showMessageDialog(this, "Invalid move!");
		}
	}

	private int getGameStatus() {
		int row = 0, col = 0;
		String text1 = "", text2 = "";

		// check rows
		row = 0;
		while (row < BOARD_SIZE) {
			col = 0;

			while (col < BOARD_SIZE - 1) {
				text1 = this.board[row][col].getText();
				text2 = this.board[row][col + 1].getText();

				if (text1 != text2 || text1.length() == 0) {
					break;
				}

				col++;
			}

			if (col == BOARD_SIZE - 1) {
				return text1.equals(ZMOVE) ? ZWins : XWins;
			}

			row++;
		}

		// check columns
		col = 0;
		while (col < BOARD_SIZE) {
			row = 0;

			while (row < BOARD_SIZE - 1) {
				text1 = this.board[row][col].getText();
				text2 = this.board[row + 1][col].getText();

				if (text1 != text2 || text1.length() == 0) {
					break;
				}

				row++;
			}

			if (row == BOARD_SIZE - 1) {
				return text1.equals(ZMOVE) ? ZWins : XWins;
			}

			col++;
		}

		// check diagonal 1
		row = 0;
		col = 0;
		while (row < BOARD_SIZE - 1 && col < BOARD_SIZE - 1) {
			text1 = this.board[row][col].getText();
			text2 = this.board[row + 1][col + 1].getText();

			if (text1 != text2 || text1.length() == 0) {
				break;
			}

			row++;
			col++;
		}

		if (row == BOARD_SIZE - 1) {
			return text1.equals(ZMOVE) ? ZWins : XWins;
		}

		// check diagonal 2
		row = 0;
		col = BOARD_SIZE - 1;
		while (row < BOARD_SIZE - 1 && col > 0) {
			text1 = this.board[row][col].getText();
			text2 = this.board[row + 1][col - 1].getText();

			if (text1 != text2 || text1.length() == 0) {
				break;
			}

			row++;
			col--;
		}

		if (row == BOARD_SIZE - 1) {
			return text1.equals(ZMOVE) ? ZWins : XWins;
		}

		// check incompleteness
		row = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE) {
				text1 = this.board[row][col].getText();

				if (text1.length() == 0) {
					return Incomplete;
				}

				col++;
			}

			row++;
		}

		return Tie;
	}

	private void declareWinner(int gs) {
		if (gs == XWins) {
			JOptionPane.showMessageDialog(null, "X wins");
		} else if (gs == ZWins) {
			JOptionPane.showMessageDialog(null, "0 wins");
		} else {
			JOptionPane.showMessageDialog(null, "Its a tie");
		}
	}
}
