import java.util.Scanner;
public class Pac_Man {
    private int pos_x;
    private int pos_y;
    private  int start_x=3;
    private  int start_y=4;
   
    public int get_start_x(){
        return start_x;
    }
    public int get_start_y(){
        return start_y;
    }
   private int[][] pos_array=new int[1][2];

    public Pac_Man(){
        pos_x=start_x;
        pos_y=start_y;
    }
    public Pac_Man(int x,int y){
        pos_x=x;
        pos_y=y;
    }
    public void set_x(int x){
         pos_x=x;
    }
    public void set_y(int y){
        pos_y=y;
   }
    public int get_x(){
        return pos_x;
    }
    public int get_y(){
        return pos_y;
    }
    public int[][] get_pre_pos(){
     
      pos_array[0][0]=pos_x;
      pos_array[0][1]=pos_y;
        return pos_array;
    }
    public String toString(){
          return "( "+pos_x+" , "+pos_y+" )";
    }
    public void move() {
        Scanner input=new Scanner(System.in);
        String direction = input.next();
        switch (direction) {
            case "x":
            pos_x += 1;
            break;
            case "d":
                pos_x -= 1;
                break;
            case "c":
                pos_y += 1;
                break;
            case "z":
                pos_y -= 1;
                break;
            default:
                System.out.println("Invalid direction. Please choose d, z, x, or c");
                break;
        }
        }
}

