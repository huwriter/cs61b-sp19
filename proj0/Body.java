public class Body {
 
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
 
 
    public String imgFileName;
 
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
 
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.hypot(dx, dy);
        return r;
    }
 
    public double calcForceExertedBy(Body b){
        double G = 6.67e-11;//
        double r1=calcDistance(b);
        double F=G*this.mass*b.mass/(r1*r1);
        return F;
    }

public double calcForceExertedByX(Body b) {
        double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return Fx;
    }
 
    public double calcForceExertedByY(Body b) {
        double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return Fy;
    }
 
    public double calcNetForceExertedByX(Body[] bs) {
        double FxNet = 0;
        for (Body c : bs) {
            if (!this.equals(c)) {
                
                FxNet += this.calcForceExertedByX(c);
            }
        }
        return FxNet;
    }
 
    public double calcNetForceExertedByY(Body[] bs) {
        double FyNet = 0;
        for (Body b : bs) {
            if (!this.equals(b)) {
                
                FyNet += this.calcForceExertedByY(b);
            }
        }
        return FyNet;
    }
 
    public void update(double dt,double fx,double fy){
        double Ax=fx/this.mass;
        double Ay=fy/this.mass;
        this.xxVel += Ax * dt;
        this.yyVel += Ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
 
 
    public void draw() {
 
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}