import java.awt.Color;

import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * November 2014 
 * 
 * This class makes a Particle, which is the baseball for this simulation. It has a bunch of properties, but I don't use some
 * of them. Getters and setters are made for all the properties, then these can be used in the Launcher (so you can get and set stuff 
 * from there). This class has 3 functions, one for changing the x position, one for changing the y position, and one for actually moving
 * the ball (using the x and y position functions). 
 *
 */
public class Particle extends Circle {
	
	//declarations
	double mass;
	double radius;
	double angle;
	double velocity;
	double vx;
	double vy;
	double xpos;
	double ypos;
	double xacc = 0;
	double yacc = 0;
	double timestep = 0.001; 
	double alpha;
		
	//getters and setters
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public double getVx() {
		return vx;
	}
	public void setVx(double vx) {
		this.vx = vx;
	}
	public double getVy() {
		return vy;
	}
	public void setVy(double vy) {
		this.vy = vy;
	}
	public double getXpos() {
		return xpos;
	}
	public void setXpos(double xpos) {
		this.xpos = xpos;
	}
	public double getYpos() {
		return ypos;
	}
	public void setYpos(double ypos) {
		this.ypos = ypos;
	}
	public double getXacc() {
		return xacc;
	}
	public void setXacc(double xacc) {
		this.xacc = xacc;
	}
	public double getYacc() {
		return yacc;
	}
	public void setYacc(double yacc) {
		this.yacc = yacc;
	}
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	
	/**
	 * The point of the method is to move the x position of the ball. If finds the x acceleration based on the formula that incorporates
	 * alpha, Vx, and Vy. To find the x velocity, it uses the x acceleration from before. Then to find the x position, it uses the 
	 * x velocity from before. This is essentially replicating graphs. The acceleration graph feeds into the velocity graph, and the velocity
	 * graph feeds into the position graph. This is called in the doStep function, so that the x information can be set for every ball  
	 */
	public void deltax()
	{
		//setXacc(-alpha*getVx()*getVx()); //the old x acc
		setXacc(-alpha*getVx()*Math.sqrt(getVx()*getVx()+getVy()*getVy())); //sets the x acceleration
		setVx(getVx() + getXacc()*timestep); //sets the x velocity 
		setXpos(getXpos() + getVx()*timestep); //sets the y velocity 
		//System.out.println("VELOCITY: " + vx); //debug statement 
					
	}
	
	/**
	 * The point of the method is to move the y position of the ball. If finds the y acceleration based on the formula that incorporates
	 * alpha, Vx, and Vy. To find the y velocity, it uses the y acceleration from before. Then to find the y position, it uses the 
	 * y velocity from before. This is essentially replicating graphs. The acceleration graph feeds into the velocity graph, and the velocity
	 * graph feeds into the position graph. This is called in the doStep function, so that the y information can be set for every ball  
	 */
	public void deltay()
	{	
		//setYacc(-9.8 - ((getVy()/Math.abs(getVy())*alpha*getVy()*getVy()))); //the old y acc 
		setYacc(-9.8 - ((getVy()/Math.abs(getVy())*alpha*getVy()*Math.sqrt(getVx()*getVx()+getVy()*getVy())))); //sets the y acceleration 
		setVy(getVy() + getYacc()*timestep); //sets the x velocity
		setYpos(getYpos() + getVy()*timestep); //sets the y velocity 
		//System.out.println("THE Y VELOCITY: " + vy); //debug statement 
	}
	
	/**
	 * This method actually moves the ball. It takes the x position and y position from the functions above and tells the circle where to go. 
	 * So it moves the ball across the screen. Also, when the ball gets over the fence, the ball turns black 
	 */
	public void move()
	{		
		//System.out.println("x: " + xpos); //debug statement 
		//System.out.println("y: " + ypos); /debug statement 
		super.setXY(this.xpos, this.ypos); //tells the circle where to be
			
		if(x >= 120 && y <= 2) //makes the ball black when goes over fence
		{
			super.color = Color.black;
		}
	}
	
	
	

	
}
