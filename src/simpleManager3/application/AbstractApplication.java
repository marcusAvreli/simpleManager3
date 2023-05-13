package simpleManager3.application;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
import org.viewaframework.annotation.processor.AnnotationProcessor;
import org.viewaframework.annotation.processor.ViewsPerspectiveProcessor;
import org.viewaframework.annotation.processor.ViewsProcessor;
import org.viewaframework.annotation.processor.ViewsProcessorWrapper;
import org.viewaframework.controller.DefaultViewControllerDispatcher;
import org.viewaframework.controller.ViewControllerDispatcher;
import org.viewaframework.controller.ViewControllerDispatcherException;
import org.viewaframework.model.AbstractViewModelManager;
import org.viewaframework.model.ViewModelManager;
import org.viewaframework.view.DefaultViewManager;
import org.viewaframework.view.ViewContainer;
import org.viewaframework.view.ViewContainerFrame;
import org.viewaframework.view.ViewException;
import org.viewaframework.view.ViewManager;
import org.viewaframework.view.ViewManagerException;
import org.viewaframework.view.perspective.DefaultPerspective;
import org.viewaframework.view.perspective.Perspective;
*/

import simpleManager3.container.DefaultViewContainerFrame;
import simpleManager3.container.ViewContainerFrame;
import simpleManager3.manager.DefaultViewManager;
import simpleManager3.manager.ViewManager;
import simpleManager3.perspective.DefaultPerspective;






/**
 * This is a default implementation of an Application which has
 * a <code>ViewControllerDispatcher</code> as well as a
 * <code>ViewManager</code>.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */

//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/core/AbstractApplication.java
public abstract class AbstractApplication implements Application
{
	private static final Logger logger = LoggerFactory.getLogger(AbstractApplication.class);
	
	private Locale						locale;
	
	private String 						name;
	private ViewContainerFrame			rootView;
	private ViewManager 				viewManager;
	public AbstractApplication(){
		logger.info("constructor_0");
		this.viewManager 			= new DefaultViewManager(this,new DefaultPerspective());
		
	}
	
	/**
	 * @param name
	 * @param mainView
	 */
	public AbstractApplication(String name,ViewContainerFrame mainView){
		logger.info("constructor_1");
		this.setName(name);
		this.setRootView(mainView);
	}
	/**
	 * This constructor sets the default root view
	 * 
	 * @param mainView
	 */
	public AbstractApplication(ViewContainerFrame mainView){
		logger.info("constructor_2");
		this.viewManager 			= new DefaultViewManager(this,new DefaultPerspective());
		this.setRootView(mainView);
	}
	

	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#close()
	 */
	public void close() {
		
		logger.info("Application_closing!");
		this.fireClose();
	}
	public void fireClose() {
		
		
		ViewContainerFrame viewContainerFrame = this.getViewManager().getRootView();
		viewContainerFrame.getFrame().dispose();
		}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#fireClose(org.viewaframework.core.ApplicationEvent)
	 */
	
	@SuppressWarnings("unchecked")
	public void prepare(){
		logger.info("Application preparing!");
		
		
	}
	public ViewContainerFrame getRootView() {
		return rootView;
	}
		/* (non-Javadoc)
		 * @see org.viewa.core.ApplicationBase#getName()
		 */
		public String getName() {
			return this.name;
		}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#prepareUI()
	 */
	public void prepareUI(DefaultViewContainerFrame f) {
		logger.info("Application preparing UI!");
		
	
		//this.setRootView();
	//	this.setRootView(ViewContainerFrame.class.cast(w.getView()));			
	}


	public void initUI() {
		
			logger.info("Application UI initializing!");
			ViewManager viewManager = this.getViewManager();
	
			Component 	view 		= viewManager.arrangeViews();
	
			view.setVisible(true);
	
		
	}
	public ViewManager getViewManager() {
		return viewManager;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
		Locale.setDefault(this.locale);
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param rootView
	 */
	public void setRootView(ViewContainerFrame rootView) {
				
		
			if (this.viewManager!=null){
				this.viewManager.setRootView(rootView);
				this.rootView = rootView;
			}else {
				logger.info("viewManager is null");
			}
		
		
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#isVisible()
	 */
	public boolean isVisible(){
		
		boolean result = true;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#setVisible(boolean)
	 */
	public void setVisible(boolean visible){
		JFrame frame = this.getRootView().getFrame();
		if (frame != null){
			if (visible){
				frame.setVisible(visible);
				frame.repaint();
			} else {
				frame.setVisible(visible);
			}
		}
	}
}