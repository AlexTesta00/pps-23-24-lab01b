package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import e2.entity.GameType;
import e2.entity.GameEntityCell;
import e2.utils.GameState;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    private final static String MINE_SYMBOL = "*";
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size, int numberOfMines) throws InstantiationException {
        this.logics = new LogicsImpl(size, numberOfMines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            GameState currentState = this.logics.reveal(pos);
            boolean aMineWasFound = currentState.equals(GameState.LOSE); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                drawBoard();            	
            }
            boolean isThereVictory = currentState.equals(GameState.WIN); // call the logic here to ask if there is victory
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    // call the logic here to put/remove a flag
                    logics.toogleFlagCell(pos);
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a mine, draw it "*"
            // disable the button
            Pair<Integer, Integer> position = new Pair<Integer,Integer>(entry.getValue().getX(), entry.getValue().getY());
            Optional<GameEntityCell> cell = this.logics.getCellAtPosition(position);
            if(cell.isPresent() && cell.get().getType() == GameType.MINE){
                entry.getKey().setText(MINE_SYMBOL);
            }
            entry.getKey().setEnabled(false);
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a cell with counter, put the number
            // if this button has a flag, put the flag
            Pair<Integer, Integer> position = new Pair<Integer,Integer>(entry.getValue().getX(), entry.getValue().getY());
            Optional<GameEntityCell> cell = this.logics.getCellAtPosition(position);

            if(cell.isPresent()){
                if(cell.get().isReveald()){
                    if(cell.get().getType() == GameType.MINE){
                        entry.getKey().setText(MINE_SYMBOL);
                    }else{
                        entry.getKey().setText(String.valueOf(this.logics.getNumberOfNeighbourMines(position)));
                    }
                    entry.getKey().setEnabled(false);
                }else if(cell.get().isFlagged()){
                    entry.getKey().setText("F");
                }else{
                    entry.getKey().setText(" ");
                }
            }
    	}
    }
    
}
