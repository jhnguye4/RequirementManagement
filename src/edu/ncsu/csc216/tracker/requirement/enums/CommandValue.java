/**
 * 
 */
package edu.ncsu.csc216.tracker.requirement.enums;

/**
 * Enum of CommandValues
 * @author jhnguye4
 *
 */ 
public enum CommandValue {  
  
	/** Accepted requirement: Submitted to Accepted*/  
	ACCEPT,
	/** Rejected requirement: 
	 * Submitted to Rejected
	 * Accepted to Rejected
	 * Working to Rejected
	 * Completed to Rejected
	 * Verified to Rejected 		
	 */
	REJECT,
	/** Revised Requirement: 
	 * Rejected to Revise
	 */
	REVISE,
	/** Assigned Requirement: 
	 * Accepted to Assign
	 * Complete to Assign
	 * Verified to Assign
	 */
	ASSIGN,
	/** Completed Requirement: 
	 * Working to Complete
	 */
	COMPLETE,  
	/** Passed Requirement: 
	 * Complete to Pass
	 */
	PASS,  
	/** Fail Requirement: 
	 * Complete to Fail
	 */
	FAIL

}
