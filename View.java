import java.awt.*;
import javax.swing.*;


public class View extends Control{

    Control control = new Control();

    public int table_size;
    final int screen_size = 800;
    String[][] xo_2d_array;

    JLabel sizeInput_title_label = new JLabel("Enter size of table");

    JButton playGame_btn = new JButton("Play");

    JFrame size_input_frame = new JFrame();

    JTextField title_txtfield;
    JTextField size_input_txtfield = new JTextField(25);

    JPanel xo_table_panel = new JPanel();

    //Create UI window for get tabel size from user
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

    //Create window for draw the table
    public void CreateGameScreen(int size){
        table_size = size;
        Create2DArray();

        this.setTitle("Tic-Tac-Toe Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(false);

        xo_table_panel.setPreferredSize(new Dimension(screen_size, screen_size));
        xo_table_panel.addMouseListener(this);
        this.add(xo_table_panel);
        this.pack();
        this.setVisible(true);

        title_txtfield = new JTextField();
        title_txtfield.setBounds(250,300,300,100);
        title_txtfield.setFont(new Font("Calibri", Font.PLAIN, 50));
        xo_table_panel.add(title_txtfield);
        xo_table_panel.setBackground(Color.GRAY);
    }

    //Draw table
    public void DrawTable(){
        int responsive = screen_size / table_size;
        xo_table_panel.removeAll();
        Graphics2D g2d = (Graphics2D) xo_table_panel.getGraphics();
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0 ; i < table_size ; i++){
            g2d.setColor(Color.black);
            g2d.drawLine(0, i * responsive, screen_size, i * responsive);
            for (int j = 0 ; j < table_size ; j++){
                g2d.setColor(Color.black);
                g2d.drawLine(j * responsive, 0, j * responsive, screen_size);
                g2d.setFont(new Font("Calibri", Font.PLAIN, responsive));
                //Check symbol for color
                if (xo_2d_array[i][j].equals("o")){
                    if(game_state == 0){
                        g2d.setColor(Color.blue);
                        g2d.drawString(xo_2d_array[i][j],(responsive * j) + 50,(responsive * (i + 1)) - 50); //ok
                    }
                }
                else if(xo_2d_array[i][j].equals("x")){
                    if(game_state == 0){
                        g2d.setColor(Color.red);
                        g2d.drawString(xo_2d_array[i][j],(responsive * j) + 50,(table_size * (i + 1)) - 50); //ok
                    }        
                }
            }
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        DrawTable();
    }

    public void AddXO(Integer row,Integer col) {
        if(xo_2d_array[row][col] == "x" || xo_2d_array[row][col] == "o"){
        } else {
            xo_2d_array[row][col] = current_player;
            ChangeCurrentPlayer();
        }
    }

    public void Create2DArray(){
        xo_2d_array = new String[table_size][table_size];
        for(int i = 0 ; i < table_size ; i++){
            for(int j = 0 ; j < table_size ; j++){
                xo_2d_array[i][j] = " ";
            }
        }
    }
}
