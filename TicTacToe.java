import java.applet.Applet;
import java.applet.AudioClip;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.net.URL;

/**
 * A class modelling a tic-tac-toe
 * 
 * @author Jatin Jain - 101184197
 * @version March 30, 2022
 */

public class TicTacToe extends MouseAdapter implements ActionListener
{
   private JButton[][] board; //2d array 
   private JLabel currentLabel;
   private JMenuItem newGame;
   private JMenuItem quitGame;

   AudioClip click;


   public static final String PLAYER_X = "X"; // player using "X"
   public static final String PLAYER_O = "O"; // player using "O"
   public static final String TIE = "T"; // game ended in a tie
 
   private String player;   // current player (PLAYER_X or PLAYER_O)

   private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

   private int numFreeSquares; // number of squares still free
   
   private boolean gameOver; //check if game was won or tied
      
   /** 
    * Constructs a new Tic-Tac-Toe board.
    */
   public TicTacToe()  
   {
       JFrame frame = new JFrame("Tic-Tac-Toe");
       
       board = new JButton[3][3];
       
       JPanel bPanel = new JPanel(new GridLayout(3,3));
       bPanel.setBounds(0,0,150,150);
       
       JPanel lPanel = new JPanel(new GridLayout());
       lPanel.setBounds(0,150,200,150);
       currentLabel = new JLabel("");
       lPanel.add(currentLabel);
       
       frame.setVisible(true); 
       frame.setSize(500,500); 
       frame.setResizable(true);
       frame.setLayout(null);
       frame.add(bPanel);
       frame.add(lPanel);
       
        
       for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
         JButton button = new JButton();
         board[i][j] = button;
         bPanel.add(board[i][j]);
         board[i][j].setText(""); 
         board[i][j].setEnabled(false); 
         board[i][j].addActionListener(this);
         }
       }
       JMenuBar menuBar = new JMenuBar(); //menu that will hold new and quit
       JMenu gameMenu= new JMenu("Game");
       gameMenu.addMouseListener(this);
       
       newGame = new JMenuItem("New");;
       quitGame = new JMenuItem("Quit");
        
       newGame.addActionListener(this);
       quitGame.addActionListener(this);
        
       gameMenu.add(newGame);
       gameMenu.add(quitGame);
       menuBar.add(gameMenu);
       frame.setJMenuBar(menuBar);
        
       newGame.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
       quitGame.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
       
       
       playGame();
       
       
   }
   

   /**
    * Plays one game of Tic Tac Toe.
    */

   public void playGame()
   {
        player = PLAYER_X; // X is always the starting player.
        winner = ""; // reset winner
        numFreeSquares = 9;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText(""); // clears all the button text for the new game.
                board[i][j].setEnabled(true); // makes button clickable upon game start.
            }
        }     
       
    

   } 


   /**
    * Returns true if filling the given square gives us a winner, and false
    * otherwise.
    *
    * @param int row of square just set
    * @param int col of square just set
    * 
    * @return true if we have a winner, false otherwise
    */
   private boolean haveWinner(int row, int col) 
   {
       // unless at least 5 squares have been filled, we don't need to go any further
       // (the earliest we can have a winner is after player X's 3rd move).

       if (numFreeSquares>5) return false;

       // Note: We don't need to check all rows, columns, and diagonals, only those
       // that contain the latest filled square.  We know that we have a winner 
       // if all 3 squares are the same, as they can't all be blank (as the latest
       // filled square is one of them).

       // check row "row"
       if ( board[row][0].getText().equals(board[row][1].getText()) &&
            board[row][0].getText().equals(board[row][2].getText()) ) return true;
       
       // check column "col"
       if ( board[0][col].getText().equals(board[1][col].getText()) &&
            board[0][col].getText().equals(board[2][col].getText()) ) return true;

       // if row=col check one diagonal
       if (row==col)
          if ( board[0][0].getText().equals(board[1][1].getText()) &&
               board[0][0].getText().equals(board[2][2].getText()) ) return true;

       // if row=2-col check other diagonal
       if (row==2-col)
          if ( board[0][2].getText().equals(board[1][1].getText()) &&
               board[0][2].getText().equals(board[2][0].getText()) ) return true;

       // no winner yet
       return false;
   }
   
   /**
    * Performs action when button or menu item is clicked 
    * @param ActionEvent e the event received by Action Listener
    */
   public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        
        if (o instanceof JButton){
            JButton button = (JButton)o;
            //If a button is clicked have the click sound play
            URL urlClick = TicTacToe.class.getResource("click.wav"); // click file
            click = Applet.newAudioClip(urlClick);
            click.play(); // just plays clip once
            
            if (button == board[0][0]){
                board[0][0].setText(player);
                board[0][0].setEnabled(false);
                gameOver = haveWinner(0,0);
            }
            if (button == board[0][1]){
                board[0][1].setText(player);
                board[0][1].setEnabled(false);
                gameOver = haveWinner(0,1);
            }
            if (button == board[0][2]){
                board[0][2].setText(player);
                board[0][2].setEnabled(false);
                gameOver = haveWinner(0,2);
            }
            if (button == board[1][0]){
                board[1][0].setText(player);
                board[1][0].setEnabled(false);
                gameOver = haveWinner(1,0);
            }
            if (button == board[1][1]){
                board[1][1].setText(player);
                board[1][1].setEnabled(false);
                gameOver = haveWinner(1,1);
            }
            if (button == board[1][2]){
                board[1][2].setText(player);
                board[1][2].setEnabled(false);
                gameOver = haveWinner(1,2);
            }
            if (button == board[2][0]){
                board[2][0].setText(player);
                board[2][0].setEnabled(false);
                gameOver = haveWinner(2,0);
            }
            if (button == board[2][1]){
                board[2][1].setText(player);
                board[2][1].setEnabled(false);
                gameOver = haveWinner(2,1);
            }
            if (button == board[2][2]){
                board[2][2].setText(player);
                board[2][2].setEnabled(false);
                gameOver = haveWinner(2,2);
            }
            
            numFreeSquares = numFreeSquares-1;
            
            if (gameOver){
                currentLabel.setText("Game Over: " + player + " wins" );
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        board[i][j].setEnabled(false); // makes button unclickable upon game start.
                    }
                }     
            }
            
            else if (numFreeSquares <= 0) {
                currentLabel.setText("Tie Game");
            }
            
            else{
                currentLabel.setText("Game In Progress: " + player + "'s turn" );
            }
            
            
            if (player == PLAYER_X){
                player = PLAYER_O;
            }
            else{
                player = PLAYER_X;
            }
            
            
            }
            
        if (o instanceof JMenuItem){
            
            JMenuItem menuItem = (JMenuItem) o;
            
            if (menuItem == newGame){
                playGame(); //restart to a new game
            }
            
            if(menuItem == quitGame){
                System.exit(0); // quit
            }
        }
        
        
        }
        
        /**
    * Detects when the mouse enters the component.  We are only "listening" to the
    * JMenu.  We highlight the menu name when the mouse goes into that component.
    * 
    * @param e The mouse event triggered when the mouse was moved into the component
    */
   public void mouseEntered(MouseEvent e) {
        JMenu item = (JMenu) e.getSource();
        item.setSelected(true); // highlight the menu name
   }

   /**
    * Detects when the mouse exits the component.  We are only "listening" to the
    * JMenu.  We stop highlighting the menu name when the mouse exits  that component.
    * 
    * @param e The mouse event triggered when the mouse was moved out of the component
    */
   public void mouseExited(MouseEvent e) {
        JMenu item = (JMenu) e.getSource();
        item.setSelected(false); // stop highlighting the menu name
   }
            
            
            
}
       
    
 


