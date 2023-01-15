import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Control extends JFrame implements MouseListener {
    public static Control control = new Control();
    public static Model model = new Model();
    public static View view = new View();
  
    public static void main(String[] args) {
        view.CreateSizeInputScreen();
        view.playGame_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(model.CheckSizeInput(view.GetTableSize())){
                        model.SetTableSize(Integer.parseInt(view.GetTableSize()));
                       if(model.GetMode() == 0){
                            control.AddListener();
                       }
                        view.CreateGameScreen(model.GetTableSize());  
                        model.CreateEmptyArray();
                        view.size_input_frame.dispose();
                        view.DrawTable();
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
                control.repaint();
            }
        });
        
        view.changeSize_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.SetCount(0);
                model.SetCurrentPlayer("x");
                model.SetMode(1);
                control.dispose();
                view.CreateSizeInputScreen();
            }
        });
        
        view.save_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.SaveGame(model.GetTableSize(), model.GetXOArray(), model.GetCurrentPlayer(), model.GetCount());
            }
        });
    
        view.load_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int table_size = model.GetTableSize();

                List<String> list = model.LoadGame();
                int old_tablesize = Integer.parseInt(list.get(0));
                if(table_size == old_tablesize){
                    model.SetCurrentPlayer(list.get(2));
                    model.SetCount(Integer.parseInt(list.get(3)));
                    char[] XO_list = list.get(1).toCharArray();
                    int XO_listDefaulthIndex = 0;
                    for(int i = 0; i < table_size ; i++){
                        for(int j = 0; j < table_size ; j++){
                            if(String.valueOf(XO_list[XO_listDefaulthIndex]).equals("n")){
                                model.SetXOArray(i, j, "");   
                            }else{
                                model.SetXOArray(i, j, String.valueOf(XO_list[XO_listDefaulthIndex]));   
                            }
                            XO_listDefaulthIndex += 1;
                        }
                    }
                    control.repaint();
                    JOptionPane.showMessageDialog(null, "Load Finished", null, JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Table size is not the same (Table size must be " + old_tablesize +").", null, JOptionPane.INFORMATION_MESSAGE);
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


    public void paint(Graphics g){
        super.paint(g);
        view.DrawTable();
    }

    private int[] convert_mousepos(int mouse_x,int mouse_y){
        int[] row_col = {(int)mouse_y/(view.GetPanelSize() / model.GetTableSize()), (int)mouse_x/(view.GetPanelSize() / model.GetTableSize())};
        return row_col;
    }

//-- Mouse Event Handler -------------------------------------------------------------------------------------------------------------------
    @Override
    public void mouseReleased(MouseEvent e) {
        int table_size = model.GetTableSize();
        int[] row_col = convert_mousepos(e.getX(),e.getY());

        if(view.AddXO(row_col[0],row_col[1])){
            model.ChangeCurrentPlayer();
            view.Update();
        }

        if(model.GetCount() == table_size * table_size && !model.CheckWinner(model.GetCurrentPlayer())){
            JOptionPane.showMessageDialog(null, "Draw", null, JOptionPane.INFORMATION_MESSAGE);
            model.SetCount(0);
            model.SetCurrentPlayer("x");
            model.CreateEmptyArray();
            view.Update();
        }else if(model.CheckWinner(model.GetCurrentPlayer())){
            if(model.GetCurrentPlayer().equals("x")){
                JOptionPane.showMessageDialog(null, "Player O Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Player X Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            model.SetCount(0);
            model.SetCurrentPlayer("x");
            model.CreateEmptyArray();
            view.Update();
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

