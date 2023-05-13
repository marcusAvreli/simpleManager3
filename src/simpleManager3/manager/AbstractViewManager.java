package simpleManager3.manager;

import java.awt.Container;
import java.util.Collection;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleManager3.application.Application;
import simpleManager3.container.ViewContainer;
import simpleManager3.perspective.PerspectiveConstraint;



/**
 * A default implementation of View Manager. It manages the views added to the application
 * and those which are removed from the application. It also is resposible of launching
 * the views lifecycle and add them to the current perspective. 
 * 
 * It's also responsible for keeping the visual part of the application stable.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/view/AbstractViewManager.java
public abstract class AbstractViewManager implements ViewManager
{
	private Application 				application;
	private Map<Object,ViewContainer> 	views;
	private static final Logger logger = LoggerFactory.getLogger(AbstractViewManager.class);
	/**
	 * Default constructor. It creates a new List where the views are
	 * added.
	 */
	public AbstractViewManager(){
		this.views = new HashMap<Object,ViewContainer>();		
	}

	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.view.ViewManager#addView(org.viewaframework.view.ViewContainer, org.viewaframework.view.perspective.PerspectiveConstraint)
	 */
	public void addView(ViewContainer view){	 
		
		Map<Object,ViewContainer> 				viewContainerCollection = this.getViews();
		ViewContainer 							viewContainer 			= viewContainerCollection.get(view.getId());		
		Application								app						= this.getApplication();
		
	
	 /* Then application instance is injected in the view */
		view.setApplication(app);
		
		
	}
	
	
	
	
	
	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationAware#getApplication()
	 */
	public Application getApplication() {
		return this.application;
	}
	
	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#getViews()
	 */
	public Map<Object, ViewContainer> getViews() {
		return this.views;
	}

	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#removeView(org.viewa.view.View)
	 */
	public void removeView(ViewContainer view) {
		
	}
	/* (non-Javadoc)
	 * @see org.viewa.view.ViewManager#arrangeView()
	 */
	public Container arrangeViews()
	{
		Map<Object,ViewContainer> cviews 					= new HashMap<Object, ViewContainer>();
		Collection<ViewContainer> viewContainerCollection 	= this.getViews().values();
	 /* ViewManager and Perspectives can make different decisions about its views so
	  * it is mandatory to create different view collections. */
		for (ViewContainer view : viewContainerCollection){
			cviews.put(view.getId(), view);
		}
		
		this.getPerspective().setViews(cviews);
		return this.getPerspective().arrange();
	}
	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationAware#setApplication(org.viewa.core.Application)
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
		
}