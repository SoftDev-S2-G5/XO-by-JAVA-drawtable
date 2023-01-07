public class View{

    Control control = new Control();

    public void CreateTable(int table_size){
        String[][] xo_2d_array = control.GetStringArray();
        System.out.print("+");
        for(int j = 0 ; j < table_size ; j++){
            System.out.print(" - +");
        }
        System.out.print("\n");
        for(int i = 0 ; i < table_size ; i++){
            System.out.print("| ");
            for(int j = 0 ; j < table_size ; j++){
                System.out.print(xo_2d_array[i][j] + " | ");
            }
            System.out.print("\n");
            System.out.print("+");
            for(int k = 0 ; k < table_size ; k++){
                System.out.print(" - +");
            }
            System.out.print("\n");
        }
    }    

    public boolean AddXO(Integer row,Integer col) {
        String[][] xo_2d_array = control.GetStringArray();
        if(xo_2d_array[row][col] == "x" || xo_2d_array[row][col] == "o"){
            return false;
        } else {
            if(control.GetCurrentPlayer().equals("x")){
                xo_2d_array[row][col] = "x";
            }
            else{
                xo_2d_array[row][col] = "o";
            }
            return true;
        }
    }
}
