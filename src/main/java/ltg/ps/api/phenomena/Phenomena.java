/*
 * Created Jun 22, 2009
 */
package ltg.ps.api.phenomena;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents a generic phenomenon type.
 * All phenomena in the server extend this class.  
 *
 * @author Gugo
 */
public abstract class Phenomena extends Observable {

	// Logger
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// Phenomenon's unique ID
	protected String instanceName = null;
	// Phenomenon's windows
	protected List<PhenomenaWindow> phenWindows = null;
	// Is the phenomena being served?
	protected Boolean running = false;
	
	
	
	/**
	 * Private empty constructor to prevent instantiation from other classes 
	 */
	private Phenomena() {
	}

	
	
	/**
	 * Assigns a unique ID to the phenomenon. 
	 * Please use a meaningful names for the instances 
	 * like "[PhenomenaName]_[School/Class]".
	 * @param instanceId
	 */
	public Phenomena(String instanceId) {
		this();
		this.instanceName = instanceId;
		this.phenWindows = new ArrayList<PhenomenaWindow>();
	}
	
	
	
	/**
	 * Returns the phenomena's unique ID
	 *
	 * @return phenomenon's unique ID
	 */
	public String getInstanceName() {
		return instanceName;
	}
	
	
	
	public List<PhenomenaWindow> getWindows() {
		return phenWindows;
	}
	
	
	
	public synchronized boolean isRunning() {
		return running;
	}
		
	
	
    /**
     * Starts the phenomenon.
     * This method must be implemented as synchronized!
     */
	public abstract void start();
	
	
	
	/**
	 * Stops the phenomenon.
	 * This method must be implemented as synchronized!
	 */
	public abstract void stop();
	
	
	
	/**
	 * Associates some windows to the phenomenon using XML.
	 * Once a particular phenomenon instance has been created, 
	 * its windows must be configured.
	 *
	 * @param configXML XML data used to configure the phenomenon windows
	 */
	abstract public void configureWindows(String configXML);
	
	
	
	/**
	 * Configures the phenomenon using XML.
	 * Once a particular phenomenon instance has been created, 
	 * it must be populated with data. This method uses XML to load 
	 * data into the phenomenon instance. For more information on the XML 
	 * syntax please refer to to the Phenomena Server documentation.
	 *
	 * @param configXML XML data used to configure the phenomenon instance
	 */
	abstract public void configure(String configXML);
	
	
	
	/**
	 * Restores the phenomenon instance to the last persisted state. 
	 * This is used when the server is booted and the phenomenon's state 
	 * needs to be restored according to the persisted local data. 
	 * This, of course, depends on the persistence mechanism the 
	 * phenomenon's designer is using. 
	 */
	abstract public void restore();
	
	
	
	/**
	 * Removes all data relative to the phenomenon instance.
	 * This function is called immediately before the server removes 
	 * this phenomenon instance. The type of cleanup necessary depends, of course,
	 * on the persistence mechanism used.
	 */
	abstract public void cleanup();

}
