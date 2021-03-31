import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MazeProgram extends JPanel implements KeyListener {
    JFrame frame;
    int dir = 1;
    int r = 1;
    int c = 0;
    char[][] maze = new char[20][67];
    int size = 15;
    Hero hero;
    Hero keyWin;
    boolean draw3D = false;
    ArrayList<Wall> walls;
    long startTime;
    int count;
    int colorPicker;


    public MazeProgram() {
        hero = new Hero(new Location(r, c), dir, size, Color.RED);
        keyWin = new Hero(new Location(19, 58), dir, size, Color.RED);
        frame = new JFrame("MazeProgram");
        frame.setSize(1200, 900);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        setBoard();
        startTime = System.currentTimeMillis();
        count = 0;
        colorPicker = 0;
    }

    public void Timer()
    {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        System.out.println(elapsedMinutes+":"+secondsDisplay);
    }

    public void setBoard() {
        File file = new File("src/Maze3D.txt");

        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            int r = 0;
            while ((text = input.readLine()) != null) {
                for (int c = 0; c < text.length(); c++) {
                    maze[r][c] = text.charAt(c);
                }
                r++;
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void createWalls()
    {
        walls = new ArrayList<Wall>();

        int rr = hero.getLocation().getR();
        int cc = hero.getLocation().getC();
        int dir = hero.getDir();

        switch(dir) {
            case 0://up
                for (int n = 0; n < 5; n++) {
                    try {
                        if(maze[rr - n][cc] == ' ')
                        {
                            walls.add(getCeil(n));
                            walls.add(getFloor(n));
                        }
                        if (maze[rr - n][cc - 1] == '*') {
                            walls.add(getLeft(n));
                        }
                        else{
                            walls.add(getLeftPath(n));
                            walls.add(getCeilingLeft(n));
                            walls.add(getFloorLeft(n));
                        }
                        if(maze[rr-n][cc+1] == '*')
                        {
                            walls.add(getRight(n));
                        }
                        else {
                            walls.add(getRightPath(n));
                            walls.add(getCeilingRight(n));
                            walls.add(getFloorRight(n));
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {

                    }
                }
                for(int n = 0; n<6; n++)
                {
                    try
                    {
                        if(maze[rr-n][cc] == '*') {
                            walls.add(getFront(n));
                            n = 6;
                        }
                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                    }
                }
                break;

            case 1://right
                for (int n = 0; n < 5; n++) {
                    try {
                        if(maze[rr][cc + n] == ' ')
                        {
                            walls.add(getCeil(n));
                            walls.add(getFloor(n));
                        }
                        if (maze[rr - 1][cc + n] == '*') {
                            walls.add(getLeft(n));
                        }
                        else{
                            walls.add(getLeftPath(n));
                            walls.add(getCeilingLeft(n));
                            walls.add(getFloorLeft(n));
                        }
                        if(maze[rr + 1][cc + n] == '*')
                        {
                            walls.add(getRight(n));
                        }
                        else {
                            walls.add(getRightPath(n));
                            walls.add(getCeilingRight(n));
                            walls.add(getFloorRight(n));
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {

                    }
                }
                for(int n = 0; n<6; n++)
                {
                    try
                    {
                        if(maze[rr][cc+n] == '*') {
                            walls.add(getFront(n));
                            n = 6;
                        }
                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                    }
                }
                break;

            case 2://down
                for (int n = 0; n < 5; n++) {
                    try {
                        if(maze[rr + n][cc] == ' ')
                        {
                            walls.add(getCeil(n));
                            walls.add(getFloor(n));
                        }
                        if (maze[rr + n][cc + 1] == '*') {
                            walls.add(getLeft(n));
                        }
                        else{
                            walls.add(getLeftPath(n));
                            walls.add(getCeilingLeft(n));
                            walls.add(getFloorLeft(n));
                        }
                        if(maze[rr+n][cc-1] == '*')
                        {
                            walls.add(getRight(n));
                        }
                        else {
                            walls.add(getRightPath(n));
                            walls.add(getCeilingRight(n));
                            walls.add(getFloorRight(n));
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {

                    }
                }
                for(int n = 0; n<6; n++)
                {
                    try
                    {
                        if(maze[rr+n][cc] == '*') {
                            walls.add(getFront(n));
                            n = 6;
                        }
                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                    }
                }
                break;

            case 3://left
                for (int n = 0; n < 5; n++) {
                    try {
                        if(maze[rr][cc - n] == ' ')
                        {
                            walls.add(getCeil(n));
                            walls.add(getFloor(n));
                        }
                        if (maze[rr + 1][cc - n] == '*') {
                            walls.add(getLeft(n));
                        }
                        else{
                            walls.add(getLeftPath(n));
                            walls.add(getCeilingLeft(n));
                            walls.add(getFloorLeft(n));
                        }
                        if(maze[rr-1][cc-n] == '*')
                        {
                            walls.add(getRight(n));
                        }
                        else {
                            walls.add(getRightPath(n));
                            walls.add(getCeilingRight(n));
                            walls.add(getFloorRight(n));
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {

                    }
                }
                for(int n = 0; n<6; n++)
                {
                    try
                    {
                        if(maze[rr][cc-n] == '*') {
                            walls.add(getFront(n));
                            n = 6;
                        }
                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                    }
                }
            break;
        }
    }
    public Wall getLeft(int n)//trapezoid
    {
        int[] rLocs = new int[]{100+50*n, 150+50*n, 650-50*n, 700-50*n};
        int[] cLocs = new int[]{100+50*n, 150+50*n, 150+50*n, 100+50*n};

        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Left Wall", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Left Wall", size);
    }
    public Wall getLeftPath(int n)//rectangle
    {
        int[] rLocs = new int[]{150+50*n, 150+50*n, 650-50*n, 650-50*n};
        int[] cLocs = new int[]{100+50*n, 150+50*n, 150+50*n, 100+50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Left Path", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Left Path", size);
    }
    public Wall getCeilingLeft(int n)//triangle top
    {
        int[] rLocs = new int[]{150+50*n, 150+50*n, 100+50*n};
        int[] cLocs = new int[]{100+50*n, 150+50*n, 100+50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Left Triangle Top", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Left Triangle Top", size);
    }
    public Wall getFloorLeft(int n)//triangle bottom
    {
        int[] rLocs = new int[]{650-50*n, 650-50*n, 700-50*n};
        int[] cLocs = new int[]{100+50*n, 150+50*n, 100+50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Left Triangle Bottom", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Left Triangle Bottom", size);
    }
    public Wall getRight(int n)//trapezoid
    {
        int[] rLocs = new int[]{100+50*n, 150+50*n, 650-50*n, 700-50*n};
        int[] cLocs = new int[]{700-50*n, 650-50*n, 650-50*n, 700-50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Right Wall", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Right Wall", size);
    }
    public Wall getRightPath(int n)//rectangle
    {
        int[] rLocs = new int[]{150+50*n, 150+50*n, 650-50*n, 650-50*n};
        int[] cLocs = new int[]{700-50*n, 650-50*n, 650-50*n, 700-50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Right Path", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Right Path", size);
    }
    public Wall getCeilingRight(int n)//triangle top
    {
        int[] rLocs = new int[]{150+50*n, 150+50*n, 100+50*n};
        int[] cLocs = new int[]{650-50*n, 700-50*n, 700-50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Right Triangle Top", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Right Triangle Top", size);
    }
    public Wall getFloorRight(int n)//triangle bottom
    {
        int[] rLocs = new int[]{650-50*n, 650-50*n, 700-50*n};
        int[] cLocs = new int[]{650-50*n, 700-50*n, 700-50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Right Triangle Bottom", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Right Triangle Bottom", size);
    }
    public Wall getFront(int n)
    {
        int[] rLocs = new int[]{100+50*n, 100+50*n, 700-50*n, 700-50*n};
        int[] cLocs = new int[]{100+50*n, 700-50*n, 700-50*n, 100+50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Front Wall", 1);
        //return new Wall(rLocs, cLocs, 255+50-50*n, 255+50-50*n, 255+50-50*n,"Front Wall", size);
    }
    public Wall getCeil(int n)
    {
        int[] rLocs = new int[]{100+50*n, 150+50*n, 150+50*n, 100+50*n};
        int[] cLocs = new int[]{100+50*n, 150+50*n, 650-50*n, 700-50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Ceiling", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Ceiling", size);
    }
    public Wall getFloor(int n)
    {
        int[] rLocs = new int[]{700-50*n, 650-50*n, 650-50*n, 700-50*n};
        int[] cLocs = new int[]{100+50*n, 150+50*n, 650-50*n, 700-50*n};
        int color = 200 - 40 * n;
        if (color > 255)
            color = 255;
        else if (color < 0)
            color = 0;
        return new Wall(rLocs, cLocs, color, color, color, "Floor", 1);
        //return new Wall(rLocs, cLocs, 255-50*n, 255-50*n, 255-50*n,"Floor", size);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Font myFont = new Font ("Courier New", 1, 100);
        Font myFont2 = new Font ("Courier New", Font.BOLD, 20);
        Font myFont3 = new Font ("Courier New", 1, 80);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,1200,900);

        if(!draw3D) {
            if(colorPicker == 0) {
                g2.setColor(Color.RED);
            }
            if(colorPicker == 1) {
                g2.setColor(Color.GREEN);
            }
            if(colorPicker == 2) {
                g2.setColor(Color.BLUE);
            }
            if(colorPicker == 3) {
                g2.setColor(Color.ORANGE);
            }
            if(colorPicker == 4) {
                g2.setColor(Color.PINK);
            }
            if(colorPicker == 5) {
                g2.setColor(Color.YELLOW);
            }
            if(colorPicker == 6) {
                g2.setColor(Color.GRAY);
            }
            for (int c = 0; c < maze[0].length; c++) {
                for (int r = 0; r < maze.length; r++) {
                    if (maze[r][c] == ' ') {
                        g2.fillRect(c * size + size, r * size + size, size, size);
                    } else {
                        g2.drawRect(c * size + size, r * size + size, size, size);
                    }
                }
            }
            g2.setFont(myFont2);
            g2.drawString("Press [r] to restart",50,340);
            g2.drawString("Moves Made: "+hero.getCount(), 50,390);
            g2.drawString("If you want to take a look at the 3D Maze, then press [space].",350,340);
            g2.drawString("Press [c] to Customize Maze Color :)",700,450);

            g2.setColor(hero.getColor());
            g2.fill(hero.getRect());
            g2.setColor(Color.YELLOW);
            g2.draw(hero.getRect());

            g2.setColor(Color.WHITE);
            g2.fillOval(300,350,400,400);
            g2.setColor(Color.BLACK);
            g2.setFont(myFont);
            g2.drawString("N",470,420);
            g2.drawString("S",470,725);
            g2.drawString("E",620,575);
            g2.drawString("W",320,575);

            g2.setColor(Color.YELLOW);
            if(hero.getDir() == 0)//up
            {
                g2.fillRect(490,420,20,150);
            }
            if(hero.getDir() == 1)//right
            {
                g2.fillRect(480,530,150,20);
            }
            if(hero.getDir() == 2)//down
            {
                g2.fillRect(490, 510, 20, 150);
            }
            if(hero.getDir() == 3)
            {
                g2.fillRect(380,530,150,20);
            }

            if(hero.getRect().intersects(keyWin.getRect())) {

                g2.setColor(keyWin.getColor());
                g2.fill(keyWin.getRect());
                g2.setColor(Color.YELLOW);
                g2.draw(keyWin.getRect());
                g2.setFont(myFont3);

                g2.drawString("Maze Done in "+hero.getCount()+" moves!",10,300);
            }
        }
        if(draw3D)
        {
            for(int i = 0; i<walls.size(); i++)
            {
                g2.setPaint(walls.get(i).getPaint());
                g2.fillPolygon(walls.get(i).getPolygon(walls.get(i).getRows(), walls.get(i).getCols()));
                g2.setColor(Color.BLACK);
                g2.drawPolygon(walls.get(i).getPolygon(walls.get(i).getRows(), walls.get(i).getCols()));
            }
            if(hero.getRect().intersects(keyWin.getRect())) {
                g2.setColor(Color.YELLOW);
                g2.setFont(myFont3);
                g2.drawString("Maze Done in "+hero.getCount()+" moves!",10,200);
            }
            g2.setColor(Color.WHITE);
            g2.fillOval(730,250,400,400);
            g2.setColor(Color.BLACK);
            g2.setFont(myFont);
            g2.drawString("N",900,320);
            g2.drawString("S",900,625);
            g2.drawString("E",1050,475);
            g2.drawString("W",750,475);
            g2.setColor(Color.YELLOW);

            if(hero.getDir() == 0)//up
            {
                g2.fillRect(920,320,20,150);
            }
            if(hero.getDir() == 1)//right
            {
                g2.fillRect(910,430,150,20);
            }
            if(hero.getDir() == 2)//down
            {
                g2.fillRect(920, 410, 20, 150);
            }
            if(hero.getDir() == 3)
            {
                g2.fillRect(810,430,150,20);
            }
            g2.setColor(Color.WHITE);
            g2.setFont(myFont2);
            g2.drawString("Press [r] to restart",800,150);
            g2.drawString("Moves Made: "+hero.getCount(), 800,200);
            if(count == 1) {
                g2.drawString("You are stuck in the 3D Maze. If you want to take a look at the 2D Maze, then press [h].", 50, 50);
            }
            else{
                g2.drawString("You are NOT stuck in the 3D Maze. If you want to take a look at the 2D Maze, then press [space].", 10, 50);
            }
        }
    }

    public static void main(String[]args)
    {
        MazeProgram app = new MazeProgram();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!hero.getRect().intersects(keyWin.getRect()) || e.getKeyCode()==82) {
            hero.move(e.getKeyCode(), maze);
            if (e.getKeyCode() == 67) {
                colorPicker++;
                if (colorPicker > 6) {
                    colorPicker = 0;
                }
            }
            if (e.getKeyCode() == 82) {
                hero = new Hero(new Location(1, 0), 1, size, Color.RED);
            }
            if (e.getKeyCode() == 72 && count == 1) {
                count++;
                draw3D = !draw3D;
            }
            if (e.getKeyCode() == 32 && count != 1) {
                draw3D = !draw3D;
                count++;
            }
            if (draw3D) {
                createWalls();
            }
            repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}