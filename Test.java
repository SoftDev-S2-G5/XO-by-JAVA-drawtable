import java.util.List;
import java.util.ArrayList;
public class Test {
    static List<String> check_rDiagonal = new ArrayList<>();

    public static void main(String args[]){

        check_rDiagonal.add("X");
        check_rDiagonal.add("X");
        check_rDiagonal.add("O");
        check_rDiagonal.add("X");
        check_rDiagonal.add("X");

        boolean allEqual = true;

        for (String s : check_rDiagonal) {
            if(!s.equals(check_rDiagonal.get(0))){
                allEqual = false;
                System.out.println(s);
            }else{
                allEqual = true;
                System.out.println(s);
            }
        }
    }
}
