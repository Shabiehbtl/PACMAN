import java.awt.*;
import javax.swing.*;

class Directed_walk {
    private int pos_x;
    private int pos_y;

    public Directed_walk() {
        pos_x = 3;
        pos_y = 4;
    }
    public int get_x() {
        return pos_x;
    }
    public int get_y() {
        return pos_y;
    }
    public void set_x(int x) {
        pos_x = x;
    }

    public void set_y(int y) {
        pos_y = y;
    }

    public void move(int direction) {
        switch (direction) {
            case 1:
            pos_x += 1;
            break;
            case 2:
                pos_x -= 1;
                break;
            case 3:
                pos_y += 1;
                break;
            case 4:
                pos_y -= 1;
                break;
        }
    }
}

class MazeGUI extends JFrame {
    private  static int[] d;
        private int m=15;
        private int n=22;
    private final char[][] bound = new char[m][n];
    private final JPanel mazePanel = new JPanel();
    private final JLabel[][] mazeLabels = new JLabel[m][n];
    private Directed_walk packman = new Directed_walk();

    public MazeGUI() {
        setTitle("Maze Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Setup Maze Grid
        setupMaze();
        mazePanel.setLayout(new GridLayout(m, n));
        add(mazePanel, BorderLayout.CENTER);
        drawMaze();

        // Buttons for movement
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton btnEast = new JButton("Move East");
        JButton btnWest = new JButton("Move West");
        JButton btnNorth = new JButton("Move North");
        JButton btnSouth = new JButton("Move South");

        buttonPanel.add(btnEast);
        buttonPanel.add(btnWest);
        buttonPanel.add(btnNorth);
        buttonPanel.add(btnSouth);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button listeners
        btnEast.addActionListener(e -> movePackman(1));  // East
        btnWest.addActionListener(e -> movePackman(2));  // West
        btnNorth.addActionListener(e -> movePackman(3)); // North
        btnSouth.addActionListener(e -> movePackman(4)); // South
    }

    private void setupMaze() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    bound[i][j] = '#'; // Outer walls
                } else {
                    bound[i][j] = '.';
                }
                mazeLabels[i][j] = new JLabel(" ");
                mazeLabels[i][j].setOpaque(true);
                mazeLabels[i][j].setBackground(Color.WHITE);
                mazeLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                mazePanel.add(mazeLabels[i][j]);
            }
        }
        addHurdles();
    }

    public void addHurdles() {

        hurdle h = new hurdle(new int[0][0]);
        int[][] hurdle_array = h.hurdles();  
        for (int i = 0; i < hurdle_array.length; i++) {
            int row = hurdle_array[i][0];
            int col = hurdle_array[i][1];
            if (row >= 0 && row < m && col >= 0 && col < n) {
                bound[row][col] = '#';
                
            }
        }
           
    }

    private void drawMaze() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (bound[i][j] == '#') {
                    mazeLabels[i][j].setBackground(Color.BLACK);
                } else if (bound[i][j] == '.') {
                    mazeLabels[i][j].setBackground(Color.WHITE);
                    mazeLabels[i][j].setForeground(Color.GRAY);
                    mazeLabels[i][j].setText(".");
                } else if (bound[i][j] == 'A') {
                    mazeLabels[i][j].setBackground(Color.YELLOW); // Pac-Man color
                }
            }
        }
        mazePanel.repaint();
    }

    private void movePackman(int direction) {
        int prevX = packman.get_x();
        int prevY = packman.get_y();

        // Move Packman
        packman.move(direction);

        int newX = packman.get_x();
        int newY = packman.get_y();

        // Check bounds and hurdles
        if (newX < 0 || newX >= m || newY < 0 || newY >= n || bound[newX][newY] == '#') {
            packman.set_x(prevX);
            packman.set_y(prevY);
            JOptionPane.showMessageDialog(this, "Hit a wall! Try a different direction.");
        } else {
            bound[prevX][prevY] = '.';
            bound[newX][newY] = 'A';
        }

        drawMaze();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MazeGUI gui = new MazeGUI();
            gui.setVisible(true);
        });
    }
}
