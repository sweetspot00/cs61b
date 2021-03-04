import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.regex.*;
public class NBody{
    public static double readRadius(String filename){
        String data = "";
        try{
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int cnt = 0;
            while(myReader.hasNextLine() && cnt<2){
                data = myReader.nextLine();
                cnt +=1;
            }
            myReader.close();
            return Double.parseDouble(data);
        }catch(FileNotFoundException e){
            System.out.println("an error occured");
            e.printStackTrace();
        }
        return Double.parseDouble(data);
    }
    public static boolean isDouble(String input) {
        Matcher mer = Pattern.compile("-?[0-9]+.*[0-9]*").matcher(input);
        return mer.find();
    }
 
    public static Planet[] readPlanets(String filename){
        ArrayList<Planet> b = new ArrayList<Planet>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int cnt=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] d= data.split("\\s+");
                // System.out.println(d.length);
                if(cnt>=2 ){
                    if(d.length==7 && isDouble(d[1])) {
                    
                        System.out.println(d[0]+"this is the img");
                        b.add(new Planet(Double.parseDouble(d[1]),
                        Double.parseDouble(d[2]),
                        Double.parseDouble(d[3]),
                        Double.parseDouble(d[4]),
                        Double.parseDouble(d[5]),
                        d[6]));}
                    else if(d.length==6 && isDouble(d[0])){
                        b.add(new Planet(Double.parseDouble(d[0]),
                        Double.parseDouble(d[1]),
                        Double.parseDouble(d[2]),
                        Double.parseDouble(d[3]),
                        Double.parseDouble(d[4]),
                        d[5]));
                    }

                    else{
                        break;
                    }
                }
             cnt+=1;
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
           
        Planet[] res = new Planet[b.size()] ;
        res = (Planet[]) b.toArray(res);
        return res;    
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        String background= "images/starfield.jpg";
        double radius = readRadius(filename);
        Planet[] bodies = readBodies(filename);
        
        for(double time=0;time<=T;time +=dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for(int i=0;i<bodies.length;i+=1){
                xForces[i]= bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);    
            }
            for(int i=0;i<bodies.length;i+=1){
                bodies[i].update(dt, xForces[i], yForces[i]);   
            }
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();

            /* Stamps three copies of advice.png in a triangular pattern. */
            StdDraw.picture(0,0,background);


            /* Shows the drawing to the screen, and waits 2000 milliseconds. */
            
            StdDraw.pause(2000);
            for(Planet b:bodies){
                b.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(10);

        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                        bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
            }
        

    }
}