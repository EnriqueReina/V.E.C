package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import aiss.api.resources.CollaboratorResource;
import aiss.api.resources.EventResource;
import aiss.api.resources.FileResource;
import aiss.api.resources.MessageResource;
import aiss.api.resources.ProjectResource;

public class VECApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public VECApplication() {
		singletons.add(CollaboratorResource.getInstance());
		singletons.add(FileResource.getInstance());
		singletons.add(ProjectResource.getInstance());
		singletons.add(MessageResource.getInstance());
		singletons.add(EventResource.getInstance());
	}
	public Set<Object> getSingletons() {
		return singletons;
	}
	public Set<Class<?>> getClasses() {
		return classes;
	}
}
