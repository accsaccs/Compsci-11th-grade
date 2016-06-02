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
 * This class is essentially the canon, or the person swinging the bat. It sets up and launches the balls based on 
 * values that you put in the control panel. 
 * The point of this program is to model the fact that if two balls have the same initial velocity and complementary angles, they will
 * land in the same spot (with no air resistance). I set this up so that the user inputs a velocity in the control panel, and then 
 * all the balls have this velocity. The angles are hard coded into the program, and they are symmetrical about angle 45 (so they range from
 * 15 to 75). The balls with the largest and the smallest angles are red, the balls with the second to largest and second to smallest angles
 * are orange, and so on. The colors are matched up in this way because once the balls are launched, the ones with matching colors land in the
 * same place (on top of each other). Therefore it is a visual demonstration of the complementary angles/same velocity idea.  
 * Because it is an odd number of balls, and you are going from the outside in, the last ball is the 45 degree angle one.
 * This ball is grey, and it always goes the farthest (no matter the velocity). This illustrates the fact that the angle that will 
 * maximize a ball's distance is 45 degrees (if launched from the ground).
 * Also, this program shows the effects of air resistance because if you give it an air resistance, you can see how the complementary angles
 * idea doesn't not hold true anymore. The balls with matching colors don't land in the same place because the air resistance is having 
 * an effect on them that makes them not go as far, and it is more or less impactful depending on the ball's angle. 
 *
 */
public class ProjectileLauncher extends AbstractSimulation {

	//Particle ball = new Particle();
	ArrayList<Particle2> balls = new ArrayList<Particle2>(); //declares the array list of particles 
	ArrayList<Trail> trails = new ArrayList<Trail>(); //declares the array list of trails 
	
	DisplayFrame frame = new DisplayFrame("X", "Y", "Display Frame Test"); //makes the frame
	
	double i = 0;

	/**
	 * This method has to be here, otherwise the program won't run. This is a loop in itself. It is what makes the balls fly across
	 * the screen. This is run when you press the "start" button the control panel, and it stops when you press the "stop" button the control
	 * panel. It launches all the balls that are in the array list, and also shows their trails on the frame. It only runs when the y position
	 * of the ball is >0, so that the balls stop when they get to 0.
	 */
	protected void doStep() 
	{	
		for (int i = 0; i < balls.size(); i++) 
		{
			if(balls.get(i).getYpos() >= 0)
			{
				balls.get(i).deltax();
				balls.get(i).deltay();			
				balls.get(i).move();
				trails.get(i).addPoint(balls.get(i).getXpos(), balls.get(i).getYpos());
				frame.addDrawable(trails.get(i));
			}
			
		}//for		
	}//do step
	
	/**
	 * This function resets the simulation. It is called if you press the "reset" button the control panel. It just wipes the screen 
	 * clean and lets you enter in new values in the control panel. With the reset button, you don't need to close the window and re-run
	 * your program, you can just reset it. 
	 * It returns all the values to zero
	 */
	public void reset()
	{	
		trails.clear(); 
		balls.clear();
		frame.clearDrawables();
		
		control.setValue("xpos", 0);
		control.setValue("ypos", 0);
		control.setValue("angle", 0);
		control.setValue("velocity", 0);
		//control.setValue("vx", 0); 
		//control.setValue("vy", 0); 
		control.setValue("xacc", 0);
		control.setValue("yacc", 0);
		control.setValue("alpha", 0);
		
		
	}
	/**
	 * This function is really important because it's where all the balls are made and given their colors. It goes through a loop and makes
	 * the balls with angles from 15 to 75 (with increments of 5). It also gives each ball a trail. The balls (and their trails) are then given
	 * colors. The balls with the highest and lowest angles are red, then the balls with the second to highest and second to lowest angles 
	 * are orange, and so on. The last ball, which is right in the middle (45 degrees), is grey. 
	 * Then this function sets all the values for the ball. 
	 */
	public void initialize()
	{
		frame.setVisible(true);
		frame.setPreferredMinMaxX(0, 150);
		frame.clearDrawables();
		
		//for loop that makes the balls
		for(int i = 0; i < 13; i++)
		{ 
			balls.add(new Particle2()); //adds ball to array list of balls
			balls.get(i).pixRadius = 4; //makes the radius smaller
			
			trails.add(new Trail()); //adds trail to the array lis of trails (so each ball has a trail) 	

			double angle2 = 75-5*i; //75 to 15
			
			//COLORS 
			if(angle2 == 75-5*0 || angle2 == 75-5*12)
			{
				balls.get(i).color = Color.red;
				trails.get(i).color = Color.red;
			}
			else if(angle2 == 75-5*1 || angle2 == 75-5*11)
			{
				balls.get(i).color = Color.orange;
				trails.get(i).color = Color.orange;
			}
			else if(angle2 == 75-5*2 || angle2 == 75-5*10)
			{
				balls.get(i).color = Color.yellow;
				trails.get(i).color = Color.yellow;
			}
			else if(angle2 == 75-5*3 || angle2 == 75-5*9)
			{
				balls.get(i).color = Color.green;
				trails.get(i).color = Color.green;
			}
			else if(angle2 == 75-5*4 || angle2 == 75-5*8)
			{
				balls.get(i).color = Color.blue;
				trails.get(i).color = Color.blue;
			}
			else if(angle2 == 75-5*5 || angle2 == 75-5*7)
			{
				balls.get(i).color = Color.pink;
				trails.get(i).color = Color.pink;
			}
			else if(angle2 == 75-5*6)
			{
				balls.get(i).color = Color.lightGray;
				trails.get(i).color = Color.lightGray;
			}
				
			//sets the information that each ball needs 
			balls.get(i).setAngle(angle2);	
			//balls.get(i).setVelocity(velocity2);	
			balls.get(i).setVelocity(control.getDouble("velocity"));
			balls.get(i).setXpos(control.getDouble("xpos"));
			balls.get(i).setYpos(control.getDouble("ypos"));
			//balls.get(i).setAngle(control.getDouble("angle"));
			//balls.get(i).setVelocity(control.getDouble("velocity"));
			balls.get(i).setVx(balls.get(i).getVelocity()*Math.cos(Math.toRadians(balls.get(i).getAngle())));		//ball.setVx(control.getDouble("vx"));
			balls.get(i).setVy(balls.get(i).getVelocity()*Math.sin(Math.toRadians(balls.get(i).getAngle())));		//ball.setVy(control.getDouble("vy"));
			balls.get(i).setXacc(control.getDouble("xacc"));
			balls.get(i).setYacc(control.getDouble("yacc"));
			balls.get(i).setAlpha(control.getDouble("alpha"));
			
			frame.addDrawable(balls.get(i)); //adds the ball to the frame so you can see it 
			
		} 
		
				
		
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
		SimulationControl.createApp(new ProjectileLauncher());
	}
	
	
}//class 


