import java.util.Scanner;

public class Control{
    Scanner scan = new Scanner(System.in);

    private static Control control = new Control();
    private static Model model = new Model();
    private static View view = new View();

    private static String[][] xo_2d_array;
    private static String current_player = "x";
    private static int count, table_size;
  
    public static void main(String[] args) {
        try {
            System.out.print("Insert Table size : ");
            table_size = control.scan.nextInt();

            xo_2d_array = new String[table_size][table_size];
            control.Create2DString(); //NULL VALUE IN TABLE
            view.CreateTable(table_size);
            while(!model.CheckWinner(current_player) && count != table_size * table_size){ // note: have winner? and count = size of table
                control.GamePlay();
            }
        } catch (Exception e) {
            System.out.println("Table size must be integer");
        }
        
    }            

    public int GetTableSize(){
        return table_size;
    }

    public String GetCurrentPlayer(){
        return current_player;
    }

    public String[][] GetStringArray(){
        return xo_2d_array;
    }

    private void ChangeCurrentPlayer(){
        if (Control.current_player.equals("x")){
            Control.current_player = "o";
        }
        else{
            Control.current_player = "x";
        }
        Control.count += 1;
    }

    private void Create2DString(){
        for(int i = 0 ; i < table_size ; i++){
            for(int j = 0 ; j < table_size ; j++){
                xo_2d_array[i][j] = " ";
            }
        }
    }

    private void GamePlay(){
        try {
            System.out.println("Where would you like to play? (" + current_player + " turn)");
            System.out.print("Row : ");
            int row = scan.nextInt();
            row -=1;
            System.out.print("Column : ");
            int column = scan.nextInt();
            column -=1;
            if(row < table_size && column < table_size && !(row <= -1) && !(column <= -1)){ //to check input
                if(view.AddXO(row,column)){
                    control.ChangeCurrentPlayer();
                }else{
                    System.out.println("Row : " + row + " | Column : " + column + " Already used.");
                }
                //Regenerate
                view.CreateTable(table_size); 
                if(count == table_size * table_size && !model.CheckWinner(current_player)){  //T and !(F) = T
                    System.out.println("------------------------------- Draw -------------------------------");
                }else if(model.CheckWinner(current_player) && current_player.equals("o")){ 
                    System.out.println("------------------------------- Player X Win -------------------------------");
                }else if(model.CheckWinner(current_player) && current_player.equals("x")){
                    System.out.println("------------------------------- Player O Win -------------------------------");
                }
            }
            else{ //alert invalid
                if(row >= table_size ){
                    System.out.println("Row must less than :" + table_size);
                }if(row <=-1){
                    System.out.println("Row must more than :0");
                }if(column >= table_size ){
                    System.out.println("Column must less than :" + table_size);
                }
                if (column <=-1){
                    System.out.println("Column must more than :0");
                }
            }    
        } catch (Exception e) {
            System.out.println("Row and Column must be integer");
        }
    }
}
