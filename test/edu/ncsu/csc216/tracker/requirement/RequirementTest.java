/**
 * 
 */
package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;
import edu.ncsu.csc216.tracker.xml.Req;

/**
 * Tests the Requirement class
 * @author jhnguye4
 *
 */
public class RequirementTest {
	/**
	 * field for Requirement
	 */
	private Requirement r;
	private static final  int NO_PRIORITY = 0;

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#Requirement(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRequirementStringString() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals("Summary", r.getSummary());
		assertNull(r.getEstimate());
		assertNull(r.getDeveloper());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(0, r.getPriority());
		assertNull(r.getRejectionReason());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#Requirement(edu.ncsu.csc216.tracker.xml.Req)}.
	 */
	@Test
	public void testRequirementReq() {
		Req re = new Req();
		re.setState("Accepted");
		re.setSummary("Brand New Summary");
		re.setTest("New test");
		re.setId(10);
		re.setEstimate("1 hour");
		re.setPriority(1);
		re.setRejection(null);
		Requirement req = new Requirement(re);
		assertEquals(10, req.getRequirementId());
		assertEquals("Brand New Summary", req.getSummary());
		assertEquals("1 hour", req.getEstimate());
		assertNull(req.getDeveloper());
		assertEquals("New test", req.getAcceptanceTestId());
		assertEquals(1, req.getPriority());
		assertNull(req.getRejectionReason());
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getRequirementId()}.
	 */
	@Test
	public void testGetRequirementId() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals(11, r.getRequirementId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getState()}.
	 */
	@Test
	public void testGetState() {
		r = new Requirement( "Summary", "AcceptanceId");
		r.getState();
		assertEquals("Summary", r.getSummary());
		assertNull(r.getEstimate());
		assertNull(r.getDeveloper());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(0, r.getPriority());
		assertNull(r.getRejectionReason());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getPriority()}.
	 */
	@Test
	public void testGetPriority() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals(0, r.getPriority());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getEstimate()}.
	 */
	@Test
	public void testGetEstimate() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertNull(r.getEstimate());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getRejectionReason()}.
	 */
	@Test
	public void testGetRejectionReason() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertNull(r.getRejectionReason());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getRejectionReasonString()}.
	 */
	@Test
	public void testGetRejectionReasonString() {
		r = new Requirement( "Summary", "AcceptanceId");
		Command c = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, 
				null, null, Rejection.DUPLICATE);
		r.update(c);
		assertEquals("Duplicate", r.getRejectionReasonString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getDeveloper()}.
	 */
	@Test
	public void testGetDeveloper() {
		r = new Requirement( "Summary", "AcceptanceId");
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", null, null);
		r.update(c);
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		assertEquals("jhnguye4", r.getDeveloper());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getSummary()}.
	 */
	@Test
	public void testGetSummary() {
		r = new Requirement( "Summary 1", "AcceptanceId");
		assertEquals("Summary 1", r.getSummary());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getAcceptanceTestId()}.
	 */
	@Test
	public void testGetAcceptanceTestId() {
		r = new Requirement( "Summary", "AcceptanceId");
		Command c5 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, null, null, Rejection.DUPLICATE);
		r.update(c5);
		Command c = new Command(CommandValue.REVISE, "New Summary", "New AcceptanceIDTest", 
				NO_PRIORITY, null, null, null);
		r.update(c);
		assertEquals("New AcceptanceIDTest", r.getAcceptanceTestId());
	}

//	/**
//	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#setAcceptanceTestId(java.lang.String)}.
//	 */
//	@Test
//	public void testSetAcceptanceTestId() {
//		Requirement rr = new Requirement( "Summary", "AcceptanceId");
//		Command c5 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, null, null, Rejection.DUPLICATE);
//		r.update(c5);
//		Command c6 = new Command(CommandValue.REVISE, "Summary15", "AcceptanceId3", NO_PRIORITY, null, null, null);
//		r.update(c6);
//		assertEquals("AcceptanceId3", rr.getAcceptanceTestId());
//		r.setAcceptanceTestId("Brand new AcceptanceTestId");
//		assertEquals("Brand new AcceptanceTestId", rr.getAcceptanceTestId());
//	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#setDeveloper(java.lang.String)}.
	 */
	@Test
	public void testSetDeveloper() {
		r = new Requirement( "Summary", "AcceptanceId");
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", null, null);
		r.update(c);
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		r.setDeveloper("New developer");
		assertEquals("New developer", r.getDeveloper());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#update(edu.ncsu.csc216.tracker.requirement.Command)}.
	 */
	@Test
	public void testUpdate() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertNull(r.getEstimate());
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", 
				null, null);
		r.update(c);
		assertEquals(1, r.getPriority());
		assertNull(r.getDeveloper());
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		assertEquals("jhnguye4", r.getDeveloper());
		Command c2 = new Command(CommandValue.COMPLETE, null, null, NO_PRIORITY, null, null, null);
		r.update(c2);
		Command c3 = new Command(CommandValue.PASS, null, null, NO_PRIORITY, null, null, null);
		r.update(c3);
		Command c4 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "New Developer", null);
		r.update(c4);
		assertEquals("New Developer", r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertEquals("1 hour", r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(1, r.getPriority());
		assertNull(r.getRejectionReason());
		
		Command c5 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, null, null, Rejection.DUPLICATE);
		r.update(c5);
		assertNull(r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertNull(r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(0, r.getPriority());
		assertEquals(Rejection.DUPLICATE, r.getRejectionReason());
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#update(edu.ncsu.csc216.tracker.requirement.Command)}.
	 */
	@Test
	public void testUpdateSubmittedNull() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertNull(r.getEstimate());
		Command c5 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, null, null, Rejection.DUPLICATE);
		r.update(c5);
		try {
			Command c6 = new Command(CommandValue.REVISE, "", "AcceptanceId3", NO_PRIORITY, null, null, null);
			r.update(c6);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid summary");
		}
		assertEquals(0, r.getPriority());
		assertNull(r.getDeveloper());

	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#update(edu.ncsu.csc216.tracker.requirement.Command)}.
	 */
	@Test
	public void testUpdateToFail() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertNull(r.getEstimate());
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", 
				null, null);
		r.update(c);
		assertEquals(1, r.getPriority());
		assertNull(r.getDeveloper());
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		assertEquals("jhnguye4", r.getDeveloper());
		Command c2 = new Command(CommandValue.COMPLETE, null, null, NO_PRIORITY, null, null, null);
		r.update(c2);
		Command c3 = new Command(CommandValue.FAIL, null, null, NO_PRIORITY, null, null, null);
		r.update(c3);
		assertEquals("jhnguye4", r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertEquals("1 hour", r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(1, r.getPriority());
		assertNull(r.getRejectionReason());
		
		Command c5 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, null, null, Rejection.DUPLICATE);
		r.update(c5);
		assertNull(r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertNull(r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(0, r.getPriority());
		assertEquals(Rejection.DUPLICATE, r.getRejectionReason());
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#update(edu.ncsu.csc216.tracker.requirement.Command)}.
	 */
	@Test
	public void testUpdateToPass() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertNull(r.getEstimate());
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", 
				null, null);
		r.update(c);
		assertEquals(1, r.getPriority());
		assertNull(r.getDeveloper());
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		assertEquals("jhnguye4", r.getDeveloper());
		Command c2 = new Command(CommandValue.COMPLETE, null, null, NO_PRIORITY, null, null, null);
		r.update(c2);
		Command c3 = new Command(CommandValue.PASS, null, null, NO_PRIORITY, null, null, null);
		r.update(c3);
		assertEquals("jhnguye4", r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertEquals("1 hour", r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(1, r.getPriority());
		assertNull(r.getRejectionReason());
		
		Command c5 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, null, null, Rejection.DUPLICATE);
		r.update(c5);
		assertNull(r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertNull(r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(0, r.getPriority());
		assertEquals(Rejection.DUPLICATE, r.getRejectionReason());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#update(edu.ncsu.csc216.tracker.requirement.Command)}.
	 */
	@Test
	public void testUpdateToCompletedToAssign() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertNull(r.getEstimate());
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", 
				null, null);
		r.update(c);
		assertEquals(1, r.getPriority());
		assertNull(r.getDeveloper());
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		assertEquals("jhnguye4", r.getDeveloper());
		Command c2 = new Command(CommandValue.COMPLETE, null, null, NO_PRIORITY, null, null, null);
		r.update(c2);
		Command c3 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "bob", null);
		r.update(c3);
		assertEquals("bob", r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertEquals("1 hour", r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(1, r.getPriority());
		assertNull(r.getRejectionReason());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#update(edu.ncsu.csc216.tracker.requirement.Command)}.
	 */
	@Test
	public void testUpdateToCompletedToReject() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertNull(r.getEstimate());
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", 
				null, null);
		r.update(c);
		assertEquals(1, r.getPriority());
		assertNull(r.getDeveloper());
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		assertEquals("jhnguye4", r.getDeveloper());
		Command c2 = new Command(CommandValue.COMPLETE, null, null, NO_PRIORITY, null, null, null);
		r.update(c2);
		Command c3 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, null, null, Rejection.OUT_OF_SCOPE );
		r.update(c3);
		assertNull(r.getDeveloper());
		assertEquals("Summary", r.getSummary());
		assertNull(r.getEstimate());
		assertEquals("AcceptanceId", r.getAcceptanceTestId());
		assertEquals(0, r.getPriority());
		assertEquals(Rejection.OUT_OF_SCOPE, r.getRejectionReason());
	}
	
	

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getXMLReq()}.
	 */
	@Test
	public void testGetXMLReqWorking() {
		r = new Requirement( "Summary", "AcceptanceId");
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", 
				null, null);
		r.update(c);
		Command c1 = new Command(CommandValue.ASSIGN, null, null, NO_PRIORITY, null, "jhnguye4", null);
		r.update(c1);
		assertEquals("jhnguye4", r.getXMLReq().getDeveloper());
		assertEquals("Working", r.getXMLReq().getState());
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getXMLReq()}.
	 */
	@Test
	public void testGetXMLReqSubmitted() {
		r = new Requirement( "Summary", "AcceptanceId");
		assertNull(r.getXMLReq().getDeveloper());
		assertEquals("Submitted", r.getXMLReq().getState());
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#getXMLReq()}.
	 */
	@Test
	public void testGetXMLReqAccepted() {
		r = new Requirement( "Summary", "AcceptanceId");
		Command c = new Command(CommandValue.ACCEPT, null, null, 1, "1 hour", 
				null, null);
		r.update(c);
		assertEquals(1, r.getXMLReq().getPriority());
		assertEquals("Accepted", r.getXMLReq().getState());
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Requirement#setCounter(int)}.
	 */
	@Test
	public void testSetCounter() {
		Requirement.setCounter(10);
		r = new Requirement( "Summary", "AcceptanceId");
		assertEquals(10, r.getRequirementId());
	}

}
