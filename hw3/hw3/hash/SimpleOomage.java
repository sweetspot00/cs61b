package hw3.hash;
import java.awt.Color;
import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;


public class SimpleOomage implements Oomage {
    protected int red;
    protected int green;
    protected int blue;

    private static final double WIDTH = 0.01;
    private static final boolean USE_PERFECT_HASH = true;

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (this.getClass() != o.getClass() || o == null) {
            return false;
        } else {
            SimpleOomage that = (SimpleOomage) o;
            return that.blue == this.blue && this.red == that.red && this.green == that.green;
        }

    }


    @Override
    public int hashCode() {
        int maxx = Integer.MAX_VALUE;
        if (!USE_PERFECT_HASH) {
            return red + green + blue;
        } else { //31:用质数得到的hashcode重复率低；31*i 可以用 (i << 5) - i 优化
            Random r = new Random();
//            int redd = (red == 0) ? 1 : red;
//            int greenn = (green == 0) ? 2 : green;
//            int bluee = (red == 0) ? 3 : blue;
//            int hash = 17;
//            hash = hash + (redd + 12) * 31;
//            hash = hash * 31 + greenn;
//            hash = hash * 31 + bluee;
            String redd = "red" + red;
            String greenn = "green" + green;
            String bluee = "blue" + blue;

            return redd.hashCode() + greenn.hashCode() + bluee.hashCode();
        }
    }

    public SimpleOomage(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException();
        }
        if ((r % 5 != 0) || (g % 5 != 0) || (b % 5 != 0)) {
            throw new IllegalArgumentException("red/green/blue values must all be multiples of 5!");
        }
        red = r;
        green = g;
        blue = b;
    }

    @Override
    public void draw(double x, double y, double scalingFactor) {
        StdDraw.setPenColor(new Color(red, green, blue));
        StdDraw.filledSquare(x, y, WIDTH * scalingFactor);
    }

    public static SimpleOomage randomSimpleOomage() {
        int red = StdRandom.uniform(0, 51) * 5;
        int green = StdRandom.uniform(0, 51) * 5;
        int blue = StdRandom.uniform(0, 51) * 5;
        return new SimpleOomage(red, green, blue);
    }

    public static void main(String[] args) {
        System.out.println("Drawing 4 random simple Oomages.");
        randomSimpleOomage().draw(0.25, 0.25, 1);
        randomSimpleOomage().draw(0.75, 0.75, 1);
        randomSimpleOomage().draw(0.25, 0.75, 1);
        randomSimpleOomage().draw(0.75, 0.25, 1);
    }

    public String toString() {
        return "R: " + red + ", G: " + green + ", B: " + blue;
    }
} 
