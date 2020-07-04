public class Body{/**这些实例要加public*/
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;


	public Body(double xP, double yP, double xV,
              double yV, double m, String img)
	{
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
		/** instance is left,so right given to left*/
	}

	public Body(Body b)/**no define type*/
	{
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}
	/**to avoid repeat, one use this. one use b.*/
	public double calcDistance(Body b)
	{
		double dx=this.xxPos-b.xxPos;
		double dy=this.yyPos-b.yyPos;
		double s2=dx*dx+dy*dy;
		double distance2=Math.sqrt(s2);
		return distance2;
	}
	public double calcForceExertedBy(Body b)
	{
		double G=6.67e-11;
		double r=calcDistance(b);
		double F=G*this.mass*b.mass/(r*r);
		return F;
	}
	public double calcForceExertedByX(Body b)
	{
		double F=calcForceExertedBy(b);
		double r=calcDistance(b);
		double Fx=F*(b.xxPos-this.xxPos)/r;
		return Fx;
	}
	public double calcForceExertedByY(Body b)
	{
		double F=calcForceExertedBy(b);
		double r=calcDistance(b);
		double Fy=F*(b.yyPos-this.yyPos)/r;
		return Fy;
	}
	/**notice this variable is arrays,so Body[]+ arr's name*/
	public double calcNetForceExertedByX(Body[] allBodys)
	{
		double Fnx=0;
		for(Body n:allBodys)/**through arrys*/
		{
			if(!this.equals(n))/**not give ifself force,for its type 
			is reference type ,use .equal to test if n=this itself?*/
				Fnx+=this.calcForceExertedByX(n);
		}
		return Fnx;
	}
	public double calcNetForceExertedByY(Body[] allBodys)
	{
		double Fny=0;
		for(Body n:allBodys)
		{
			if(!this.equals(n))
				Fny+=this.calcForceExertedByY(n);
		}
		return Fny;
	}
	public void update(double dt,double fX,double fY)
	{
		/**fX,fX is Fnx,Fny,left is calculated by formula*/
		double ax=fX/this.mass;
		double ay=fY/this.mass;
		this.xxVel+=ax*dt;
		this.yyVel+=ay*dt;
		this.xxPos+=dt*this.xxVel;
		this.yyPos+=dt*this.yyVel;
	}
	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}
}
	