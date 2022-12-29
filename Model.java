import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.JOptionPane;


public class Model extends Control {

    //Check user size input
    public boolean CheckSizeInput(String table_size){
        try {
            Integer.parseInt(table_size);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    //Check winner case
    Boolean CheckWinner(String currentPlayer){
        String[] checker = new String[view_control.table_size];
        for (int i = 0 ; i < view_control.table_size ; i++){
            if(currentPlayer.equals("x")){
                checker[i] = "o";
            }
            else{
                checker[i] = "x";
            }
        }
        String[] temp_hor = new String[view_control.table_size];
        String[] temp_ver = new String[view_control.table_size];
        for (int i = 0 ; i < view_control.table_size ; i++){
            if(Arrays.deepEquals(checker, view_control.xo_2d_array[i])){ // check row
                return true;
            }
            String[] temp = new String[view_control.table_size];
            for (int j = 0 ; j < view_control.table_size ; j++){
                temp[j] = view_control.xo_2d_array[j][i];
            }
            if(Arrays.deepEquals(checker, temp)){ // check collumn
                return true;
            }
            temp_hor[i] = view_control.xo_2d_array[i][i];
            temp_ver[i] = view_control.xo_2d_array[i][(view_control.table_size - 1) - i];
        }
        if(Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)){
            return true;
        }
        return false;
    }
}
