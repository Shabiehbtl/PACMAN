import java.util.Random;

public class Ghosts {
  private int pos_x;
    private int pos_y;
    private static int[][] ghost_array = new int[1][2];
    private Random random = new Random();

   private  int MAZE_WIDTH;
   private  int MAZE_HEIGHT;
    public Ghosts(int[] dim) {
        if (dim.length >= 2) {
            this.MAZE_WIDTH = dim[1];
            this.MAZE_HEIGHT = dim[0];
        } else {
            System.out.println("Invalid dimensions");
            this.MAZE_WIDTH = 20;
            this.MAZE_HEIGHT= 20;
        }
        pos_x=7;
        pos_y=4;
    }
    public Ghosts(int x, int y) {
        pos_x = x;
        pos_y = y;
    }

    public void set_x(int x) {
        pos_x = x;
    }

    public void set_y(int y) {
        pos_y = y;
    }

    public int get_x() {
        return pos_x;
    }

    public int get_y() {
        return pos_y;
    }

    public int[][] get_pre_ghost() {
        ghost_array[0][0] = pos_x;
        ghost_array[0][1] = pos_y;
        return ghost_array;
    }

    public String toString() {
        return "( " + pos_x + " , " + pos_y + " )";
    }
    public void move_random() {
        for (int i = 0; i < 10; i++) {
            int move = random.nextInt(4);
            int new_x = pos_x;
            int new_y = pos_y;
            switch (move) {
                case 0:
                    new_x++;
                    break;
                case 1: 
                    new_x--;
                    break;
                case 2: 
                    new_y++;
                    break;
                case 3:
                    new_y--;
                    break;
            }
            if (new_x >= 0 && new_x < MAZE_WIDTH && new_y >= 0 && new_y < MAZE_HEIGHT) {
              pos_x = new_x;
              pos_y = new_y;
               break;
          }
         }
      }
}
