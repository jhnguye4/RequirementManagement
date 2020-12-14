/**
 * 
 */
package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;

/**
 * Tests the command class
 * @author jhnguye4
 *
 */
public class CommandTest {
	private static final  int NO_PRIORITY = 0;
	private Command c6;

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#Command(edu.ncsu.csc216.tracker.requirement.enums.CommandValue, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, edu.ncsu.csc216.tracker.requirement.enums.Rejection)}.
	 */
	@Test
	public void testCommand() {
		Command c = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, 
				null, null, Rejection.DUPLICATE);
		assertEquals(Rejection.DUPLICATE, c.getRejectionReason());
		
		Command c1 = new Command(CommandValue.ACCEPT, "Summary", "AcceptanceId", 3, 
				"1 hour", null, null);
		assertEquals("1 hour", c1.getEstimate());
		
		Command c2 = new Command(CommandValue.REVISE, "Summary", "AcceptanceId", NO_PRIORITY, 
				null, null, null);
		assertEquals("Summary", c2.getSummary());
		
		Command c3 = new Command(CommandValue.ASSIGN, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals(3, c3.getPriority());
		
		Command c4 = new Command(CommandValue.PASS, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals("jhnguye4", c4.getDeveloperId());
		
		Command c5 = new Command(CommandValue.FAIL, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertNull(c5.getRejectionReason());
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#Command(edu.ncsu.csc216.tracker.requirement.enums.CommandValue, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, edu.ncsu.csc216.tracker.requirement.enums.Rejection)}.
	 */
	@Test
	public void testInvalidRejectionCommand() {
		try {
			c6 = new Command(CommandValue.REJECT, null, null, NO_PRIORITY, 
					null, null, null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Null rejection reason");
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#Command(edu.ncsu.csc216.tracker.requirement.enums.CommandValue, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, edu.ncsu.csc216.tracker.requirement.enums.Rejection)}.
	 */
	@Test
	public void testInvalidAcceptCommand() {
		try {
			c6 = new Command(CommandValue.ACCEPT, "Summary", "AcceptanceId", NO_PRIORITY, 
					"1 hour", null, null);
			
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Command");
		}
		try {
			c6 = new Command(CommandValue.ACCEPT, "Summary", "AcceptanceId", 1, "", null, null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Command");
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#Command(edu.ncsu.csc216.tracker.requirement.enums.CommandValue, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, edu.ncsu.csc216.tracker.requirement.enums.Rejection)}.
	 */
	@Test
	public void testInvalidAssignCommand() {
		try {
			c6 = new Command(CommandValue.ASSIGN, "Summary", "AcceptanceId", 1, 
					"1 hour", null, null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Command");
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#Command(edu.ncsu.csc216.tracker.requirement.enums.CommandValue, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, edu.ncsu.csc216.tracker.requirement.enums.Rejection)}.
	 */
	@Test
	public void testInvalidReviseCommand() {
		try {
			c6 = new Command(CommandValue.REVISE, "Summary", "", NO_PRIORITY, 
					null, null, null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Command");
		}
		try {
			c6 = new Command(CommandValue.REVISE, "Summary", null, NO_PRIORITY, 
					null, null, null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Command");
		}
		try {
			c6 = new Command(CommandValue.REVISE, "", "AcceptanceId", NO_PRIORITY, 
					null, null, null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Command");
		}
		try {
			c6 = new Command(CommandValue.REVISE, null, "AcceptanceId", NO_PRIORITY, 
					null, null, null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Command");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#getSummary()}.
	 */
	@Test
	public void testGetSummary() {
		Command c = new Command(CommandValue.COMPLETE, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals("Summary", c.getSummary());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#getAcceptanceTestId()}.
	 */
	@Test
	public void testGetAcceptanceTestId() {
		Command c = new Command(CommandValue.COMPLETE, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals("AcceptanceId", c.getAcceptanceTestId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#getCommand()}.
	 */
	@Test
	public void testGetCommand() {
		Command c = new Command(CommandValue.COMPLETE, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals(CommandValue.COMPLETE, c.getCommand());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#getEstimate()}.
	 */
	@Test
	public void testGetEstimate() {
		Command c = new Command(CommandValue.COMPLETE, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals("1 hour", c.getEstimate());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#getPriority()}.
	 */
	@Test
	public void testGetPriority() {
		Command c5 = new Command(CommandValue.COMPLETE, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals(3, c5.getPriority());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#getDeveloperId()}.
	 */
	@Test
	public void testGetDeveloperId() {
		Command c = new Command(CommandValue.COMPLETE, "Summary", "AcceptanceId", 3, 
				"1 hour", "jhnguye4", null);
		assertEquals("jhnguye4", c.getDeveloperId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.tracker.requirement.Command#getRejectionReason()}.
	 */
	@Test
	public void testGetRejectionReason() {
		c6 = new Command(CommandValue.REJECT, "Summary", "AcceptanceId", NO_PRIORITY, 
				null, null, Rejection.INAPPROPRIATE);
		assertEquals(Rejection.INAPPROPRIATE, c6.getRejectionReason());
	}

}
