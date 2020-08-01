import java.util.IllegalFormatWidthException;

public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String file){
        In in = new In(file);
        int num = in.readInt();
        double rad = in.readDouble();
        Body[] bodies = new Body[num];

        for(int i=0;i<num;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Body planet = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
            bodies[i] = planet;
        }
        return bodies;
    }
    
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double rad = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-rad, rad);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(Body body : bodies){
            body.draw();
        }
        StdDraw.show();

        double[] xForces = new double[bodies.length];
        double[] yForces = new double[bodies.length];
        for(double time=0;time<T;time+=dt){
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i=0; i<bodies.length; i++){
                double xForce = bodies[i].calcNetForceExertedByX(bodies);
                double yForce = bodies[i].calcNetForceExertedByY(bodies);
                xForces[i] = xForce;
                yForces[i] = yForce;
            }
            for(int i=0;i<bodies.length;i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
                bodies[i].draw();
            }
            StdDraw.pause(10);
            StdDraw.show();
        }


    }
}