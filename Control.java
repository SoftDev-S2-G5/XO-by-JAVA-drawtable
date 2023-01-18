import java.awt.event.*;
import javax.swing.*;

public class Control implements MouseListener {
    private Model model = new Model();
    private View view = new View();
    
    public static void main(String[] args) {
        Control control = new Control();
        control.GameStart(control);
    }   

    public void GameStart(Control control){
        view.SetControlObject(control);
        view.CreateSizeInputScreen();
        view.playGame_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(CheckIntInput(view.GetTableSize())){
                        model.SetTableSize(Integer.parseInt(view.GetTableSize()));
                       if(model.GetMode() == 0){
                            AddListener();
                       }
                        view.CreateGameScreen(model.GetCurrentPlayer()); 
                        model.SetTableSize(Integer.parseInt(view.GetTableSize())); 
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
                model.SetCount(0);
                model.SetCurrentPlayer("x");
                model.CreateEmptyArray();
                view.repaint();
            }
        });
        
        view.changeSize_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.SetCount(0);
                model.SetCurrentPlayer("x");
                model.SetMode(1);
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
    
    public String[][] GetXOArray(){
        return model.GetXOArray();
    }

    public int GetTableSize(){
        return model.GetTableSize();
    }

    public void SetTableSize(int table_size){
        model.SetTableSize(table_size);
    }

    public String GetCurrentPlayer(){
        return model.GetCurrentPlayer();
    }

    private int[] convert_mousepos(int mouse_x,int mouse_y){
        int[] row_col = {(int)mouse_y/(view.GetPanelSize() / model.GetTableSize()), (int)mouse_x/(view.GetPanelSize() / model.GetTableSize())};
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
        int table_size = model.GetTableSize();
        int[] row_col = convert_mousepos(e.getX(),e.getY());

        if(model.AddXO(row_col[0],row_col[1])){
            model.ChangeCurrentPlayer();
            view.repaint();
        }

        if(model.GetCount() == table_size * table_size && !model.CheckWinner()){
            JOptionPane.showMessageDialog(null, "Draw", null, JOptionPane.INFORMATION_MESSAGE);
            model.SetCount(0);
            model.SetCurrentPlayer("x");
            model.CreateEmptyArray();
            view.repaint();
        }else if(model.CheckWinner()){
            if(model.GetCurrentPlayer().equals("x")){
                JOptionPane.showMessageDialog(null, "Player O Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Player X Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            model.SetCount(0);
            model.SetCurrentPlayer("x");
            model.CreateEmptyArray();
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

