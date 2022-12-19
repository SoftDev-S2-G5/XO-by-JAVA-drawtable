import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class TicTacToe implements ActionListener{
    public int tablesize;
    List<String> check_horizontal = new ArrayList<>();
    List<String> check_vertical = new ArrayList<>();
    List<String> check_rDiagonal = new ArrayList<>();
    List<String> check_lDiagonal = new ArrayList<>();

    Random random = new Random();
    JFrame frame = new JFrame();

    JPanel title_panel = new JPanel();
    JPanel option_panel = new JPanel();
    JPanel playButton_panel = new JPanel();
    JPanel optionButton_panel = new JPanel();

    JLabel textfield = new JLabel();

    JButton[][] buttons;
    JButton playAgain_btn = new JButton("Play again");
    JButton changeSize_btn = new JButton("Change size");
    JButton exit_btn = new JButton("Exit");

    boolean playerwon_rightDia, playerwon_lefttDia,  playerwon_hori,  playerwon_verti  = true;
    boolean player1_turn;

    public void CreateGame(int tableSize){
        tablesize = tableSize;
        buttons = new JButton[tableSize][tableSize];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        //set text 
        textfield.setBackground(new Color(211, 84, 0));
        textfield.setForeground(Color.BLACK);
        textfield.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);
        //set frame for text 
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,70);
        //set frame for btn option
        option_panel.setLayout(new BorderLayout());
        option_panel.setBounds(0,0,300,300);
        optionButton_panel.setBackground(new Color(211, 84, 0));
        //playing board game
        playButton_panel.setLayout(new GridLayout(tableSize,tableSize));

        for(int i = 0; i < tableSize; i++){
            for(int j = 0; j < tableSize; j++){
                buttons[i][j] = new JButton();
                playButton_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,120));
                buttons[i][j].setBackground(new Color(237, 187, 153));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }

        //generate btn
            //btn's size
        changeSize_btn.setPreferredSize(new Dimension(150,50));
        playAgain_btn.setPreferredSize(new Dimension(150,50));
        exit_btn.setPreferredSize(new Dimension(150,50));

        optionButton_panel.add(changeSize_btn);
        optionButton_panel.add(playAgain_btn);
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
                playerwon_hori = true;
                playerwon_verti = true;
                playerwon_rightDia = true;
                playerwon_lefttDia = true;
                RemoveAllElement();
                textfield.setForeground(Color.BLACK);
                textfield.setText("Tic-Tac-Toe");
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
                    if(player1_turn){
                        if(buttons[i][j].getText() == ""){
                            buttons[i][j].setForeground(new Color(255,0,0));
                            buttons[i][j].setText("X");
                            player1_turn = false;
                            textfield.setText("O turn");
                            check();
                        }
                    }else{
                        if(buttons[i][j].getText() == ""){
                            buttons[i][j].setForeground(new Color(0,0,255));
                            buttons[i][j].setText("O");
                            player1_turn = true;
                            textfield.setText("X turn");
                            check();
                        }
                    }
                }
            }
        }
    }

    public void firstTurn(){
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        if(random.nextInt(2) == 0){
            player1_turn = true;
            textfield.setText("X turn");
        }else{
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

    public void check(){
        // Horizontal
        for(int i = 0 ; i < tablesize ; i++){
            for(int j = 0 ; j < tablesize; j++){
                if(buttons[i][j].getText() == "X"){
                    check_horizontal.add("X");
                }else if(buttons[i][j].getText() == "O"){
                    check_horizontal.add("O");
                }
            }
        }
        // Vertical
        for(int i = 0 ; i < tablesize ; i++){
            for(int j = 0 ; j < tablesize; j++){
                if(buttons[j][i].getText() == "X"){
                    check_vertical.add("X");
                }else if(buttons[j][i].getText() == "O"){
                    check_vertical.add("O");
                }
            }
        }
        // Right Diagonal
        for(int i = 0 ; i < tablesize ; i++){
            if(buttons[i][i].getText() == "X"){
                check_rDiagonal.add("X");
            }else if(buttons[i][i].getText() == "O"){
                check_rDiagonal.add("O");
            }
        }
        // Left Diagonal
        for(int i = 0; i < tablesize ; i++){
            if(buttons[i][tablesize - 1 - i].getText() == "X"){
                check_lDiagonal.add("X");
            }else if(buttons[i][tablesize - 1 - i].getText() == "O"){
                check_lDiagonal.add("O");
            }
        }
        //send the result to terminal
        System.out.println("Horizontal:"+check_horizontal);
        System.out.println("Vertical:"+check_vertical);
        System.out.println("rDiaginal:"+check_rDiagonal);
        System.out.println("lDiagonal:"+check_lDiagonal);

        //call the function
        CheckHorizontal();
        CheckVertical();
        CheckRightDiagonal();
        CheckLeftDiagonal();
        RemoveAllElement();
    }

    //check winner in  each case
    public void CheckHorizontal(){
        for (String s : check_horizontal) {
            if (!s.equals(check_horizontal.get(0))){
                playerwon_hori = false;
                System.out.println("Horizontal list element not the same");
            }   
        }
        if(playerwon_hori && check_horizontal.size() == tablesize){
            System.out.println("Right Diagonal list lenght same as tablesize");
            if(check_horizontal.get(0) == "X"){
                xWins();
                System.out.println("Player X wins (Horizontal)");
            }
            else{
                oWins();
                System.out.println("Player O wins (Horizontall)");
            }
        }
    }

    public void CheckVertical(){
        for (String s : check_vertical) {
            
        }  
    }

    public void CheckRightDiagonal(){
        for (String s : check_rDiagonal) {
            if(!s.equals(check_rDiagonal.get(0))){
                playerwon_rightDia = false;
                System.out.println("Right Diagonal list element not the same");
            }
        }
        if(playerwon_rightDia && check_rDiagonal.size() == tablesize){
            System.out.println("Right Diagonal list lenght same as tablesize");
            if(check_rDiagonal.get(0) == "X"){
                xWins();
                System.out.println("Player X wins (Right Diagonal)");
            }else{
                oWins();
                System.out.println("Player O wins (Right Diagonal)");
            }
        }
    }

    public void CheckLeftDiagonal(){
        for (String s : check_lDiagonal) {
            if(!s.equals(check_lDiagonal.get(0))){
                playerwon_lefttDia = false;
                System.out.println("Left Diagonal list element not the same");
            }
        }
        if(playerwon_lefttDia && check_lDiagonal.size() == tablesize){
            System.out.println("Left Diagonal list lenght same as tablesize");
            if(check_lDiagonal.get(0) == "X"){
                xWins();
                System.out.println("Player X wins (Left Diagonal)");
            }else{
                oWins();
                System.out.println("Player O wins (Left Diagonal)");
            }
        }
    }

    //Display aftter know the winner
    public void xWins(){
        for(int i = 0; i < tablesize; i++){
            for(int j = 0; j < tablesize; j++){
                buttons[i][j].setEnabled(false);
            }
        }
        textfield.setForeground(Color.GREEN);
        textfield.setText("X Wins");
    }

    public void oWins(){
        for(int i = 0; i < tablesize; i++){
            for(int j = 0; j < tablesize; j++){
                buttons[i][j].setEnabled(false);
            }
        }
        textfield.setForeground(Color.GREEN);
        textfield.setText("O Wins");
    }

    //Delete list value
    public void RemoveAllElement(){
        check_horizontal.removeAll(check_horizontal);
        check_vertical.removeAll(check_vertical);
        check_rDiagonal.removeAll(check_rDiagonal);
        check_lDiagonal.removeAll(check_lDiagonal);
    }
}