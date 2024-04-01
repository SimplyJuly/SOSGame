package sprint3_1.product;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sprint3_1.product.Game.Cell;
import sprint3_1.product.Game.GameState;


/* 
 * The GUI code was originally written by 
 * Prof. Chua Hock Chuan, Nanyang Technological University 
 */

@SuppressWarnings("serial")
public class TicTacToeGUI extends JFrame {

	public static final int CELL_SIZE = 100; 
	public static final int GRID_WIDTH = 8; 
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 
	
	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
	public static final int SYMBOL_STROKE_WIDTH = 8;

	private int CANVAS_WIDTH; 
	private int CANVAS_HEIGHT;

	private GameBoardCanvas gameBoardCanvas; 
	private JLabel gameStatusBar;  
	
	private JPanel gameSettings;
	
	private JPanel gameOptions;
	private JButton newGame;
	private JLabel sos;
	private JRadioButton generalGameButton;
	private JRadioButton simpleGameButton;
	
	private JPanel boardSizeSelection;
	private JLabel boardSizeLabel;
	private JTextField boardSizeInput;

	private Game game;
	private String gameMode = "Simple Game";

	public TicTacToeGUI(Game game) {
		this.game = game;
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("Tic Tac Toe");
		setVisible(true);  
	}
	
	private void setContentPane(){
		game = new SimpleGame();
		
		gameBoardCanvas = new GameBoardCanvas();  
		CANVAS_WIDTH = CELL_SIZE * game.getTotalRows();  
		CANVAS_HEIGHT = (CELL_SIZE * game.getTotalColumns());
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		
		gameStatusBar = new JLabel("  ");
		gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		
		boardSizeSelection = new JPanel();
		boardSizeLabel = new JLabel("Board Size");
		boardSizeInput = new JTextField(2);
		newGame = new JButton("New Game");
		boardSizeSelection.add(boardSizeLabel);
		boardSizeSelection.add(boardSizeInput);
		boardSizeSelection.add(newGame);
		
		gameOptions = new JPanel();
		sos = new JLabel("SOS");
		simpleGameButton = new JRadioButton("Simple Game");
		generalGameButton = new JRadioButton("General Game");
		simpleGameButton.addActionListener(actionListener);
		generalGameButton.addActionListener(actionListener);
		gameOptions.add(sos);
		gameOptions.add(simpleGameButton);
		gameOptions.add(generalGameButton);
		
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(simpleGameButton);
        buttonGroup.add(generalGameButton);
        simpleGameButton.setSelected(true);

		gameSettings = new JPanel();
		gameSettings.setLayout(new BoxLayout(gameSettings, BoxLayout.Y_AXIS));
		gameSettings.add(gameOptions);
		gameSettings.add(boardSizeSelection);
		

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gameSettings, BorderLayout.PAGE_START);
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		contentPane.add(gameStatusBar, BorderLayout.PAGE_END); 
		
		
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int size = Integer.parseInt(boardSizeInput.getText());
            	System.out.println(size);
            	game.updateBoardSize(size);
        		CANVAS_WIDTH = CELL_SIZE * game.getTotalRows();  
        		CANVAS_HEIGHT = (CELL_SIZE * game.getTotalColumns());
        		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
            }
        });
	}

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            gameMode = abstractButton.getText();
            System.out.println("Selected: " + gameMode);
            
            if (gameMode.equals("Simple Game")) {
                game = new SimpleGame();
            } else if (gameMode.equals("General Game")) {
                game = new GeneralGame();
            }
            
        }
    };
    
	class GameBoardCanvas extends JPanel {
		
		GameBoardCanvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
					if (game.getGameState() == GameState.PLAYING) {
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						game.makeMove(rowSelected, colSelected);
					} else {
						game.resetGame(); 
						System.out.println("Line 136, TicTacToeGUI: where the game resets");
					}
					repaint();  
					printStatusBar();
//					System.out.println("Line 149, TicTacToeGUI: repaint has been run");
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawBoard(g);
			printStatusBar();
		}
		
		private void drawGridLines(Graphics g){
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < game.getTotalRows(); ++row) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
						CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < game.getTotalColumns(); ++col) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
						GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
			}

		}

		private void drawBoard(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
			for (int row = 0; row < game.getTotalRows(); ++row) {
				for (int col = 0; col < game.getTotalColumns(); ++col) {
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if (game.getCell(row,col) == Cell.S) {
						g2d.setColor(Color.RED);
						
						int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
					    int middleY = (y1 + y2) / 2;
						
					    g2d.drawLine(x1, y1, x2, y1);
					    g2d.drawLine(x1, y1, x1, middleY);
					    g2d.drawLine(x1, middleY, x2, middleY);
					    g2d.drawLine(x2, middleY, x2, y2);
					    g2d.drawLine(x1, y2, x2, y2);
							
					} else if (game.getCell(row,col) == Cell.NOUGHT) {
						g2d.setColor(Color.BLUE);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
			}
		}

		private void printStatusBar(){
			if (game.getGameState() == GameState.PLAYING) {
				gameStatusBar.setForeground(Color.BLACK);
				if (game.getTurn() == 'S') {
					gameStatusBar.setText(gameMode + ": S's Turn");
				} else {
					gameStatusBar.setText(gameMode + ": O's Turn");
				}
			} else if (game.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("It's a Draw! Click to play again.");
			} else if (game.getGameState() == GameState.S_WON) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("'S' Won! Click to play again.");
			} else if (game.getGameState()== GameState.NOUGHT_WON) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("'O' Won! Click to play again.");
			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TicTacToeGUI(new SimpleGame()); 
			}
		});
	}
}
