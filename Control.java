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
                        model.set_size(Integer.parseInt(view.GetTableSize()));
                        view.CreateGameScreen(model.get_player()); 
                        model.create_board();
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
                model.resetgame();
                view.repaint();
            }
        });
        
        view.changeSize_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.resetgame();
                view.dispose();
                view.CreateSizeInputScreen();
            }
        });
        
        view.save_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.savegame();
            }
        });
    
        view.load_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.loadgame();
                view.repaint();
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
    
    public String get_valueOFboard(int row, int col) {
        return model.get_valueOFboard(row, col);
    }

    public void change_valueOFboard(int row, int col, String value) {
        model.change_valueOFboard(row, col, value);
    }

    public int get_size(){
        return model.get_size();
    }


    private int[] convert_mousepos(int mouse_x,int mouse_y){
        int[] row_col = {(int)mouse_y/(view.GetPanelSize() / model.get_size()), (int)mouse_x/(view.GetPanelSize() / model.get_size())};
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
        int table_size = model.get_size();
        int[] row_col = convert_mousepos(e.getX(),e.getY());

        if (model.get_valueOFboard(row_col[0], row_col[1]) == "X" || model.get_valueOFboard(row_col[0], row_col[1]) == "O") {
            JOptionPane.showMessageDialog(null, "Already used.", null, JOptionPane.INFORMATION_MESSAGE);
        } else {
            model.action(row_col[0], row_col[1], view);
            view.repaint();
        }

        if(model.get_turn() == table_size * table_size && !model.checkWin()){
            JOptionPane.showMessageDialog(null, "Draw", null, JOptionPane.INFORMATION_MESSAGE);
            model.resetgame();
            view.repaint();
        }else if(model.checkWin()){
            if(model.get_player().equals("X")){
                JOptionPane.showMessageDialog(null, "Player O Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Player X Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            model.resetgame();
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

