import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.JOptionPane;

public class Model {
    private String[][] xo_2d_array;
    private String current_player = "x";
    private int count, table_size;
    private int mode = 0;

    public int GetMode() {
        return mode;
    }

    public void SetMode(int mode) {
        this.mode += mode;
    }

    public int GetTableSize() {
        return table_size;
    }

    public void SetTableSize(int table_size) {
        this.table_size = table_size;
    }

    public int GetCount() {
        return count;
    }

    public void SetCount(int count) {
        this.count = count;
    }

    public String GetCurrentPlayer() {
        return current_player;
    }

    public void SetCurrentPlayer(String current_player) {
        this.current_player = current_player;
    }

    public String[][] GetXOArray() {
        return xo_2d_array;
    }

    public void SetXOArray(int x, int y, String value) {
        this.xo_2d_array[x][y] = value;
    }

    public void ChangeCurrentPlayer() {
        if (this.current_player.equals("x")) {
            this.current_player = "o";
        } else {
            this.current_player = "x";
        }
        this.count += 1;
    }

    public void CreateEmptyArray() {
        xo_2d_array = new String[table_size][table_size];
        for (int i = 0; i < table_size; i++) {
            for (int j = 0; j < table_size; j++) {
                xo_2d_array[i][j] = " ";
            }
        }
    }

    public boolean AddXO(int row, int col) {
        if (xo_2d_array[row][col] == "x" || xo_2d_array[row][col] == "o") {
            return false;
        } else {
            if (current_player.equals("x")) {
                xo_2d_array[row][col] = "x";
            } else {
                xo_2d_array[row][col] = "o";
            }
            return true;
        }
    }

    // Check winner case
    Boolean CheckWinner() {
        String[] checker = new String[table_size];

        for (int i = 0; i < table_size; i++) {
            if (current_player.equals("x")) {
                checker[i] = "o";
            } else {
                checker[i] = "x";
            }
        }
        String[] temp_hor = new String[table_size];
        String[] temp_ver = new String[table_size];
        for (int i = 0; i < table_size; i++) {

            if (Arrays.deepEquals(checker, xo_2d_array[i])) { // check row
                return true;
            }
            String[] temp = new String[table_size];
            for (int j = 0; j < table_size; j++) {
                temp[j] = xo_2d_array[j][i];
            }
            if (Arrays.deepEquals(checker, temp)) { // check collumn
                return true;
            }
            temp_hor[i] = xo_2d_array[i][i];
            temp_ver[i] = xo_2d_array[i][(table_size - 1) - i];
        }
        if (Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)) {
            return true;
        }
        return false;
    }

    // Save game
    public void SaveGame() {
        try {
            FileWriter writer = new FileWriter(new File("save.txt"));
            writer.write(String.valueOf(table_size));
            writer.write("\n");
            for (int i = 0; i < table_size; i++) {
                for (int j = 0; j < table_size; j++) {
                    if (!xo_2d_array[i][j].equals("x") && !xo_2d_array[i][j].equals("o")) {
                        writer.write("n");
                    } else {
                        writer.write(xo_2d_array[i][j]);
                    }
                }
            }
            writer.write("\n");
            writer.write(current_player);
            writer.write("\n");
            writer.write(String.valueOf(count));
            writer.write("\n");
            writer.close();
            JOptionPane.showMessageDialog(null, "Saved", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load Game
    public boolean LoadGame() {
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
            String data;
            while ((data = reader.readLine()) != null) {
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int table_size = GetTableSize();

        int old_tablesize = Integer.parseInt(list.get(0));
        if (table_size == old_tablesize) {
            SetCurrentPlayer(list.get(2));
            SetCount(Integer.parseInt(list.get(3)));
            char[] XO_list = list.get(1).toCharArray();
            int XO_listDefaulthIndex = 0;
            for (int i = 0; i < table_size; i++) {
                for (int j = 0; j < table_size; j++) {
                    if (String.valueOf(XO_list[XO_listDefaulthIndex]).equals("n")) {
                        SetXOArray(i, j, "");
                    } else {
                        SetXOArray(i, j, String.valueOf(XO_list[XO_listDefaulthIndex]));
                    }
                    XO_listDefaulthIndex += 1;
                }
            }
            JOptionPane.showMessageDialog(null, "Load Finished", null, JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Table size is not the same (Table size must be " + old_tablesize + ").", null,
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
}
