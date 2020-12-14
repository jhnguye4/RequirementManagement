/**
 * 
 */
package edu.ncsu.csc216.tracker.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;


/**
 * Tests the RequirementTrackerModel Class
 * @author jhnguye4
 *
 */
public class RequirementsTrackerModelTest {
	/** RegistrationManager object */
	private RequirementsTrackerModel manager;

	/**
	 * Instantiates the RequirementTrackerModel
	 *@throws Exception if RequirementTrackerModel cant be instantiated.
	 */
	@Before
	public void setUp() throws Exception {
		manager = RequirementsTrackerModel.getInstance();
		
	}



	/**
	 * Test method for save requirements to file.
	 */
	@Test
	public void testSaveRequirementsToFile() {
		manager.createNewRequirementsList();
		manager.loadRequirementsFromFile("test-files/req1.xml");
		manager.saveRequirementsToFile("test-files/exp_req1.xml");
		manager.addRequirement("summary", "acceptanceTestId");
		assertEquals("summary", manager.getRequirementById(33).getSummary());
		assertNull(manager.getRequirementById(33).getRejectionReason());
		manager.saveRequirementsToFile("test-files/exp_req1.xml");

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.model.RequirementsTrackerModel#createNewRequirementsList()}.
	 */
	@Test
	public void testCreateNewRequirementsList() {
		manager.createNewRequirementsList();
		assertEquals(0, manager.getRequirementListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.model.RequirementsTrackerModel#loadRequirementsFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadRequirementsFromFile() {
		manager.loadRequirementsFromFile("test-files/req1.xml");
		assertEquals(6, manager.getRequirementListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.model.RequirementsTrackerModel#deleteRequirementById(int)}.
	 */
	@Test
	public void testDeleteRequirementById() {
		manager.createNewRequirementsList();
		manager.loadRequirementsFromFile("test-files/req1.xml");
		assertEquals(6, manager.getRequirementListAsArray().length);
		manager.deleteRequirementById(23);
		assertEquals(5, manager.getRequirementListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.model.RequirementsTrackerModel#getRequirementById(int)}.
	 */
	@Test
	public void testGetRequirementById() {
		manager.createNewRequirementsList();
		manager.loadRequirementsFromFile("test-files/req1.xml");
		manager.getRequirementById(1);
		assertEquals("SubmittedToAccepted", manager.getRequirementById(1).getAcceptanceTestId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.model.RequirementsTrackerModel#getRequirementListAsArray()}.
	 */
	@Test
	public void testGetRequirementListAsArray() {
		manager.createNewRequirementsList();
		manager.loadRequirementsFromFile("test-files/req1.xml");
		manager.getRequirementListAsArray();
		assertEquals(6, manager.getRequirementListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.model.RequirementsTrackerModel#executeCommand(int, edu.ncsu.csc216.tracker.requirement.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		manager.createNewRequirementsList();
		manager.loadRequirementsFromFile("test-files/req1.xml");
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", null, null);
		assertEquals("Submitted" , manager.getRequirementById(0).getXMLReq().getState());
		manager.executeCommand(0, c);
		assertEquals(1, manager.getRequirementById(1).getPriority());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.model.RequirementsTrackerModel#addRequirement(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddRequirement() {
		manager.createNewRequirementsList();
		manager.loadRequirementsFromFile("test-files/req1.xml");
		manager.addRequirement("summary1", "acceptanceTestId1");
		assertEquals(0, manager.getRequirementById(33).getPriority());
		assertEquals(0, manager.getRequirementById(23).getPriority());
		assertEquals(7, manager.getRequirementListAsArray().length);
	}

}
