package de.hpi.bpmn;

import java.util.ArrayList;
import java.util.List;

import de.hpi.bpmn.serialization.BPMNSerialization;

/**
 * This is the parent class for Node and Edge. 
 * Why could edges have incoming and outgoing edges? Well, e.g. SequenceFlow can have an undirected association attached
 * 
 * @author gero.decker
 *
 */
public abstract class DiagramObject {

	protected String id;
	protected String resourceId;
	protected List<Edge> outgoingEdges;
	protected List<Edge> incomingEdges;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null)
			id = id.replace("#", "");
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public List<Edge> getIncomingEdges() {
		if (incomingEdges == null)
			incomingEdges = new ArrayList<Edge>();
		return incomingEdges;
	}

	public List<Edge> getOutgoingEdges() {
		if (outgoingEdges == null)
			outgoingEdges = new ArrayList<Edge>();
		return outgoingEdges;
	}
//	Added by Ahmed Awad
	public boolean equals(Object other) {
		if (resourceId != null && other instanceof DiagramObject)
			return this.resourceId.equals(((DiagramObject) other).getResourceId());
		else if (this == other)
			return true;
		else
			return false;
	}
	
	/**
	 * This method has to be implemented by all non-abstract child classes.
	 * An implementation of this method usually looks like:
	 * 
	 * public StringBuilder getSerialization(BPMNSerialization serialization) {
	 * 		return serialization.getSerializationForDiagramObject(this);
	 * }
	 * 
	 * @param a specific implementation for the interface BPMNSerialization
	 * @return the serialization for this class
	 */
	public abstract StringBuilder getSerialization(BPMNSerialization serialization);

}
