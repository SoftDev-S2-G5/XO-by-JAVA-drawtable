import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.JOptionPane;


public class Model {

    Control control = new Control();

    //Check user size input
    // public boolean CheckSizeInput(String table_size){
    //     try {
    //         Integer.parseInt(table_size);
    //     } catch (Exception e) {
    //         return false;
    //     }
    //     return true;
    // }
    
    //Check winner case
    Boolean CheckWinner(String currentPlayer){
        String[][] xo_2d_array = control.GetStringArray();
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
            
            if(Arrays.deepEquals(checker, xo_2d_array[i])){ // check herizontal
                return true;
            }
            String[] temp = new String[control.GetTableSize()];
            for (int j = 0 ; j < control.GetTableSize() ; j++){
                temp[j] = xo_2d_array[j][i];
            }
            if(Arrays.deepEquals(checker, temp)){ // check vertical
                return true;
            }
            temp_hor[i] = xo_2d_array[i][i];
            temp_ver[i] = xo_2d_array[i][(control.GetTableSize() - 1) - i];
        }
        if(Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)){ //check diagonal
            return true;
        }
        return false;
    }

    //Save game
    // public void SaveGame(int tableSize,String [][] buttons, String currentPlayer ,int count){
    //     try {
    //         FileWriter writer = new FileWriter(new File("save.txt"));
    //         writer.write(String.valueOf(control.GetTableSize()));
    //         writer.write("\n");
    //         for(int i = 0; i < control.GetTableSize(); i++){
    //             for(int j = 0; j < control.GetTableSize(); j++){
    //                 if(!buttons[i][j].equals("x") && !buttons[i][j].equals("o")) 
    //                 {
    //                     writer.write("n");
    //                 }else{
    //                     writer.write(buttons[i][j]);
    //                 }
    //             }
    //         }
    //         writer.write("\n");
    //         writer.write(currentPlayer);
    //         writer.write("\n");
    //         writer.write(String.valueOf(count));
    //         writer.write("\n");
    //         writer.close();
    //         JOptionPane.showMessageDialog(null, "Saved", null, JOptionPane.INFORMATION_MESSAGE);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // //Load Game
    // public List<String> LoadGame(){
    //     List<String> list = new ArrayList<String>();  
    //     try {
    //         BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
    //         String data;
    //         while((data = reader.readLine()) != null){
    //            list.add(data);
    //         }
    //     }  catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return list;
    // }
}
