import java.awt.event.*;
import javax.swing.*;

public class Control implements MouseListener {
    private Model model;
    private View view;

    Control(Model model, View view){
        this.model = model;
        this.view = view;
        AddListener();
        GameStart();
    }
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Control control = new Control(model,view);
        view.SetControlObject(control);
    }   

    public void GameStart(){
        view.CreateSizeInputScreen();
        view.playGame_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(CheckIntInput(view.GetTableSize())){
                        model.set_board_lenght(Integer.parseInt(view.GetTableSize()));
                        view.CreateGameScreen(model.get_player()); 
                        model.CreateEmptyArray();
                        view.size_input_frame.dispose();
                        view.repaint();
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter number", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        view.resetGame_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.Newgame();
                view.repaint();
            }
        });
        
        view.changeSize_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.Newgame();
                view.dispose();
                view.CreateSizeInputScreen();
            }
        });
        
        view.save_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.SaveGame();
            }
        });
    
        view.load_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(model.LoadGame()){
                    view.repaint();
                }
            }
        });
            
        view.exit_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }

    public void AddListener(){
        view.GetPanelObject().addMouseListener(this);
    }

    public String get_player(){
        return model.get_player();
    }
    
    public String get_valueOFboard(int row, int column) {
        return model.get_valueOFboard(row, column);
    }

    public void change_valueOFboard(int row, int column, String value) {
        model.change_valueOFboard(row, column, value);
    }

    public int get_board_lenght(){
        return model.get_board_lenght();
    }

    public void set_board_lenght(int table_size){
        model.set_board_lenght(table_size);
    }

    private int[] convert_mousepos(int mouse_x,int mouse_y){
        int[] row_col = {(int)mouse_y/(view.GetPanelSize() / model.get_board_lenght()), (int)mouse_x/(view.GetPanelSize() / model.get_board_lenght())};
        return row_col;
    }

    // Check user size input
    public boolean CheckIntInput(String table_size) {
        try {
            Integer.parseInt(table_size);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

//-- Mouse Event Handler -------------------------------------------------------------------------------------------------------------------
    @Override
    public void mouseReleased(MouseEvent e) {
        int table_size = model.get_board_lenght();
        int[] row_col = convert_mousepos(e.getX(),e.getY());

        if(model.AddXO(row_col[0],row_col[1])){
            model.Action();
            view.repaint();
        }
        if(model.get_turn() == table_size * table_size && !model.CheckWinner()){
            JOptionPane.showMessageDialog(null, "Draw", null, JOptionPane.INFORMATION_MESSAGE);
            model.Newgame();
            view.repaint();
        }else if(model.CheckWinner()){
            if(model.get_player().equals("X")){
                JOptionPane.showMessageDialog(null, "Player O Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Player X Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            model.Newgame();
            view.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {} 
//---------------------------------------------------------------------------------------------------------------------------------
}

