import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Model {
    public int gameMode;

    private int fontsize = 100;
    private String[][] board;
    private String currentPlayer = "X";
    private int turn, size;
    private boolean checkwin = false;

    public int get_turn() {
        return turn;
    }

    public void change_turn(String value) {
        currentPlayer = value;
        turn += 1;
    }

    public String get_player() {
        return currentPlayer;
    }

    public String get_valueOFboard(int row, int col) {
        return board[row][col];
    }

    public void change_valueOFboard(int row, int col, String value) {
        this.board[row][col] = value;
    }

    public boolean get_checkwin_variable() {
        return checkwin;
    }

    private void change_checkwin_variable(boolean X) {
        checkwin = X;
    }

    public void change_gamemode(int value) {
        gameMode = value;
    }

    public void create_board() {
        board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = " ";
            }
        }
    }
    public int getfontsize() {
        return fontsize;
    }

    public int get_size() {
        return size;
    }

    public void set_size(int size) {
        this.size = size;
    }

    public void resetgame(){
        this.currentPlayer = "X";
        this.turn = 0;
        create_board();
    }

    public void action(int row, int col, View myView){
        change_valueOFboard(row,col,this.currentPlayer);
        if (this.currentPlayer.equals("X")) {
            this.currentPlayer = "O";
        } else {
            this.currentPlayer = "X";
        }
        this.turn += 1;
    }

    // Check winner case
    Boolean checkWin() {
        String[] checker = new String[size];

        for (int i = 0; i < size; i++) {
            if (currentPlayer.equals("X")) {
                checker[i] = "O";
            } else {
                checker[i] = "X";
            }
        }
        String[] temp_hor = new String[size];
        String[] temp_ver = new String[size];
        for (int i = 0; i < size; i++) {

            if (Arrays.deepEquals(checker, board[i])) { // check row
                return true;
            }
            String[] temp = new String[size];
            for (int j = 0; j < size; j++) {
                temp[j] = board[j][i];
            }
            if (Arrays.deepEquals(checker, temp)) { // check collumn
                return true;
            }
            temp_hor[i] = board[i][i];
            temp_ver[i] = board[i][(size - 1) - i];
        }
        if (Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)) {
            return true;
        }
        return false;
    }

    public String select_path() {
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + filePath);
            return filePath;
        }
        return null;
    }

    // Save game
    public void savegame() {
        try {
            FileWriter writer = new FileWriter(select_path());
            writer.write(String.valueOf(size));
            writer.write("\n");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                        writer.write("n");
                    } else {
                        writer.write(board[i][j].toLowerCase());
                    }
                }
            }
            writer.write("\n");
            writer.write(currentPlayer);
            writer.write("\n");
            writer.write(String.valueOf(turn));
            writer.write("\n");
            writer.close();
            JOptionPane.showMessageDialog(null, "Saved", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load Game
    public void loadgame() {
        List<String> list = new ArrayList<String>();
        try {
            Path file = Paths.get(select_path());
            BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            String data;

            while ((data = reader.readLine()) != null) {
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int old_size = Integer.parseInt(list.get(0));
        if (this.size == old_size) {
            this.currentPlayer = list.get(2);
            this.turn = (Integer.parseInt(list.get(3)));
            char[] XO_list = list.get(1).toCharArray();
            int XO_listDefaulthIndex = 0;
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size ; j++) {
                    if (String.valueOf(XO_list[XO_listDefaulthIndex]).equals("n")) {
                        System.out.println("n");
                        change_valueOFboard(i, j, " ");
                    } else {
                        System.out.println(XO_list[XO_listDefaulthIndex]);
                        change_valueOFboard(i, j, String.valueOf(XO_list[XO_listDefaulthIndex]).toUpperCase());
                    }
                    XO_listDefaulthIndex += 1;
                }
            }
            JOptionPane.showMessageDialog(null, "Load Finished", null, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Table size is not the same (Table size must be " + old_size + ").", null,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
