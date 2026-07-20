public class World {
    private int m;  //dim
    private int n;  //dim
    private String[][] bound;
    private int[][] pos_array = new int[1][2];
    //private int[][] ghost_array = new int[1][2];
    private static  int count = 0;
    private static int lives = 3;

    public World(int[] dim) {
        if (dim.length >= 2) {
            this.m = dim[0];
            this.n = dim[1];
        } else {
            System.out.println("Invalid dimensions");
            this.m = 20;
            this.n = 20;
        }
        bound = new String[m][n];
    }
    public String[][] setMaze() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    bound[i][j] = "#";  // Outer wall
                } else {
                    bound[i][j] = ".";  // Empty space
                }
            }
        } 
        addHurdles();
        return bound;
    }

    public void addHurdles() {
        hurdle h = new hurdle(new int[0][0]);
        int[][] hurdle_array = h.hurdles();
        for (int i = 0; i < hurdle_array.length; i++) {
            int row = hurdle_array[i][0];
            int col = hurdle_array[i][1];
            if (row >= 0 && row < m && col >= 0 && col < n) {
                bound[row][col] = "#"; 
            }
        }
    }

    public void draw() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(bound[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int[][] PackMan_move(Pac_Man pacman) {
        pos_array[0][0] = pacman.get_x();
        pos_array[0][1] = pacman.get_y();
        System.out.println("Please choose d for up, x for down, c for right , and z for left");
        pacman.move();
        return pos_array;
    }
    public String[][] check_board_P(Pac_Man pos) {
        int startX = pos.get_start_x();
        int startY = pos.get_start_y();
        int x = pos.get_x();
        int y = pos.get_y();
        int prevX = pos_array[0][0];
        int prevY = pos_array[0][1];
        bound[prevX][prevY] = " ";
        if (bound[x][y].equals("#")) {
            pos.set_x(prevX);
            pos.set_y(prevY);
            bound[prevX][prevY] = "P";
            System.out.println("wall");
        } else if (bound[x][y].equals(".")) {
            // Move Pac-Man and eat food
            count += 1;
            bound[x][y] = "P";
            System.out.println("food");
        }else if (bound[x][y].equals(" ")) {
            bound[x][y] = "P";
        } else if (bound[x][y].equals("G")) {
            lives -= 1;
            System.out.println("Pac-Man caught by ghost! Lives remaining: " + lives);
            pos.set_x(startX);
            pos.set_y(startY);
            bound[startX][startY] = "P";
        }
        return bound;
    }
    public String[][] check_board_G(Ghosts g1) {
     
        int prevX = g1.get_x();
        int prevY = g1.get_y();
        g1.move_random();
        int x = g1.get_x();
        int y = g1.get_y();

        if (x < 0 || x >= m || y < 0 || y >= n || bound[x][y].equals("#")) {
            g1.set_x(prevX);
            g1.set_y(prevY);
        } else {
            if (bound[x][y].equals(" ")){
               bound[prevX][prevY] = " ";
               bound[x][y] = "G";}
            else if (bound[x][y].equals(".")){
                bound[prevX][prevY] = ".";
                bound[x][y] = "G";}
            if (bound[prevX][prevY].equals("G")){
                bound[prevX][prevY] = ".";
            }  
        }
        return bound;
    }
    public static void main(String[] args) {
        hurdle h = new hurdle();
        h.hurdles();
        h.total_score();
        int[] d = h.get_dimention();
        int dots=h.get_total_dots();
        System.out.println("Dimensions: " + d[0] + " x " + d[1]);
        World obj = new World(d);
        Ghosts ghost = new Ghosts(d);
        System.out.println(dots);
        for (int i = 0; i < 20; i++) {
            ghost.move_random();
        }
        obj.setMaze();
        obj.draw();
        Pac_Man pacman = new Pac_Man();
        Ghosts ghost1 = new Ghosts(d);
        Ghosts ghost2 = new Ghosts(d);
        while (obj.lives > 0) {
            /*System.out.println("Before move:");
            System.out.println("Pac-Man: (" + pacman.get_x() + ", " + pacman.get_y() + ")");
            System.out.println("Ghost 1: (" + ghost1.get_x() + ", " + ghost1.get_y() + ")");
            System.out.println("Ghost 2: (" + ghost2.get_x() + ", " + ghost2.get_y() + ")");*/

            obj.PackMan_move(pacman);
            obj.check_board_G(ghost1);
            obj.check_board_G(ghost2);
            obj.check_board_P(pacman);

            //System.out.println("After move:");
            //System.out.println("Pac-Man: (" + pacman.get_x() + ", " + pacman.get_y() + ")");
            obj.draw();
        if(count==dots){
            System.out.println("_______PacMan won_______");
            System.out.println("_______Game ends_______");
            break;
        }
        System.out.println("_______Score______ "+count);
    }
    System.out.println("_________Game over!________");
    }
}
