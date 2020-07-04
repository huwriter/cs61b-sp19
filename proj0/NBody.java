public class NBody{
	public static double readRadius(String file){
		In in =new In(file);
		int line=in.readInt();
		double radius=in.readDouble();
		return radius;
	}
	public static Body[] readBodies(String file){
		In in =new In(file);
		int line=in.readInt();
		in.readDouble();
		Body[] body=new Body[line];
		for(int i=0;i<line;i++)
		{
			double xpf=in.readDouble();
			double ypf=in.readDouble();
			double xvf=in.readDouble();
			double yvf=in.readDouble();
			double mass=in.readDouble();
			String name=in.readString();
			body[i]= new Body(xpf, ypf, xvf, yvf, mass, name);
			
		}
		return body;
	}
	public static void main(String[] args)
	{
		double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double uniRadius = NBody.readRadius(fileName);
        Body[] Planets = NBody.readBodies(fileName);

        StdDraw.setScale(-uniRadius,uniRadius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");

        for(Body n:Planets)
        {
        	n.draw();
        }
        StdDraw.enableDoubleBuffering();
        for(int t=0;t<=T;t+=dt)
        {
        	double[] xForces=new double[Planets.length];
        	double[] yForces=new double[Planets.length];
        	for (int i=0;i<Planets.length;i++) 
        	{
        		xForces[i]=Planets[i].calcNetForceExertedByX(Planets);
        		yForces[i]=Planets[i].calcNetForceExertedByY(Planets);			
        	}
        	for(int i=0;i<Planets.length;i++)
        	{
        		Planets[i].update(dt,xForces[i],yForces[i]);
        	}
        	StdDraw.picture(0,0,"images/starfield.jpg");
        	for(int i=0;i<Planets.length;i++)
        	{
        		Planets[i].draw();
        	}
        	StdDraw.show();
        	StdDraw.pause(10);
        }
        StdOut.printf("%d\n",Planets.length);
		StdOut.printf("%.2e\n",uniRadius);
		for (int i = 0; i<Planets.length; i++)
		    {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                  Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
			}
    }
}	
	

	
	
	
	

	
