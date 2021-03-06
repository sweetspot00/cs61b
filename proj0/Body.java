public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;
    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    public double calcDistance(Planet b){
        double dist = Math.sqrt(Math.pow(this.xxPos-b.xxPos,2)+Math.pow(this.yyPos-b.yyPos,2));
        return dist;
    }
    public double calcForceExrtedBy(Planet b){
        double dist = this.calcDistance(b);
        double force = this.mass*b.mass*G/Math.pow(dist, 2);
        return force;
    }
    public double calcForceExertedByX(Planet b){
        //f1*cos
        double F1 = this.calcForceExrtedBy(b);
        return F1*(b.xxPos-this.xxPos)/this.calcDistance(b);
    }
    public double calcForceExertedByY(Planet b){
        return this.calcForceExrtedBy(b)*(b.yyPos-this.yyPos)/this.calcDistance(b);
    }
    public double calcNetForceExertedByX(Planet[] b){
        double res=0;
        for(Planet body:b){
            if(this.equals(body)){continue;}
            else{
            res += this.calcForceExertedByX(body);
            }
        }
        return res;
    }
    public double calcNetForceExertedByY(Planet[] b){
        double res=0;
        for(Planet body:b){
            if(this.equals(body)){continue;}
            else{
            res += this.calcForceExertedByY(body);
            }
        }
        return res;        
    }
    public void update(double dt,double fX,double fY){
        this.xxVel = this.xxVel+dt*(fX/this.mass);
        this.yyVel = this.yyVel+dt*(fY/this.mass);
        this.xxPos = this.xxPos+dt*this.xxVel;
        this.yyPos = this.yyPos+dt*this.yyVel;
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
