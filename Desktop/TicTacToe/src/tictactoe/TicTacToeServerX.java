package tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simple TicTacToe Client Server Application.
 */
public class TicTacToeServerX implements Runnable {

	private String ip = "127.0.0.1";
	private int port = 5000;
	private JFrame frame;
	private final int boardWidth = 500;
	private final int boardHeight = 500;
	private Thread thread;

	private Painter painter;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	private ServerSocket serverSocket;

	private BufferedImage gameBoard;
	private BufferedImage redX;
	private BufferedImage blueX;
	private BufferedImage redO;
	private BufferedImage blueO;

	private String[] boardCells = new String[9];

	private boolean yourTurn = false;
	private boolean OTurn = true;
	private boolean accepted = false;
	private boolean unableToCommunicateWithOpponent = false;
	private boolean won = false;
	private boolean opponentWon = false;
	private boolean tie = false;

	private int lengthOfSpace = 160;
	private int errors = 0;
	private int firstSpot = -1;
	private int secondSpot = -1;

	private Font font = new Font("Verdana", Font.BOLD, 32);
	private Font smallerFont = new Font("Verdana", Font.BOLD, 20);
	private Font largerFont = new Font("Verdana", Font.BOLD, 50);

	private String waitingString = "Waiting for another player";
	private String unableToCommunicateWithOpponentString = "Unable to communicate with opponent.";
	private String wonString = "You won!";
	private String OpponentWonString = "Opponent won!";
	private String tieString = "Game ended in a tie.";

	// storing all TicTacToe winning possibilities into winCases array  
	private int[][] winCases = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, 
							{ 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

	/**
	 * TicTacToe Constructor which loads TicTacToe game board and 
	 * initializes the server if it is not yet started, creates 
	 * thread for each player
	 */
	public TicTacToeServerX() {

		loadImages();

		painter = new Painter();
		painter.setPreferredSize(new Dimension(boardWidth, boardWidth));

		if (!connect()) initializeServer();

		frame = new JFrame();
		frame.setTitle("Tic-Tac-Toe Player X");
		frame.setContentPane(painter);
		frame.setSize(boardWidth, boardWidth);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		thread = new Thread(this, "TicTacToe");
		thread.start();
	}

	/**
	 * On thread start this method executes to handle player input
	 * repaint the board accordingly 
	 */
	public void run() {
		while (true) {
			tick();
			painter.repaint();
			if (!OTurn && !accepted) {
				listenForClientRequest();
			}
		}
	}
	
	/**
	 * Images are rendered on the game board based on the (x,y) coordinates
	 * @param g
	 */
	private void render(Graphics g) {
		g.drawImage(gameBoard, 0, 0, null);
		if (unableToCommunicateWithOpponent) {
			g.setColor(Color.RED);
			g.setFont(smallerFont);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			int stringWidth = g2.getFontMetrics().stringWidth(unableToCommunicateWithOpponentString);
			g.drawString(unableToCommunicateWithOpponentString, boardWidth / 2 - stringWidth / 2, boardHeight / 2);
			return;
		}

		if (accepted) {
			for (int i = 0; i < boardCells.length; i++) {
				if (boardCells[i] != null) {
					if (boardCells[i].equals("X")) {
						if (OTurn) {
							g.drawImage(redX, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						} else {
							g.drawImage(blueX, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						}
					} else if (boardCells[i].equals("O")) {
						if (OTurn) {
							g.drawImage(blueO, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						} else {
							g.drawImage(redO, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3), null);
						}
					}
				}
			}
			if (won || opponentWon) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(10));
				g.setColor(Color.BLACK);
				g.drawLine(firstSpot % 3 * lengthOfSpace + 10 * firstSpot % 3 + lengthOfSpace / 2, (int) (firstSpot / 3) * lengthOfSpace + 10 * (int) (firstSpot / 3) + lengthOfSpace / 2, secondSpot % 3 * lengthOfSpace + 10 * secondSpot % 3 + lengthOfSpace / 2, (int) (secondSpot / 3) * lengthOfSpace + 10 * (int) (secondSpot / 3) + lengthOfSpace / 2);

				g.setColor(Color.RED);
				g.setFont(largerFont);
				if (won) {
					int stringWidth = g2.getFontMetrics().stringWidth(wonString);
					g.drawString(wonString, boardWidth / 2 - stringWidth / 2, boardHeight / 2);
				} else if (opponentWon) {
					int stringWidth = g2.getFontMetrics().stringWidth(OpponentWonString);
					g.drawString(OpponentWonString, boardWidth / 2 - stringWidth / 2, boardHeight / 2);
				}
			} 
			else if (tie) {
				Graphics2D g2 = (Graphics2D) g;
				g.setColor(Color.YELLOW);
				g.setFont(font);
				int stringWidth = g2.getFontMetrics().stringWidth(tieString);
				g.drawString(tieString, boardWidth / 2 - stringWidth / 2, boardHeight / 2);
			}
		} else {
			g.setColor(Color.RED);
			g.setFont(font);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			int stringWidth = g2.getFontMetrics().stringWidth(waitingString);
			g.drawString(waitingString, boardWidth / 2 - stringWidth / 2, boardHeight / 2);
		}
	}

	/**
	 * This method synchronizes the game between the players
	 * takes player input and stores in an array.
	 * Checks for game state (win/lose/tie)  
	 */
	private void tick() {
		if (errors >= 10) unableToCommunicateWithOpponent = true;

		if (!yourTurn && !unableToCommunicateWithOpponent) {
			try {
				int space = dis.readInt();
				if (OTurn) 
					boardCells[space] = "X";
				else 
					boardCells[space] = "O";
				checkForOpponentWin();
				checkForTie();
				yourTurn = true;
			} catch (IOException e) {
				e.printStackTrace();
				errors++;
			}
		}
	}

	/**
	 * Method to check if the player wins the game or not after every move
	 */
	private void checkForWin() {
		for (int i = 0; i < winCases.length; i++) {
			if (OTurn) {
				if (boardCells[winCases[i][0]] == "O" && boardCells[winCases[i][1]] == "O" && boardCells[winCases[i][2]] == "O") {
					firstSpot = winCases[i][0];
					secondSpot = winCases[i][2];
					won = true;
				}
			} else {
				if (boardCells[winCases[i][0]] == "X" && boardCells[winCases[i][1]] == "X" && boardCells[winCases[i][2]] == "X") {
					firstSpot = winCases[i][0];
					secondSpot = winCases[i][2];
					won = true;
				}
			}
		}
	}

	/**
	 * Method to check if the opponent player wins the game or not after every move
	 */
	private void checkForOpponentWin() {
		for (int i = 0; i < winCases.length; i++) {
			if (OTurn) {
				if (boardCells[winCases[i][0]] == "X" && boardCells[winCases[i][1]] == "X" && boardCells[winCases[i][2]] == "X") {
					firstSpot = winCases[i][0];
					secondSpot = winCases[i][2];
					opponentWon = true;
				}
			} else {
				if (boardCells[winCases[i][0]] == "O" && boardCells[winCases[i][1]] == "O" && boardCells[winCases[i][2]] == "O") {
					firstSpot = winCases[i][0];
					secondSpot = winCases[i][2];
					opponentWon = true;
				}
			}
		}
	}

	/**
	 * Method to check for stalemate in the game
	 */
	private void checkForTie() {
		for (int i = 0; i < boardCells.length; i++) {
			if (boardCells[i] == null) {
				tie = false;
				return;
			}
		}
		if(!(won||opponentWon))
			tie = true;
	}

	/**
	 * Method to listen for Client Request 
	 */
	private void listenForClientRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method creates a server socket
	 * @return boolean
	 */
	private boolean connect() {
		try {
			System.out.println("Starting a server");
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
		} catch (IOException e) {
			return false;
		}
		System.out.println("Successfully connected to the server.");
		return true;
	}

	/**
	 * Initializes the server socket
	 */
	private void initializeServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}
		yourTurn = true;
		OTurn = false;
	}

	/**
	 * Loads external images to buffered image objects 
	 */
	private void loadImages() {
		try {
			gameBoard = ImageIO.read(getClass().getResourceAsStream("/gameBoard.png"));
			redX = ImageIO.read(getClass().getResourceAsStream("/redX.png"));
			redO = ImageIO.read(getClass().getResourceAsStream("/redO.png"));
			blueX = ImageIO.read(getClass().getResourceAsStream("/blueX.png"));
			blueO = ImageIO.read(getClass().getResourceAsStream("/blueO.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TicTacToeServerX ticTacToeServer = new TicTacToeServerX();
	}
	
	/**
	 * Painter subclass listens for mouse click on the panel 
	 * Renders the image objects for every click 
	 */
	private class Painter extends JPanel implements MouseListener {
		private static final long serialVersionUID = 1L;
		
		/**
		 * Method which gives focus to the game board
		 * sets background color and adds mouse listner to it.
		 */
		public Painter() {
			setFocusable(true);
			requestFocus();
			setBackground(Color.WHITE);
			addMouseListener(this);
		}

		/**
		 * PaintComponent renders the image objects on the JPanel
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			render(g);
		}
		
		/**
		 * Method which triggers on a mouse click and handles players
		 * input on the game board 
		 */
		public void mouseClicked(MouseEvent e) {
			if (accepted) {
				if (yourTurn && !unableToCommunicateWithOpponent && !won && !opponentWon) {
					int x = e.getX() / lengthOfSpace;
					int y = e.getY() / lengthOfSpace;
					y *= 3;
					int position = x + y;

					if (boardCells[position] == null) {
						if (!OTurn) boardCells[position] = "X";
						else boardCells[position] = "O";
						yourTurn = false;
						repaint();
						Toolkit.getDefaultToolkit().sync();

						try {
							dos.writeInt(position);
							dos.flush();
						} catch (IOException e1) {
							errors++;
							e1.printStackTrace();
						}

						System.out.println("DATA WAS SENT");
						checkForWin();
						checkForTie();
					}
				}
			}
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}
		
		public void mouseExited(MouseEvent e) {

		}

	}

}
