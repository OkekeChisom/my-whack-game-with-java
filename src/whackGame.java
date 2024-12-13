import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;

public class whackGame {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Mario: whack Game");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new  JPanel();

    // create an array fo the nine buttons
    JButton[] board = new JButton[9];
    ImageIcon moleIcon;
    ImageIcon plantIcon;

    JButton currentMoleTile;
    JButton currentPlantTile;

    Random random = new Random();
    Timer setMoleTimer;
    Timer setPlantTimer;
    int score = 0;


    whackGame() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN, 50 ));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("score: 0");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // boardPanel.setBackground(Color.BLACK);
        boardPanel.setLayout(new GridLayout(3,3));
        frame.add(boardPanel);

        Image plantImg = new ImageIcon(getClass().getResource("./fire.png")).getImage();
        plantIcon = new ImageIcon(plantImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

        Image moleImg = new ImageIcon(getClass().getResource("./mole.png")).getImage();
        moleIcon = new ImageIcon(moleImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
        
        
        
        // // create a loop for the nine buttons
        for ( int i = 0; i < 9; i++) {
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            tile.setFocusable(false);
            // tile.setIcon(moleIcon);

            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton tile = (JButton) e.getSource();
                    if ( tile == currentMoleTile) {
                        score  += 10;
                        textLabel.setText("score: " + Integer.toString(score));
                    }
                    else if ( tile == currentPlantTile) {
                        textLabel.setText("Game Over: " + Integer.toString(score));
                        setMoleTimer.stop();
                        setPlantTimer.stop();

                        for ( int i = 0; i < 9; i++) {
                            board[i].setEnabled(false);
                        }

                    }
                }
            });
        }
        
        setMoleTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( currentMoleTile != null) {
                    currentMoleTile.setIcon(null);
                    currentMoleTile = null;
                }
                
                int num = random.nextInt(9); // the number will be from 0 - 8
                JButton tile = board[num];

                // if tile is been occupied by plant, skip this turn
                if ( currentPlantTile == tile) return;
                
                currentMoleTile = tile;
                currentMoleTile.setIcon(moleIcon);
            }
            
        });
        
        setPlantTimer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( currentPlantTile != null) {
                    currentPlantTile.setIcon(null);
                    currentPlantTile = null;
                }
                
                int num = random.nextInt(9); // the number will be from 0 - 8
                JButton tile = board[num];
                
                // if tile is been occupied by mole, skip this turn
                if ( currentMoleTile == tile) return;
                
                currentPlantTile = tile;
                currentPlantTile.setIcon(plantIcon);
            }
            
        });
        setMoleTimer.start();
        setPlantTimer.start();
        frame.setVisible(true);

    }
    


    
}
