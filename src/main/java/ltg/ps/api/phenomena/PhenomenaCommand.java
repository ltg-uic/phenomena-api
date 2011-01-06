/*
 * Created Jul 21, 2010
 */
package ltg.ps.api.phenomena;

import ltg.ps.api.Command;


/**
 * TODO Description
 *
 * @author Gugo
 */
public abstract class PhenomenaCommand implements Command{
	
	
	protected Phenomena target = null;
	
	public PhenomenaCommand(Phenomena target) {
		this.target = target;
	}

}
