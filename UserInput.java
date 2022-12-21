import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInput extends JFrame {
    private static JLabel title_label = new JLabel("Enter size of table");
    private static JTextField sizeInput_txtfield = new JTextField(25);
    private static JButton next_btn = new JButton("Play");
    private static int sizeInput;
    JFrame frame = new JFrame();

    public void CreateWindow(){
        frame.setSize(300,330);
        frame.setVisible(true);
        frame.setTitle("Tic-Tac-Toe");
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(title_label);
        frame.getContentPane().add(sizeInput_txtfield);
        frame.getContentPane().add(next_btn);
        frame.pack();

        next_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(CheckInput()){
                    TicTacToe start = new TicTacToe();
                    start.CreateGame(sizeInput);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter number", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static boolean CheckInput(){
        try {
            sizeInput = Integer.parseInt(sizeInput_txtfield.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
