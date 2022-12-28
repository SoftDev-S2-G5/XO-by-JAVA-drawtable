import java.awt.*;
import javax.swing.*;


public class View extends Control{

    Control control = new Control();

    public int tableSize;
    final int sc_size = 800;
    String[][] buttons;

    JLabel sizeInput_title_label = new JLabel("Enter size of table");

    JButton playGame_btn = new JButton("Play");

    JFrame size_input_frame = new JFrame();

    JTextField title_txtfield;
    JTextField size_input_txtfield = new JTextField(25);

    JPanel screen = new JPanel();

    public void CreateSizeInputScreen(){
        size_input_frame.setSize(300,330);
        size_input_frame.setVisible(true);
        size_input_frame.setTitle("Tic-Tac-Toe");
        size_input_frame.setLayout(new FlowLayout());
        size_input_frame.getContentPane().add(sizeInput_title_label);
        size_input_frame.getContentPane().add(size_input_txtfield);
        size_input_frame.getContentPane().add(playGame_btn);
        size_input_frame.pack();
        size_input_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CreateGameScreen(int tablesize){
        //set 
        tableSize = tablesize;
        buttons = new String[tableSize][tableSize];
        for(int i = 0 ; i < tableSize ; i++){
            for(int j = 0 ; j < tableSize ; j++){
                buttons[i][j] = " ";
            }
        }

        this.setTitle("Tic-Tac-Toe Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(false);

        screen.setPreferredSize(new Dimension(sc_size, sc_size));
        screen.addMouseListener(this);
        this.add(screen);
        this.pack();
        this.setVisible(true);

        title_txtfield = new JTextField();
        title_txtfield.setBounds(250,300,300,100);
        title_txtfield.setFont(new Font("Calibri", Font.PLAIN, 50));
        screen.add(title_txtfield);
    }

    public void CreateGameTable(){
        

        int table_size = sc_size/tableSize;
        screen.removeAll();
        Graphics2D g2d = (Graphics2D) screen.getGraphics();
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0 ; i < tableSize ; i++){
            g2d.setColor(Color.black);
            g2d.drawLine(0, i * table_size, sc_size, i * table_size);
            for (int j = 0 ; j < tableSize ; j++){
                g2d.setColor(Color.black);
                g2d.drawLine(j * table_size, 0, j * table_size, sc_size);
                g2d.setFont(new Font("Calibri", Font.PLAIN, table_size));
                //Check symbol for color
                if (buttons[i][j].equals("O")){
                    g2d.setColor(Color.blue);
                    g2d.drawString(buttons[i][j],(table_size * j) + 50,(table_size * (i + 1)) - 50); //ok
                }
                else if(buttons[i][j].equals("X")){
                    g2d.setColor(Color.red); 
                    g2d.drawString(buttons[i][j],(table_size * j) + 60,(table_size * (i + 1)) - 50);  //not ok              
                }
                
               
                // System.out.println("test");
            }
        }
        if(control.count == tableSize * tableSize && !Control.model_control.check_winner()){
            System.out.println("Draw");
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        CreateGameTable();
    }

    public void add_position(Integer row,Integer col) {
        if(buttons[row][col] == "X" || buttons[row][col] == "O"){
        } else {
            buttons[row][col] = control.currentPlayer;
            System.out.println(row+" "+col);
            control.change_player();
        }
    }
}
