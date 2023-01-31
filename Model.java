import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.JOptionPane;

public class Model {
    private String[][] board;
    private String player = "X";
    private int turn, board_lenght;

    public int get_turn() {
        return turn;
    }

    public String get_player() {
        return player;
    }

    public String get_valueOFboard(int row, int column) {
        return board[row][column];
    }

    public void change_valueOFboard(int row, int column, String value) {
        this.board[row][column] = value;
    }

    public void CreateEmptyArray() {
        board = new String[board_lenght][board_lenght];
        for (int i = 0; i < board_lenght; i++) {
            for (int j = 0; j < board_lenght; j++) {
                board[i][j] = " ";
            }
        }
    }

    public boolean AddXO(int row, int col) {
        if (board[row][col] == "X" || board[row][col] == "O") {
            return false;
        } else {
            if (player.equals("X")) {
                board[row][col] = "X";
            } else {
                board[row][col] = "O";
            }
            return true;
        }
    }

    public int get_size(){
        return this.board_lenght;
    }

    public int get_board_lenght(){
        return this.board_lenght;
    }

    public void set_board_lenght(int lenght){
        this.board_lenght = lenght;
    }

    public void Newgame(){
        this.player = "X";
        this.turn = 0;
        CreateEmptyArray();
    }

    public void Action(){
        if (this.player.equals("X")) {
            this.player = "O";
        } else {
            this.player = "X";
        }
        this.turn += 1;
    }

    // Check winner case
    Boolean CheckWinner() {
        String[] checker = new String[board_lenght];

        for (int i = 0; i < board_lenght; i++) {
            if (player.equals("X")) {
                checker[i] = "O";
            } else {
                checker[i] = "X";
            }
        }
        String[] temp_hor = new String[board_lenght];
        String[] temp_ver = new String[board_lenght];
        for (int i = 0; i < board_lenght; i++) {

            if (Arrays.deepEquals(checker, board[i])) { // check row
                return true;
            }
            String[] temp = new String[board_lenght];
            for (int j = 0; j < board_lenght; j++) {
                temp[j] = board[j][i];
            }
            if (Arrays.deepEquals(checker, temp)) { // check collumn
                return true;
            }
            temp_hor[i] = board[i][i];
            temp_ver[i] = board[i][(board_lenght - 1) - i];
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
            writer.write(String.valueOf(board_lenght));
            writer.write("\n");
            for (int i = 0; i < board_lenght; i++) {
                for (int j = 0; j < board_lenght; j++) {
                    if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                        writer.write("n");
                    } else {
                        writer.write(board[i][j].toLowerCase());
                    }
                }
            }
            writer.write("\n");
            writer.write(player);
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

        int old_board_lenght = Integer.parseInt(list.get(0));
        if (this.board_lenght == old_board_lenght) {
            this.player = list.get(2);
            this.turn = (Integer.parseInt(list.get(3)));
            char[] XO_list = list.get(1).toCharArray();
            int XO_listDefaulthIndex = 0;
            for (int i = 0; i < this.board_lenght; i++) {
                for (int j = 0; j < this.board_lenght ; j++) {
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
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Table size is not the same (Table size must be " + old_board_lenght + ").", null,
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
}
