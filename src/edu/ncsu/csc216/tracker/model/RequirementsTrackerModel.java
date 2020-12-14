package edu.ncsu.csc216.tracker.model;

import edu.ncsu.csc216.tracker.requirement.Command;

import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.xml.*;

/**
 * This class is used to maintain the RequirementList and handles the activity around loading, saving, creating new RequirementLists.
 * 
 * @author jhnguye4
 */
public class RequirementsTrackerModel {
	/**
	 * field for RequirementList
	 */
	private RequirementsList reqList;
	/**
	 * field for RequirementTrackerModel
	 */
	private static RequirementsTrackerModel instance;
	
	/**
	 * Constructor for RequirementTracker model but it is private and cannot be instantiated outside of this class
	 */
	private RequirementsTrackerModel() { } 
	/**
	 * This is the method that will be used to handle the activity of loading, saving, and creating RequirementLists.
	 * @return instance of RequirementTrackerModel
	 */
	public static RequirementsTrackerModel getInstance() {
		  if (instance == null) {
			instance = new RequirementsTrackerModel();
		}
		return instance;
	}
	/**
	 * This method saves the RequirementList to a file.
	 * @param fileName is the output file name 
	 * @throws IllegalArgumentException if file cannot be saved
	 */
	public void saveRequirementsToFile(String fileName) {
		try {
			RequirementsWriter w = new RequirementsWriter(fileName);
			for (int i = 0; i < reqList.getRequirements().size(); i++) {
				Requirement r = reqList.getRequirements().get(i);
				w.addReq(r.getXMLReq());
				w.marshal();
			}
		} catch (RequirementIOException e) {
			throw new IllegalArgumentException();
		}
		
	}
	/**
	 * Creates a new RequirementList and sets the counter to 0
	 */
	public void createNewRequirementsList() {
		RequirementsList newRequirements = new RequirementsList();	
		reqList = newRequirements;
		Requirement.setCounter(0);
	}

	/**
	 * Loads an xml file into a RequirementList. First creates and empty RequirementList then calls upon
	 * RequirementReader to store the Reqs.
	 * @param fileName is the file being read and its values stored in a RequirementList
	 */
	public void loadRequirementsFromFile(String fileName) {
		try {
			createNewRequirementsList();
			RequirementsReader r = new RequirementsReader(fileName);
			reqList.addXMLReqs(r.getReqs());
		} catch (RequirementIOException e){
			throw new IllegalArgumentException();
		}
		
		
	}
	/**
	 * This method deletes a Requirement depending on the id being passed through
	 * @param reqId is RequirementId of the Requirement and if they match then it will be deleted
	 */
	public void deleteRequirementById(int reqId) {
		reqList.deleteRequirementById(reqId);
		
	}
	/**
	 * Returns a Requirement depending on if the RequirementId exists in the RequirementList
	 * @param reqId is the Id that will searched for in the RequirementList
	 * @return Requirement that is returned if ids match
	 */
	public Requirement getRequirementById(int reqId) {	
		return reqList.getRequirementById(reqId);
	}
	/**
	 * Creates a 2D array of all the requirements in the RequirementList. This methods purpose is for the GUI
	 * @return Object which is a 2D array
	 */
	public Object[][] getRequirementListAsArray() {
		if (reqList == null) {
			createNewRequirementsList();
		}
		int row = reqList.getRequirements().size();
		Object [][] list = new Object[row][3];
		if(row > 0) {
			for (int i = 0; i < reqList.getRequirements().size(); i++) {
				Requirement l = reqList.getRequirements().get(i);
				list[i][0] = l.getRequirementId();
				list[i][1] = l.getState().getStateName();
				list[i][2] = l.getSummary();
			}
		}
		return list;
	}
	/**
	 * This method is used to update the state of the Requirement. The id is used to locate which Requirement to be updated
	 * and the command param has the information that it will be updated to.
	 * @param reqId is the id of the user that you want to update
	 * @param c command is the Command object that has all the information needed to update the Requirement
	 */
	public void executeCommand(int reqId, Command c) {
		reqList.executeCommand(reqId, c);
		
	}
	/**
	 * This method is used to create a new Requirement and add it to RequirementList
	 * @param summary is the summary that the new Requirement will be set with
	 * @param acceptanceTestId is the acceptanceTest that will be set in the Requirement
	 */
	public void addRequirement(String summary, String acceptanceTestId) {
		reqList.addRequirement(summary, acceptanceTestId);
		
	}



}
