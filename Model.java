import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.JOptionPane;


public class Model {

    Control control = new Control();

    private String[][] xo_2d_array;
    private String current_player = "x";
    private int count, table_size;
    private int mode = 0;

    //Check user size input
    public boolean CheckSizeInput(String table_size){
        try {
            Integer.parseInt(table_size);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int GetMode(){
        return mode;
    }

    public void SetMode(int mode){
        this.mode += mode;
    }

    public int GetTableSize(){
        return table_size;
    }

    public void SetTableSize(int table_size){
        this.table_size = table_size;
    }

    public int GetCount(){
        return count;
    }

    public void SetCount(int count){
        this.count = count;
    }

    public String GetCurrentPlayer(){
        return current_player;
    }

    public void SetCurrentPlayer(String current_player){
        this.current_player = current_player;
    }

    public String[][] GetXOArray(){
        return xo_2d_array;
    }

    public void SetXOArray(int x, int y, String value){
        this.xo_2d_array[x][y] = "s";
    }

    public void ChangeCurrentPlayer(){
        if (this.current_player.equals("x")){
            this.current_player = "o";
        }
        else{
            this.current_player = "x";
        }
        this.count += 1;
    }

    public void CreateEmptyArray(){
        xo_2d_array = new String[table_size][table_size];
        for(int i = 0 ; i < table_size ; i++){
            for(int j = 0 ; j < table_size ; j++){
                xo_2d_array[i][j] = " ";
            }
        }
    }
    
    //Check winner case
    Boolean CheckWinner(String currentPlayer){
        String[] checker = new String[control.GetTableSize()];

        for (int i = 0 ; i < control.GetTableSize() ; i++){
            if(currentPlayer.equals("x")){
                checker[i] = "o";
            }
            else{
                checker[i] = "x";
            }
        }
        String[] temp_hor = new String[control.GetTableSize()];
        String[] temp_ver = new String[control.GetTableSize()];
        for (int i = 0 ; i < control.GetTableSize() ; i++){
            
            if(Arrays.deepEquals(checker, xo_2d_array[i])){ // check row
                return true;
            }
            String[] temp = new String[control.GetTableSize()];
            for (int j = 0 ; j < control.GetTableSize() ; j++){
                temp[j] = xo_2d_array[j][i];
            }
            if(Arrays.deepEquals(checker, temp)){ // check collumn
                return true;
            }
            temp_hor[i] = xo_2d_array[i][i];
            temp_ver[i] = xo_2d_array[i][(control.GetTableSize() - 1) - i];
        }
        if(Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)){
            return true;
        }
        return false;
    }

    //Save game
    public void SaveGame(int tableSize,String [][] buttons, String currentPlayer ,int count){
        try {
            FileWriter writer = new FileWriter(new File("save.txt"));
            writer.write(String.valueOf(control.GetTableSize()));
            writer.write("\n");
            for(int i = 0; i < control.GetTableSize(); i++){
                for(int j = 0; j < control.GetTableSize(); j++){
                    if(!buttons[i][j].equals("x") && !buttons[i][j].equals("o")) 
                    {
                        writer.write("n");
                    }else{
                        writer.write(buttons[i][j]);
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

    //Load Game
    public List<String> LoadGame(){
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
        return list;
    }
}
