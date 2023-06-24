package NumberGuesser;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuesser extends JFrame {

    JTextField number;
    JButton guess;
    private int correct = 10;
    private String[] options = {"New game", "Exit game"};

    NumberGuesser() {
        //numberGen();

        number = new JTextField();
        number.setPreferredSize(new Dimension(200,40));

        guess = new JButton("Guess");
        guess.setFocusable(false);
        guess.addActionListener(e -> {
            if (e.getSource() == guess) {
                numberCompare(Integer.valueOf(number.getText()));
            }
        });

        JLabel logo = new JLabel();
        logo.setText("Number Guesser");
        logo.setFont(new Font(null, Font.BOLD, 50));

        JLabel description = new JLabel();
        description.setText("Guess the a number from 1-99 to begin!");
        description.setFont(new Font(null, Font.PLAIN, 20));

        this.setTitle("Number Guesser"); //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application when closed
        this.setLayout(new FlowLayout());
        this.setSize(500,500); //sets width and height
        this.add(logo);
        this.add(description);
        this.add(number);
        this.add(guess);
        this.setVisible(true); //make frame visible
    }

    private void numberGen(){
        Random gen = new Random();
        correct = gen.nextInt(100);
    }
    private void numberCompare(int num) {
        int diff = correct - num;
        if (diff == 0) {
            if(JOptionPane.showOptionDialog(null, "Congratulations, you guessed the number!", "Nice", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0)==1){
                this.dispose();
            }
            else {
                numberGen();
            }
        }
        else {
            if (diff < 0) {
                JOptionPane.showMessageDialog(null, "Lower!", "Hint", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Higher!", "Hint", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
