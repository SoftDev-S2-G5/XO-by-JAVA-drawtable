import java.util.*;


public class Model extends Control {

    //method 
    public boolean CheckSizeInput(String tableSize){
        try {
            Integer.parseInt(tableSize);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    Boolean check_winner(){
        String[] checker = new String[view_control.tableSize];
        for (int i=0;i<view_control.tableSize;i++){
            if(currentPlayer.equals("x")){
                checker[i] = "o";
            }
            else{
                checker[i] = "x";
            }
        }
        String[] horizontal = new String[view_control.tableSize];
        String[] temp_hor = new String[view_control.tableSize];
        String[] temp_ver = new String[view_control.tableSize];
        for (int i=0;i<view_control.tableSize;i++){
            for (int x=0;x<view_control.tableSize;x++){
                horizontal[x] = view_control.buttons[i][x];
            }
            if(Arrays.deepEquals(checker, horizontal)){      // Check Row
                return true;
            }

            String[] temp = new String[view_control.tableSize];
            for (int j=0;j<view_control.tableSize;j++){
                temp[j] = view_control.buttons[j][i];
            }
            if(Arrays.deepEquals(checker, temp)){            // Check Column
                return true;
            }
            temp_hor[i] = view_control.buttons[i][i];
            temp_ver[i] = view_control.buttons[i][(view_control.tableSize-1)-i];
        }
        if(Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)){     // Check Diagonal
            System.out.println("Got winner");
            return true;
        }
        System.out.println("Got no Winnwe");
        return false;
    }

    // public void SaveGame(int view_control.tableSize,JButton [][] buttons, String currentPlayer ,int count){
    //     try {
    //         FileWriter writer = new FileWriter(new File("save.txt"));
    //         writer.write(String.valueOf(view_control.tableSize));
    //         writer.write("\n");
    //         for(int i = 0; i < view_control.tableSize; i++){
    //             for(int j = 0; j < view_control.tableSize; j++){
    //                 if(!buttons[i][j].getText().equals("x") && !buttons[i][j].getText().equals("o")) 
    //                 {
    //                     writer.write("n");
    //                 }else{
    //                     writer.write(buttons[i][j].getText());
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
    
    //     public static void ResetTable() {
    //     for(int i = 0; i < Control.view_control.view_control.tableSize; i++){
    //         for(int j = 0; j < Control.view_control.view_control.tableSize; j++){
    //             Control.view_control.buttons[i][j].setEnabled(true);
    //             Control.view_control.buttons[i][j].setBackground(new Color(237, 187, 153));
    //             Control.view_control.buttons[i][j].setText("");
    //         }
    //     }
    //     Control.view_control.count = 0;
    //     Control.view_control.currentPlayer = "x";
    //     Control.view_control.textfield.setForeground(Color.BLACK);
    //     Control.view_control.textfield.setText("Tic-Tac-Toe");
        
    // }
    public static void ExitGame() {
        System.exit(0); 
    }

    // public static void ButtonEnable(int view_control.tableSize){
    //     for(int x = 0; x < view_control.tableSize; x++){
    //         for(int y = 0; y < view_control.tableSize; y++){
    //             Control.view_control.buttons[x][y].setEnabled(false);
    //         }
    //     }
    // }
}
