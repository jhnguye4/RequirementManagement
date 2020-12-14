/**
 * 
 */
package edu.ncsu.csc216.tracker.model;



import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.xml.Req;

/**
 * This class is the RequirementList class and is created after a Requirement and state pattern are implemented.
 * This class maintains a list of Requirements. It will be an ArrayList.
 * @author jhnguye4
 *
 */
public class RequirementsList {
	/**
	 * field for ArrayList of Requirements
	 */
	private ArrayList<Requirement> reqs;
	/**
	 * Constructor for RequirementsList that instantiates and creates an empty ArrayList
	 */
	public RequirementsList() {
		reqs = new ArrayList<Requirement>();
		Requirement.setCounter(0);
	}
	/**
	 * This method creates a new Requirement and adds it to the RequirementList array
	 * @param summary is the summary that is set in the Requirement
	 * @param acceptanceTestId is the String that is set in the Requirement for acceptanceTestId
	 * @return int of the RequirementId.
	 */
	public int addRequirement(String summary, String acceptanceTestId) {
		Requirement r = new Requirement(summary, acceptanceTestId);
		reqs.add(r);
		return r.getRequirementId();
		
	}
	
	/**
	 * This method takes a list of Reqs and creates them into Requirements by calling on the constructor
	 * in Requirement that takes in a Req. After a Requirement is made, it will be placed in the RequirementList
	 * @param list is the List of Reqs that will be converted to Requirements
	 */
	public void addXMLReqs(List<Req> list) { 
		Requirement r;
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			r = new Requirement(list.get(i));
			reqs.add(r);
			if (r.getRequirementId() > count) {
				Requirement.setCounter(r.getRequirementId() + 1 );
			}
		}
	}
	
	/**
	 * This class returns the ArrayList of Requirements
	 * @return ArrayList of Requirements
	 */
	public ArrayList<Requirement> getRequirements() {
		return reqs;
	}
	/**
	 * This class returns a Requirement after it parses through the RequirementList to see if the reqId passed through
	 * matches a reqId in the RequirementList.
	 * @param reqId is the Requirement that the user is looking for
	 * @return the Requirement that matches the reqId, if no id matches then it will return null
	 */
	public Requirement getRequirementById(int reqId) {
		if (reqId < 0) {
			return null;
		}
		for (int i = 0; i < reqs.size(); i++) {
			Requirement r = reqs.get(i);
			if (reqId == r.getRequirementId()) {
				return r;
			}
		}
		return null;
	}
	/**
	 * This method will update the Requirement that matches the id passed through. The command param will have the information
	 * that will allow the user to update.
	 * @param reqId is the id that you are looking for when parsing through the RequirementList
	 * @param c is the Command object that will have the information to update the Requirement
	 */
	public void executeCommand(int reqId, Command c) {
		for (int i = 0; i < reqs.size(); i++) {
			Requirement r = reqs.get(i);
			if (reqId == r.getRequirementId()) {
				r.update(c);
				break;
			}
		}
	}
	/**
	 * This method will remove a Requirement if its id matches an id in the RequirementList
	 * @param reqId is the Requirement id that that the user is looking for
	 */
	public void deleteRequirementById(int reqId) {
		for (int i = 0; i < reqs.size(); i++) {
			Requirement r = reqs.get(i);
			if (reqId == r.getRequirementId()) {
				reqs.remove(i);
			}
		}
		
	}
}
