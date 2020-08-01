public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Body target){
        return Math.sqrt((xxPos-target.xxPos)*(xxPos-target.xxPos)+(yyPos-target.yyPos)*(yyPos-target.yyPos));
    }

    public double calcForceExertedBy(Body target){
        double d = calcDistance(target);
        return (6.67E-11*mass*target.mass)/(d*d);
    }

    public double calcForceExertedByX(Body target){
        double dx = target.xxPos - xxPos;
        double f = calcForceExertedBy(target);
        double d = calcDistance(target);
        return f*dx/d;
    }

    public double calcForceExertedByY(Body target){
        double dy = target.yyPos - yyPos;
        double f = calcForceExertedBy(target);
        double d = calcDistance(target);
        return f*dy/d;
    }

    public double calcNetForceExertedByX(Body[] targets){
        double netF = 0;
        for(Body target : targets){
            if(!this.equals(target)){
                netF += calcForceExertedByX(target);
            }
        }
        return netF;
    }

    public double calcNetForceExertedByY(Body[] targets){
        double netF = 0;
        for(Body target : targets){
            if(!this.equals(target)){
                netF += calcForceExertedByY(target);
            }
        }
        return netF;
    }

    public void update(double time, double fx, double fy){
        double ax = fx/mass;
        double ay = fy/mass;
        xxVel += time*ax;
        yyVel += time*ay;

        xxPos += time*xxVel;
        yyPos += time*yyVel;

    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
