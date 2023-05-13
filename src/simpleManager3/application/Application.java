package simpleManager3.application;

import java.util.Locale;

import simpleManager3.container.DefaultViewContainerFrame;
import simpleManager3.container.ViewContainerFrame;



/**
 * This is the entry point of any application. From Application object
 * views are loaded and unloaded through its ViewManager.<br/><br/>
 * It is also responsible for connecting views to its controllers 
 * through its ControllerDispatcher.<br/><br/>
 * 
 * Before the first view is shown some background work is done. The
 * application lifecycle starts at <br/><br/>
 * <code>execute(Application)</code><br/><br/>
 * This method launches the the viewManager in the EventDispatcherThread while
 * it creates a new Thread for the main application code.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
public interface Application {

/**
 * 
 */
public void close();

/**
 * The name of the application is not very important, but its used
 * as default title name if the application is frame based.
 * 
 * @return the name of the application
 */
public abstract String getName();

/**
 * This method returns the current locale
 * 
 * @return java.util.Locale
 */
//public Locale getLocale();


/**
 * Returns whether the application is visible or not
 * 
 * @return
 */
public boolean isVisible();


/**
 * Establish the root view of the application
 * 
 * @param rootView
 * @since 1.0.4
 */
public abstract void setRootView(ViewContainerFrame rootView);
public void prepare() ;
public void prepareUI(DefaultViewContainerFrame f) ;
public void initUI();

/**
 * This method sets the visibility of the root view
 * 
 * @param visible
 * @since 1.0.4
 */
public void setVisible(boolean visible);
}