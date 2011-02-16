/*
 * Created Jul 21, 2010
 */
package ltg.ps.api.phenomena;

import ltg.ps.api.Command;


/**
 * Represents a command sent to a specific phenomena.
 *
 * @author Gugo
 */
public abstract class PhenomenaCommand implements Command{
	
	
	protected Phenomena target = null;
	
	
	/**
	 * Constructor.
	 * The phenomena passed as input is in charge of handling this command.
	 * 
	 * @param target Phenomena that will handle the command.
	 */
	public PhenomenaCommand(Phenomena target) {
		this.target = target;
	}

}
