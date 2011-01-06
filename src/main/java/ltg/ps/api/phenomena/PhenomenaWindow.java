/*
 * Created Sep 17, 2009
 */
package ltg.ps.api.phenomena;

import java.util.Observable;
import java.util.Observer;


/**
 * This class describes a window you can have on a meta-phenomena.
 * A window is nothing more than a subset of the variables of the 
 * instance a particular client is interested in. 
 * Thanks to this mechanism you can isolate any portion of the 
 * phenomena space and map it to an arbitrary portion of the physical
 * space that is represented by any display device.   
 *
 * @author Gugo
 */
abstract public class PhenomenaWindow implements Observer {
	// Used to propagate updates over the net
	//protected NetworkController net = NetworkController.getInstance();
	
	// Name of the phenomena
	protected String windowId = null;

	
	/**
	 * Assigns the name to the window. 
	 * Please use a meaningful name for the window 
	 * like "[ComputerName/FormFactor]>[PhenomenaInstance]".
	 * @param instanceName
	 */
	public PhenomenaWindow(String windowName) {
		// TODO before this check this is unique!!!
		this.windowId = windowName;
	}
	
	
	@Override
	public void update(Observable o, Object arg){
		update((Phenomena)o);
	}
	
	/**
	 * Fetches relevant information from the phenomena instance
	 * this windows is observing.
	 *
	 * @param p the phenomena instance this window is observing
	 */
	abstract public void update(Phenomena p);
	
	
	/**
	 * Converts the data in XML format (to be sent over the network or stored in a file)
	 *
	 * @return the XML description of the window
	 */
	abstract public String toXML();
	
	
	public String getWindowId() {
		return windowId;
	}
	
}
