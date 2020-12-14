/**
 * 
 */
package edu.ncsu.csc216.tracker.requirement;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;

/**
 * Command class is the class that is used to update the Requirement state. It uses the FSM design pattern to 
 * error check before it is used in Requirement class.
 * @author jhnguye4
 *
 */
public class Command {
	/**
 	* field for summary 
 	*/
	private String summary;
	/**
 	* field for acceptanceTestId 
 	*/
	private String acceptanceTestId;
	/**
 	* field for developerId
 	*/
	private String developerId;
	/**
 	* field for estiamte 
 	*/
	private String estimate;
	/**
 	* field for priority
 	*/
	private int priority;
	/**
 	* field for CommandValue 
 	*/
	private CommandValue c;
	/**
 	* field for Rejection
 	*/
	private Rejection resolutionReason;
	/**
 	* Constant for priority max
 	*/
	private static final int PRIORITY_MAX = 3; 
	/**
 	* Constructor for Command object that has seven parameters that will have a value or null depending on which RequirementState
 	* it is in and what information is needed to update the Requirement.
 	* @param command is the Command object
 	* @param summary String of the summary for the requirement, shoulder never be empty or null
 	* @param acceptanceTestId String of the acceptanceTestId for the requirement, shoulder never be empty or null
 	* @param priority int of the priority for the Requirement, will be 0 in submitted and rejected state but 1-3 other states
 	* @param estimate String that is null in submitted state but is edited in accepted state
 	* @param developerId String of developer that is set in the working state
 	* @param rejectionReason Rejection object of the Requirement when in Rejected state.
 	*/
	public Command(CommandValue command, String summary, String acceptanceTestId, int priority, String estimate, 
			String developerId, Rejection rejectionReason) throws IllegalArgumentException{
		if (command == null) {
			throw new IllegalArgumentException();
		}
		switch(command) {
		    // Accepted requirement: Submitted -> Accepted 
			case ACCEPT:
				if(estimate == null || estimate.equals("") || priority < 1 || priority > PRIORITY_MAX) {
					throw new IllegalArgumentException();
				}
				c = command;
				this.estimate = estimate;
				this.priority = priority;
				
				break;
			/* Rejected requirement: 
			* Submitted -> Rejected
			* Accepted -> Rejected
			* Working -> Rejected
			* Completed -> Rejected
			* Verified -> Rejected 		
			*/	
			case REJECT:
				if (rejectionReason == null) {
					throw new IllegalArgumentException();
				}
				c = command;
				resolutionReason = rejectionReason;
				
				break;
			/* Revised Requirement: 
			* Rejected -> Revise
			*/	
			case REVISE:
				
			if (summary == null || summary.contentEquals("") || acceptanceTestId == null || 
				acceptanceTestId.contentEquals("")) {
					throw new IllegalArgumentException();
				}
				c = command;
				this.summary = summary;
				this.acceptanceTestId = acceptanceTestId;
				
				break;
			/* Assigned Requirement: 
			* Accepted -> Assign
			* Complete -> Assign
			* Verified -> Assign
			*/	
			case ASSIGN:
				if(developerId == null || developerId.equals("")) {
					throw new IllegalArgumentException();
				}
				c = command;
				this.estimate = estimate;
				this.priority = priority;
				this.summary = summary;
				this.acceptanceTestId = acceptanceTestId;
				this.developerId = developerId;
				
				break;
			/* Completed Requirement: 
			* Working -> Complete
			*/	
			case COMPLETE:
				c = command;
				this.estimate = estimate;
				this.priority = priority;
				this.summary = summary;
				this.acceptanceTestId = acceptanceTestId;
				this.developerId = developerId;
				break;
			/* Passed Requirement: 
			* Complete -> Pass
			*/	
			case PASS:
				c = command;
				this.estimate = estimate;
				this.priority = priority;
				this.summary = summary;
				this.acceptanceTestId = acceptanceTestId;
				this.developerId = developerId;
				break;
			/** Fail Requirement: 
			* Complete -> Fail
			*/
			case FAIL:
				c = command;
				this.estimate = estimate;
				this.priority = priority;
				this.summary = summary;
				this.acceptanceTestId = acceptanceTestId;
				this.developerId = developerId;
				break;
			default:	
		}
	}

	/**
	 * Returns the summary that will be used in Requirement
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Returns the AcceptanceTestId that will be used in Requirement
	 * @return the acceptanceTestId
	 */
	public String getAcceptanceTestId() {
		return acceptanceTestId;
	}

	/**
	 * Returns the Command object that is used to update requirement
	 * @return the command
	 */
	public CommandValue getCommand() {
		return c;
	}

	/**
	 * Returns the estimate that is used in the Requirement. It will be used in the accepted state
	 * @return the estimate
	 */
	public String getEstimate() {
		return estimate;
	}

	/**
	 * Returns the priority that will be used in requirement and updated in the accepted state
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Returns the developer id that is used in requirement and updated when it goes to working state
	 * @return the developerId
	 */
	public String getDeveloperId() {
		return developerId;
	}

	/**
	 * Returns th Rejection object which will be used in Requirement. It will be used in rejected state where it cannot be null.
	 * @return the rejectionReason
	 */
	public Rejection getRejectionReason() {
		return resolutionReason;
	}
}
