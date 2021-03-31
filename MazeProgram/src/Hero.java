import java.awt.*;

public class Hero
{
    private Location location;
    private int dir;
    private int size;
    private Color color;
    int count;

    public Hero(Location location, int dir, int size, Color color)
    {
        this.location=location;
        this.dir=dir;
        this.size=size;
        this.color=color;
        count = 0;
    }

    public Location getLocation()
    {
        return location;
    }
    public int getDir()
    {
        return dir;
    }
    public int getSize()
    {
        return size;
    }
    public Color getColor()
    {
        return color;
    }
    public Rectangle getRect()
    {
        int r = getLocation().getR();
        int c = getLocation().getC();
        return new Rectangle(c*size+size,r*size+size,size,size);//(r,c,size,size)
    }
    public void move(int key, char[][] maze)
    {
        int r = getLocation().getR();
        int c = getLocation().getC();
        if(key == 38)//forward
        {
            if(dir==0) //up
            {
                if(r>0 && maze[r-1][c] == ' ')
                {
                    getLocation().setR(-1);
                    count++;
                }
            }
            if(dir == 1) //right
            {
                if(c<maze[0].length-1 && maze[r][c+1] == ' ')
                {
                    getLocation().setC(+1);
                    count++;
                }
            }
            if(dir == 2) //down
            {
                if(r< maze.length-1 && maze[r+1][c] == ' ')
                {
                    getLocation().setR(+1);
                    count++;
                }
            }
            if(dir == 3)
            {
                if(c>0 && maze[r][c-1] == ' ')
                {
                    getLocation().setC(-1);
                    count++;
                }
            }
        }
        if(key == 37)//rotate left
        {
            dir--;
            if(dir<0)
            {
                dir=3;
            }
        }
        if(key == 39)//rotate right
        {
            dir++;
            if(dir>3)
            {
                dir = 0;
            }
        }
    }
    public int getCount()
    {
        return count;
    }
}
