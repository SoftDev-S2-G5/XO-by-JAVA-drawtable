import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class TicTacToe implements ActionListener{
    public int tablesize, count;
    String currentPlayer = "x";

    Random random = new Random();
    JFrame frame = new JFrame();

    JPanel title_panel = new JPanel();
    JPanel option_panel = new JPanel();
    JPanel playButton_panel = new JPanel();
    JPanel optionButton_panel = new JPanel();

    JLabel textfield = new JLabel();

    JButton[][] buttons;
    JButton playAgain_btn = new JButton("Reset");
    JButton changeSize_btn = new JButton("Size");
    JButton save_btn = new JButton("Save");
    JButton load_btn = new JButton("Load");
    JButton exit_btn = new JButton("Exit");

    public void CreateGame(int tableSize){
        tablesize = tableSize;
        buttons = new JButton[tableSize][tableSize];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(211, 84, 0));
        textfield.setForeground(Color.BLACK);
        textfield.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,70);

        option_panel.setLayout(new BorderLayout());
        option_panel.setBounds(0,0,300,300);
        optionButton_panel.setBackground(new Color(211, 84, 0));


        playButton_panel.setLayout(new GridLayout(tableSize,tableSize));

        for(int i = 0; i < tableSize; i++){
            for(int j = 0; j < tableSize; j++){
                buttons[i][j] = new JButton();
                playButton_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,80));
                buttons[i][j].setBackground(new Color(237, 187, 153));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }

        changeSize_btn.setPreferredSize(new Dimension(80,50));
        playAgain_btn.setPreferredSize(new Dimension(80,50));
        save_btn.setPreferredSize(new Dimension(80,50));
        load_btn.setPreferredSize(new Dimension(80,50));
        exit_btn.setPreferredSize(new Dimension(80,50));

        optionButton_panel.add(changeSize_btn);
        optionButton_panel.add(playAgain_btn);
        optionButton_panel.add(save_btn);
        optionButton_panel.add(load_btn);
        optionButton_panel.add(exit_btn);

        title_panel.add(textfield);
        option_panel.add(optionButton_panel);

        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(playButton_panel,BorderLayout.CENTER);
        frame.add(option_panel,BorderLayout.SOUTH);
        
        changeSize_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                UserInput start = new UserInput();
                start.CreateWindow();
                frame.dispose();
            }
        });
        

        playAgain_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                for(int i = 0; i < tableSize; i++){
                    for(int j = 0; j < tablesize; j++){
                        buttons[i][j].setEnabled(true);
                        buttons[i][j].setBackground(new Color(237, 187, 153));
                        buttons[i][j].setText("");
                    }
                }
                count = 0;
                textfield.setForeground(Color.BLACK);
                textfield.setText("Tic-Tac-Toe");
            }
        });

        save_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SaveGame();
            }
        });

        load_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                LoadGame();
            }
        });
        

        exit_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < tablesize; i++){
            for(int j = 0; j < tablesize; j++){
                if(e.getSource() == buttons[i][j]){
                    if(currentPlayer == "x"){
                        if(buttons[i][j].getText() != ""){
                            JOptionPane.showMessageDialog(null, "Already used.", null, JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            buttons[i][j].setForeground(new Color(255,0,0));
                            buttons[i][j].setText("x");
                            change_player();
                            textfield.setText("o turn");
                            if(check()){
                                ButtonEnable();
                                textfield.setForeground(Color.GREEN);
                                textfield.setText("x wins");
                            }
                        }
                    }else{
                        if(buttons[i][j].getText() != ""){
                            JOptionPane.showMessageDialog(null, "Already used.", null, JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            buttons[i][j].setForeground(new Color(0,0,255));
                            buttons[i][j].setText("o");
                            change_player();
                            textfield.setText("x turn");
                            if(check()){
                                ButtonEnable();
                                textfield.setForeground(Color.GREEN);
                                textfield.setText("o wins");
                            }
                        }
                    }
                }
            }
        }
        if(!check() && count == tablesize * tablesize){
            for(int i = 0; i < tablesize; i++){
                for(int j = 0; j < tablesize; j++){
                    buttons[i][j].setEnabled(false);
                }
            }
            textfield.setText("draw");
        }
    }

    void change_player(){
        if (this.currentPlayer.equals("x")){
            this.currentPlayer = "o";
        }
        else{
            this.currentPlayer = "x";
        }
        this.count++;
    }


    public boolean check(){
        String[] checker = new String[tablesize];
        for (int i=0;i<tablesize;i++){
            if(this.currentPlayer.equals("x")){
                checker[i] = "o";
            }
            else{
                checker[i] = "x";
            }
        }
        String[] horizontal = new String[tablesize];
        String[] temp_hor = new String[tablesize];
        String[] temp_ver = new String[tablesize];
        for (int i=0;i<tablesize;i++){
            for (int x=0;x<tablesize;x++){
                horizontal[x] = buttons[i][x].getText();
            }
            if(Arrays.deepEquals(checker, horizontal)){      // Check Row
                return true;
            }
            
            String[] temp = new String[tablesize];
            for (int j=0;j<tablesize;j++){
                temp[j] = buttons[j][i].getText();
            }
            if(Arrays.deepEquals(checker, temp)){            // Check Column
                return true;
            }
            temp_hor[i] = buttons[i][i].getText();
            temp_ver[i] = buttons[i][(tablesize-1)-i].getText();
        }
        if(Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)){     // Check Diagonal
            return true;
        }
        return false;
    }

    public void SaveGame(){
        try {
            FileWriter writer = new FileWriter(new File("save.txt"));
            writer.write(String.valueOf(tablesize));
            writer.write("\n");
            for(int i = 0; i < tablesize; i++){
                for(int j = 0; j < tablesize; j++){
                    if(!buttons[i][j].getText().equals("x") && !buttons[i][j].getText().equals("o")) 
                    {
                        writer.write("n");
                    }else{
                        writer.write(buttons[i][j].getText());
                    }
                }
            }
            writer.write("\n");
            writer.write(currentPlayer);
            writer.write("\n");
            writer.write(String.valueOf(count));
            writer.write("\n");
            writer.close();
            JOptionPane.showMessageDialog(null, "Saved", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadGame(){
        List<String> list = new ArrayList<String>();  
        try {
            BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
            String data;
            while((data = reader.readLine()) != null){
               list.add(data);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        int old_tablesize = Integer.parseInt(list.get(0));
        if(tablesize == old_tablesize){
            currentPlayer = list.get(2);
            count = Integer.parseInt(list.get(3));
            System.out.println(list.get(1));
            char[] XO_list = list.get(1).toCharArray();
            System.out.println(XO_list);
            int XO_listDefaulthIndex = 0;
            for(int i = 0; i < tablesize ; i++){
                for(int j = 0; j < tablesize; j++){
                    buttons[i][j].setForeground(new Color(0,0,255));
                    if(String.valueOf(XO_list[XO_listDefaulthIndex]).equals("n")){
                        buttons[i][j].setText("");
                    }else{
                        if(String.valueOf(XO_list[XO_listDefaulthIndex]).equals("x")){
                            buttons[i][j].setForeground(new Color(255,0,0));
                        }else{
                            buttons[i][j].setForeground(new Color(0,0,255));
                        }
                        buttons[i][j].setText(String.valueOf(XO_list[XO_listDefaulthIndex]));
                    }
                    if(currentPlayer == "x"){
                        textfield.setText("x turn");
                    }else{
                        textfield.setText("o turn");
                    }
                    XO_listDefaulthIndex += 1;
                }
            }
            ShowWinCase();
            JOptionPane.showMessageDialog(null, "Load Finished", null, JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Table size is not the same (Table size must be " + old_tablesize +").", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void ShowWinCase(){
        if(count == tablesize * tablesize){
            ButtonEnable();
            textfield.setText("draw");
        }else{
            if(check() && currentPlayer.equals("o")){
                ButtonEnable();
                textfield.setForeground(Color.GREEN);
                textfield.setText("x wins");
            }else if(check() && currentPlayer.equals("x")){
                ButtonEnable();
                textfield.setForeground(Color.GREEN);
                textfield.setText("o wins");
            }
        }
    }

    public void ButtonEnable(){
        for(int x = 0; x < tablesize; x++){
            for(int y = 0; y < tablesize; y++){
                buttons[x][y].setEnabled(false);
            }
        }
    }
}
