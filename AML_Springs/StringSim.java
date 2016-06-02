import java.awt.Color;
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.DisplayFrame;
import org.opensourcephysics.frames.PlotFrame;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/*
 * Annabel Strauss and Cal Lavicka
 * March/April 2015
 * Comp Sci X
 * Springs 
 * Version 1.4
 */
public class StringSim extends AbstractSimulation {

  ArrayList<Particle> springs = new ArrayList<Particle>();
  DisplayFrame frame = new DisplayFrame("X", "Y", "Display Frame Test");
  PlotFrame frame2 = new PlotFrame("X", "Y", "Max Y vs. time");
  PlotFrame frame3 = new PlotFrame("X", "Y", "Particle Y vs. time");

  double time = 0;
  double timestep = 0;
  double distance = 0;
  double xdist = 0;
  double ydist = 0;
  double xforce = 0;
  double yforce = 0;
  double k = 0;

  boolean toning = false;
  Thread soundThread = new Thread(new Runnable() {

    @Override
    public void run() {
      toning = true;
      try {
        sound((int) control.getDouble("frequency"), 1000, 0.8);
      } catch (LineUnavailableException e) {
        e.printStackTrace();
      }

      toning = false;
    }
  });

  protected void doStep() {
    time += timestep; // this is the timestep
    //moves the first particle
    springs.get(0).setY(Math.sin(control.getDouble("frequency") * time / (2 * Math.PI)) * control.getDouble("amplitude"));
    
    //moves the last particle (if you turn the wall off)
    if(control.getBoolean("wall on") == false){
      springs.get(springs.size()-1).setY(Math.sin(control.getDouble("frequency 2") * time / (2 * Math.PI)) * control.getDouble("amplitude 2"));
    }
    
    //updates the info of each particle (using the step function in the ptcl class)
    for (int i = 1; i < springs.size() - 1; i++) {
      if (i + 1 < springs.size()) {
        springs.get(i).step(timestep, springs.get(i - 1), springs.get(i + 1));
      } 
      else {
        springs.get(i).step(timestep, springs.get(i - 1), null);
      }
    }
    
    //literally moves each particle on the screen 
    for (int i = 1; i < springs.size() - 1; i++) {
      springs.get(i).updatePosition();
    }
    
    //MAKES SOUND!
    if(control.getBoolean("sound") == true){
      if(!soundThread.isAlive()) {
        soundThread = new Thread(new Runnable() {

          @Override
          public void run() {
            toning = true;
            try {
              sound((int) control.getDouble("frequency"), 1000, 0.8);
            } catch (LineUnavailableException e) {
              e.printStackTrace();
            }

            toning = false;
          }
        });
        soundThread.start();
      }
    }

    double maxy = 0;
    float hue = 0;
    //graph that does Max Y position vs. time
    for (int i = 0; i < springs.size() - 1; i++) {
      if(springs.get(i).getY() > maxy) { 
        maxy = springs.get(i).getY(); //finds the maximum Y position 
      }    
    }//for
    frame2.append(20, time, maxy); //puts the max Y point on the graph 

    double y = 0;
    //graph that does Y position of every ptcl vs. time 
    for (int i = 0; i < springs.size()-1; i+=20) {
      y = springs.get(i).getY(); //finds the y position of the ptcl 
      frame3.append(i, time, y); //puts the y position on the graph
    }

  }// do step

  /**
   * This function resets the simulation. It is called if you press the
   * "reset" button the control panel. It just wipes the screen clean and lets
   * you enter in new values in the control panel. With the reset button, you
   * don't need to close the window and re-run your program, you can just
   * reset it. It returns all the values to zero
   */
  public void reset() {
    springs.clear(); // clears the balls so it can start fresh

    control.setValue("length", 0);
    control.setValue("distance btwn particles", .01);
    control.setValue("spring constant k", 10);
    control.setValue("time step", .01);
    control.setValue("mass", 1);
    control.setValue("frequency", Math.PI * 2);
    control.setValue("amplitude", 0.1);
    control.setValue("string tension", 2.0);
    control.setValue("wall on", true);
    control.setValue("frequency 2", Math.PI * 2);
    control.setValue("amplitude 2", 0.1);
    control.setValue("sound", false);

    if(toning) soundThread.stop();
    toning = false;
  }

  public void initialize() {
    // makes the display frame
    time = 0;
    springs.clear();

    frame.setVisible(true);
    frame.setPreferredMinMaxX(-1, 1);
    frame.clearDrawables();

    double length = control.getDouble("length");
    double distbtwn = control.getDouble("distance btwn particles");
    double mass = control.getDouble("mass");

    k = control.getDouble("spring constant k");
    timestep = control.getDouble("time step");

    setDelayTime(0);
    setStepsPerDisplay(10);

    float hue = 0;

    double indK = k * (length / distbtwn);

    for (int i = 0; i < Math.round(length / distbtwn); i++) {
      springs.add(new Particle()); // adds a spring to the array list of
      // springs (spring is a new
      // Particle)
      springs.get(i).pixRadius = 2; // makes the radius of the ball
      // smaller
      springs.get(i).setX(i * distbtwn);
      springs.get(i).setMass(mass);
      springs.get(i).k = indK;
      springs.get(i).color = new Color(Color.HSBtoRGB(hue, 1, 1));
      frame3.setMarkerColor(i, springs.get(i).color);
      springs.get(i).originalDistance = distbtwn / control.getDouble("string tension");
      hue += distbtwn / length * 0.8f;

      frame.addDrawable(springs.get(i)); // adds the spring to the frame
      // (so you can see it)
    }

    if(control.getBoolean("wall on") == true){
      // Creates a rectangle with center (60, 0), width of 120, and height of .5
      DrawableShape rectangle = DrawableShape.createRectangle(length + 0.05, 0, 0.1, 2);
      rectangle.setMarkerColor(Color.blue, Color.blue); // changes the interior, border
      frame.addDrawable(rectangle); // draws the rect on the graph
    }
  }// initialize

  public static float SAMPLE_RATE = 8000f;

  public static void sound(int hz, int msecs, double vol)
      throws LineUnavailableException {

    if (hz <= 0)
      throw new IllegalArgumentException("Frequency <= 0 hz");

    if (msecs <= 0)
      throw new IllegalArgumentException("Duration <= 0 msecs");

    if (vol > 1.0 || vol < 0.0)
      throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");

    byte[] buf = new byte[(int) SAMPLE_RATE * msecs / 1000];

    for (int i = 0; i < buf.length; i++) {
      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
      buf[i] = (byte) (Math.sin(angle) * 127.0 * vol);
    }

    // shape the front and back 10ms of the wave form
    for (int i = 0; i < SAMPLE_RATE / 100.0 && i < buf.length / 2; i++) {
      buf[i] = (byte) (buf[i] * i / (SAMPLE_RATE / 100.0));
      buf[buf.length - 1 - i] = (byte) (buf[buf.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
    }

    AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
    sdl.open(af);
    sdl.start();
    sdl.write(buf, 0, buf.length);
    sdl.drain();
    sdl.close();
  }

  /**
   * gives the simulation the ability to run
   * 
   * @param args
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new StringSim());
  }

}// class
