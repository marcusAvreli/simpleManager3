package simpleManager3.manager;

import simpleManager3.application.Application;

public interface ApplicationAware
{
	/**
	 * Sets the application instance
	 * 
	 * @param application
	 */
	public abstract void setApplication(Application application);
	
	/**
	 * Returns the instance of the current application
	 * 
	 * @return
	 */
	public abstract Application getApplication();

}
