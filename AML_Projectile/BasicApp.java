import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;


public class BasicApp extends AbstractSimulation 
{
	int x;
	
	protected void doStep()
	{
		control.println("X = " + x);
		x++;
	
	}
	
	public void reset()
	{
		control.setValue("x", 50);
	}
	
	public void initialize()
	{
		int z = control.getInt("x");
	}
	
	
	public static void main(String[] args) {
		SimulationControl.createApp(new BasicApp());
	}
}
