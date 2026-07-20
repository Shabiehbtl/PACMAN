import java.io.*;
import java.util.Scanner;

public class hurdle {
    private int[][] hurdle;
    private int[] dimention=new int[2];
    private static int length_of_hurdles=0;
    private static int final_total_dots;
    public hurdle(int[][] hurdleArray) {
        this.hurdle = hurdleArray;
    }
    public hurdle(){
        
    }
    public void hurdle(int[][] hurdleArray) {
        hurdle = hurdleArray;
    }
    public int[][] getHurdleArray() {
        return hurdle;
    }
    
    public int[][] hurdles() {
        int row = 0;
        int len=0;
        int[][] array = new int[len][2];  
 
        try {
            File myfile = new File("hurdle.txt");
            Scanner sc = new Scanner(myfile);
            dimention[0] = sc.nextInt();
            dimention[1] = sc.nextInt();
            //System.out.println(dimention[0]);
            //System.out.println(dimention[1]);
            length_of_hurdles= sc.nextInt();
            array=new int[length_of_hurdles][2];

            while (sc.hasNextInt()) {
                for (int j = 0; j < 2; j++) {
                    if (sc.hasNextInt()) {
                        int line = sc.nextInt();
                        array[row][j] = line;
                        //System.out.println(array[row][j]);
                    } else {
                        break;
                    }
                }
                row++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file");
            e.printStackTrace();
        }
        int row_size = array.length - 2;
        hurdle = new int[row_size][2];
        
        for (int i = 0, newIndex = 1; i < row_size; i++, newIndex++) {
            hurdle[i][0] = array[newIndex][1];
            hurdle[i][1] = array[newIndex + 1][0];
        }
        return hurdle;
    }
    public int[] get_dimention() {
        return dimention;
    }
    public int total_score(){
        int r=dimention[0]-2;
        int c=dimention[1]-2;
        int inital_total=r*c;
        final_total_dots=inital_total-length_of_hurdles;
        return final_total_dots;
    }
    public int get_total_dots(){
        return final_total_dots;
    }
    public static void main(String[] args) {
        hurdle h=new hurdle();
        h.total_score();

    }
}