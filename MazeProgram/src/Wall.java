import java.awt.*;

public class Wall {
    private int[] rows;
    private int[] cols;
    int r;
    int g;
    int b;
    String type;
    int size;

    public Wall(int[] rows, int[] cols, int r, int g, int b, String type, int size) {
        this.rows = rows;
        this.cols = cols;
        this.r = r;
        this.g = g;
        this.b = b;
        this.type = type;
        this.size = size;
    }

    public Color getColor() {
        return new Color(r, g, b);
    }

    public int[] getRows() {
        return rows;
    }

    public int[] getCols() {
        return cols;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public Polygon getPolygon(int[] rLocs, int[] cLocs) {
        if (rLocs.length == 4 && cLocs.length == 4) {
            return new Polygon(cLocs, rLocs, 4);//(r,c,size,size)
        } else {
            return new Polygon(cLocs, rLocs, 3);//(r,c,size,size)
        }
    }

    public GradientPaint getPaint() {
        int red = 0;
        int green = 0;
        int blue = 0;

        if (r - 20 >= 0)
            red = r - 40;
        if (g - 20 >= 0)
            green = g - 40;
        if (b - 20 >= 0)
            blue = b - 40;

        if(type.contains("Right")) //right
            return new GradientPaint(cols[0], rows[1], new Color(red, green, b), cols[1], rows[1], new Color(r, green, b));
        else if(type.contains("Left")) //left
            return new GradientPaint(cols[0], rows[1], new Color(r, green, b), cols[2], rows[1], new Color(red, green, b));
        else if(type.contains("Front")) //front
            return new GradientPaint(cols[0], rows[1], new Color(red, green, b), cols[1], rows[1], new Color(red, green, blue));
        else if(type.contains("Floor")) //floor
            return new GradientPaint(cols[0], rows[0], new Color(red, green, b), cols[0], rows[2], new Color(r, green, b));

        return new GradientPaint(cols[0], rows[0], new Color(r, green, b), cols[0], rows[2], new Color(red, green, b));

    }
}
