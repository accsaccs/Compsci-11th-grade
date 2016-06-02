import java.awt.Color;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;

import java.util.Random;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * November 2014 
 * 
 * This class is essentially the canon, or the person swinging the bat. It sets up and launches the ball based on 
 * values that you put in the control panel. 
 * The way I found the optimal velocity was by shooting a bunch of balls at different velocities and angles, and then finding the one
 * with the lowest velocity. The amount of balls is determined by the range of angles, angle increment, range of velocities, and velocity
 * increment. The user inputs all those values. Then the computer calculates the number of balls that have to be launched based on these
 * values, and then launches all those balls. The balls when launched are red, and their trails are orange. If the ball goes over the fence,
 * the ball turns black and it's trail turns green. This acts as a visual aid to seeing which balls, and how many balls, make it over the 
 * fence. The program finds which ball (that made it over) has the lowest velocity, and prints that velocity and the angle at which it was 
 * "hit". It also prints the ball's final velocity. It's cool to compare the initial and final velocities - it also explains why people
 * in the baseball stands can catch balls that are hit with lots of power. 
 * Everything is set up in the initialize function. This function is where all the balls are made. When you press start on the 
 * control panel, it launches all the balls. The balls going across the screen happens in the do step function. If you press reset on the
 * control panel, you can start fresh - aka make a new simulation (without having to restart the whole program)
 */
public class MainLauncher extends AbstractSimulation {

	//Particle ball = new Particle();
	ArrayList<Particle> balls = new ArrayList<Particle>(); //declares the array list of particles 
	ArrayList<Trail> trails = new ArrayList<Trail>(); //declares the array list of trails 
	
	DisplayFrame frame = new DisplayFrame("X", "Y", "Display Frame Test"); //makes the frame where the balls show up
	
	double i = 0;
	double min = 100;

	/**
	 * This method has to be here, otherwise the program won't run. This is a loop in itself. It is what makes the balls fly across
	 * the screen. This is run when you press the "start" button the control panel, and it stops when you press the "stop" button the control
	 * panel. It launches all the balls that are in the array list, and also shows their trails on the frame. It only runs when the y position
	 * of the ball is >0, so that the balls stop when they get to 0. If the balls get over the fence, this turns the ball black and its
	 * trail green. 
	 * This is also where I find the minimum velocity and its corresponding angle. For every ball that is launched, its initial velocity is
	 * checked. If its initial velocity is smaller than the one before it, it is kept. This continues until there are no smaller 
	 * initial velocities, which means that the smallest one has been found.  
	 */
	protected void doStep() 
	{	
		for (int i = 0; i < balls.size(); i++) //this means go thru for every ball in the array list 
		{
			if(balls.get(i).getYpos() >= 0) //this is so that the balls don't keep going if they're below the "ground" (y = 0)
			{
				balls.get(i).deltax(); //function that finds the x position 
				balls.get(i).deltay();	//function that finds the y position 		
				balls.get(i).move(); //function that actually moves the ball
				trails.get(i).addPoint(balls.get(i).getXpos(), balls.get(i).getYpos()); //makes the trail 
				frame.addDrawable(trails.get(i)); 
			}
			if(balls.get(i).getXpos() >= 120 && balls.get(i).getYpos() >= 2 ) //this turns the trail green if ball goes over fence
			{
				trails.get(i).color = Color.green;
			}
			
			//this finds the ball with the lowest initial velocity, and prints out that velocity, the angle, and the final velocity 
			if(balls.get(i).getXpos() >= 120 && balls.get(i).getYpos() >= 2 && balls.get(i).getVelocity() < min)
			{
				double finalV = Math.sqrt(balls.get(i).getVx()*balls.get(i).getVx()+balls.get(i).getVy()*balls.get(i).getVy());
				
				min = balls.get(i).getVelocity();
				System.out.println("min: " + min);
				System.out.println("angle: " + balls.get(i).getAngle());
				System.out.println("final velocity: " + finalV + " <-- huge decrease in V! that's why people can catch the ball");
			}
				
		}//for		
	}//do step
	
	/**
	 * This function resets the simulation. It is called if you press the "reset" button the control panel. It just wipes the screen 
	 * clean and lets you enter in new values in the control panel. With the reset button, you don't need to close the window and re-run
	 * your program, you can just reset it. 
	 * It returns all the values to zero (except for alpha, which is .01)
	 */
	public void reset()
	{	
		trails.clear(); //clears the trails so it can start fresh 
		balls.clear(); //clears the balls so it can start fresh 
		
		//control.setValue("xpos", 0);
		//control.setValue("ypos", 0);
		//control.setValue("angle", 0);
		//control.setValue("velocity", 0);
		//control.setValue("vx", 0); 
		//control.setValue("vy", 0); 
		//control.setValue("xacc", 0);
		//control.setValue("yacc", 0);
		control.setValue("alpha", .01);
		control.setValue("starting velocity", 0);
		control.setValue("ending velocity", 0);
		control.setValue("velocity increment", 0);
		control.setValue("starting angle", 0);
		control.setValue("ending angle", 0);
		control.setValue("angle increment", 0);		
	}
	
	/**
	 * This function is really important because it sets everything up. You type in all the values you want into the control panel and
	 * once you hit "initialize", this runs. 
	 * First it takes the range of velocities and the velocity increment, and the range of angles and the angle increment, and it figures
	 * out the number of velocities and the number of angles it'll be using. Then it goes through 2 for loops, one for velocities and one
	 * for angles. It creates a ball for every angle at every velocity (so the total number of balls is the number of velocities times
	 * the number of angles). 
	 * Then it gives the ball the information that it needs in order to correctly model a projectile. It sets the angle and the velocity, and 
	 * from those two values it can calculate the x velocity and the y velocity. It also sets the alpha (air resistance). This is automatically
	 * set to .01 in the control panel, but it can be changed by the user in the control panel. 
	 * This function also draws the "baseball field". The fence is 120 meters from home plate, and 3 meters high. Because the batter is 1
	 * meter high, instead of starting at (0,1), I start at (0,0) and my fence is only 2 meters high. So my baseball field is just  
	 * two rectangles, one 120 meters long, and the other 2 meters long. 
	 */
	public void initialize()
	{
		//makes the display frame 
		frame.setVisible(true);
		frame.setPreferredMinMaxX(0, 150);
		frame.clearDrawables();
		
		//declarations
		double angle2 = 0;
		double velocity2 = 0;
		double vstart = 0;
		double vend = 0;
		double vinc = 0;
		double prenumV = 0;
		int numV = 0;
		double anglestart = 0;
		double angleend = 0;
		double angleinc = 0;
		double prenumAngles = 0;
		int numAngles = 0;
		
		//makes the simulation run faster
		super.delayTime = 0;
		super.stepsPerDisplay = 10;
	
		//takes this info from the control panel
		vstart = control.getDouble("starting velocity");
		vend = control.getDouble("ending velocity");
		vinc = control.getDouble("velocity increment");
		prenumV = ((vend-vstart)/vinc)+1; //figures out the number of velocities there are (based on the user's input of velocity range and increment)
		numV = (int) prenumV; //turns prenumV into an int (this is necessary for later loops)
		anglestart = control.getDouble("starting angle");
		angleend = control.getDouble("ending angle");
		angleinc = control.getDouble("angle increment");
		prenumAngles = ((angleend-anglestart)/angleinc)+1; //figures out the number of angles there are (based on the user's input of angle range and increment)
		numAngles = (int) prenumAngles; //turns prenumAngles into an int (this is necessary for later loops)
		
		for(int i = 0; i < numV; i++) //loop that goes thru velocities 
		{ 
			System.out.println("Num velocities: " + numV); //debug statement
			velocity2 = vstart + (vinc*i); 
			//System.out.println("v: " + velocity2);
			
			for (int j = 0; j < numAngles; j++) //loop that goes thru the angles
			{
				System.out.println("Num angles: " + numAngles); //debug statement
				angle2 = anglestart + (angleinc*j);
				//System.out.println("angle: " + angle2); 
				
				balls.add(new Particle()); //adds a ball to the array list of balls (ball is a new Particle)
				balls.get(numAngles*i+j).pixRadius = 4; //makes the radius of the ball smaller
				System.out.println("NUMANGLES*I+J: " + (numAngles*i+j)); //debug statement 
				
				trails.add(new Trail()); //adds the trail for the ball
				trails.get(numAngles*i+j).color = Color.orange; 
					
				//--------------------------------------------------------// 
				
				//sets the information that each ball needs 
				balls.get(numAngles*i+j).setAngle(angle2);	
				balls.get(numAngles*i+j).setVelocity(velocity2);
				//balls.get(numAngles*i+j).setXpos(control.getDouble("xpos"));
				//balls.get(numAngles*i+j).setYpos(control.getDouble("ypos"));
				//balls.get(j).setAngle(control.getDouble("angle"));
				//balls.get(j).setVelocity(control.getDouble("velocity"));
				balls.get(numAngles*i+j).setVx(balls.get(numAngles*i+j).getVelocity()*Math.cos(Math.toRadians(balls.get(numAngles*i+j).getAngle()))); //calculates the x velocity based on initial velocity and the angle 
				balls.get(numAngles*i+j).setVy(balls.get(numAngles*i+j).getVelocity()*Math.sin(Math.toRadians(balls.get(numAngles*i+j).getAngle()))); //calculates the y velocity based on initial velocity and the angle 
				//balls.get(numAngles*i+j).setXacc(control.getDouble("xacc"));
				//balls.get(numAngles*i+j).setYacc(control.getDouble("yacc"));
				balls.get(numAngles*i+j).setAlpha(control.getDouble("alpha"));
				
				frame.addDrawable(balls.get(numAngles*i+j)); //adds the ball to the frame (so you can see it)
					
			}//for j
		}//for i
		System.out.println("balls: " + balls.size()); //debug statement - prints the size of the array list
		
		
		//THE BASEBALL FIELD
		//Creates a rectangle with center (60, 0), width of 120, and height of .5
        DrawableShape rectangle = DrawableShape.createRectangle(60,.25,120,.5);  
        rectangle.setMarkerColor(Color.blue,Color.blue);  //changes the interior to blue and the border to green.   
        frame.addDrawable(rectangle); //draws the rect on the graph  
        //Creates a rectangle with center (120, .5), width of .5, and height of 1.5
        DrawableShape rectangle2 = DrawableShape.createRectangle(119.5,1,1,1.5);  
        rectangle2.setMarkerColor(Color.blue,Color.blue);  //changes the interior to blue and the border to green.   
        frame.addDrawable(rectangle2); //draws the rect on the graph 
		

	}
	
	/**
	 * gives the simulation the ability to run
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SimulationControl.createApp(new MainLauncher());
	}
	
	
}//class 


