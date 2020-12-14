package edu.ncsu.csc216.tracker.requirement;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;
import edu.ncsu.csc216.tracker.xml.Req;

/**
 * Requirement is the context class which has six inner classes that implement the two methods
 * from the interface RequirementState. This class also has setters/getters for each of the 
 * parameters that the Requirement requires such as id, summary, estimate, developer, acceptanceTestId
 * priority, rejection reason and state.
 *  
 * @author jhnguye4
 *
 */
public class Requirement {
	/** Requirement id */
	private Integer requirementId;
	/** Requirement summary */
	private String summary;
	/** Requirement estimate */
	private String estimate;
	/** Requirement developer */
	private String developer;
	/** Requirement acceptanceTestId */
	private String acceptanceTestId;
	/** Requirement priority */
	private int priority;

	/** Requirement rejection reason */
	private Rejection rejectionReason;
	/** Current state of requirement */
	private RequirementState state;
	/** Requirement in submitted state */
	private RequirementState submittedState = new Submitted();
	/** Requirement in accepted state */
	private RequirementState acceptedState = new Accepted();
	/** Requirement in working state */
	private RequirementState workingState = new Working();
	/** Requirement in completed state */
	private RequirementState completedState = new Completed();
	/** Requirement in verifired state */
	private RequirementState verifiedState = new Verified();
	/** Requriement in rejected state */
	private RequirementState rejectedState = new Rejected();
	
	/** String for submitted */
	public static final String SUBMITTED_NAME = "Submitted";
	/** String for accepted */
	public static final String ACCEPTED_NAME = "Accepted";
	/** String for rejected */
	public static final String REJECTED_NAME = "Rejected";
	/** String for working */
	public static final String WORKING_NAME = "Working";
	/** String for completed */
	public static final String COMPLETED_NAME = "Completed";
	/** String for verified */
	public static final String VERIFIED_NAME = "Verified";
	/** String for duplicate */
	public static final String DUPLICATE_NAME = "Duplicate";
	/** String for infeasible */
	public static final String INFEASIBLE_NAME = "Infeasible";
	/** String for too large */
	public static final String TOO_LARGE_NAME = "Too Large";
	/** String for out of scope */
	public static final String OUT_OF_SCOPE_NAME = "Out of Scope";
	/** String for inappropriate */
	public static final String INAPPROPRIATE_NAME = "Inappropriate";
	
	/** Counter that will be used for Requirement id */
	private static int counter = 0;

	/**
	 * This is the constructor that is used when a new Requirement is added.
	 * The parameters that are set are summary and acceptanceTestid while the state
	 * is in the submitted state, and everything is else is set to 0 or null.
	 * 
	 * @param summary Is the summary of the requirement
	 * @param acceptanceTestId Is the Test id of the requirement
	 */
	public Requirement(String summary, String acceptanceTestId) {
		this.requirementId = counter;
		setState(SUBMITTED_NAME);
		if(summary == null || summary.equals("")) {
			throw new IllegalArgumentException();
		}
		this.summary = summary;
		estimate = null;
		setDeveloper(null);
		setAcceptanceTestId(acceptanceTestId);
		priority = 0;
		rejectionReason = null;
		incrementCounter();
	}
	
	/**
	 * This is the second constructor that is used when a Requirement is called upon to
	 * be edited. The constructor will call upon the Req opject and set the fields in 
	 * this class to values in Req object.
	 * 
	 * @param requirement Is a Req object that is used to get information to set into
	 * fields
	 */
	public Requirement(Req requirement) {
		if(requirement.getSummary() == null || requirement.getState() == null || requirement.getTest() == null || requirement.getSummary().equals("") || requirement.getState().equals("") || requirement.getTest().equals("")) {
			throw new IllegalArgumentException();
		}
		requirementId = requirement.getId();
		setState(requirement.getState());
		summary = requirement.getSummary();
		estimate = requirement.getEstimate();
		developer = requirement.getDeveloper();
		acceptanceTestId = requirement.getTest();
		priority = requirement.getPriority();
		setRejectionReason(requirement.getRejection());
	}
	/** This method is used to increase the counter that is used to set the id */
	private static void incrementCounter() {
		counter++;
	}

	/**
	 * Returns the id of the Requirement
	 * @return the requirementId which is an int
 	 */
	public int getRequirementId() {
		return requirementId;
	}

	/** 
	 * Gets the current state of the object.
	 *  @return A RequirementState which will tell which state the object is in
	 */
	public RequirementState getState() {
		return state;

	}
	/** 
	 * Method sets the state of the Requirement. Method is called in the constructor of Requirement where the state is being 
	 * passed in as a string and then placed into the correct state depending on what the string is passed through.
	 *  @throws IllegalArgumentException if state is null or an invalid string is passed in
	 */
	private void setState(String state) {
		if (state == null || !(state.equals(SUBMITTED_NAME) || state.equals(ACCEPTED_NAME) || state.equals(REJECTED_NAME) ||
				state.equals(WORKING_NAME) || state.equals(COMPLETED_NAME) || state.equals(VERIFIED_NAME))) {
			throw new IllegalArgumentException();
		}
		if(state.equals(SUBMITTED_NAME)) {
			this.state = submittedState;
		}
		if(state.equals(ACCEPTED_NAME)) {
			this.state = acceptedState;
		}
		if(state.equals(WORKING_NAME)) {
			this.state = workingState;
		}
		if(state.equals(COMPLETED_NAME)) {
			this.state = completedState;
		}
		if(state.equals(VERIFIED_NAME)) {
			this.state = verifiedState;
		}
		if(state.equals(REJECTED_NAME)) {
			this.state = rejectedState;
		}
	}
	
	/** 
	 * Returns the priority of the Requirement
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Returns the estimate of the Requirement
	 * @return the estimate
	 */
	public String getEstimate() {
		return estimate;
	}
	/**
	 * Returns the Rejection object of the Requirement
	 * @return the Rejection object
	 */
	public Rejection getRejectionReason() {
		return rejectionReason;
	}
	/**
	 * Returns the string of the Rejection. Depending on what Rejection object it is, it is converted to a string.
	 * This method is used in the getXML method where the Rejection is set as a string.
	 * @return a String of the Rejection
	 */
	public String getRejectionReasonString() {
		String reason = null;
		if(rejectionReason == Rejection.DUPLICATE) {
			reason = DUPLICATE_NAME;
		}
		if(rejectionReason == Rejection.INFEASIBLE) {
			reason = INFEASIBLE_NAME;
		}
		if(rejectionReason == Rejection.TOO_LARGE) {
			reason = TOO_LARGE_NAME;
		}
		if(rejectionReason == Rejection.OUT_OF_SCOPE) {
			reason = OUT_OF_SCOPE_NAME;
		}
		if(rejectionReason == Rejection.INAPPROPRIATE) {
			reason = INAPPROPRIATE_NAME;
		}
		return reason;
	}
	/**
	 * Sets the rejectionReason field to be a Rejection object depending on what string is passed.
	 * This method is used in the constructor where a Req is passed through
	 * @return nothing as it is used to jump out of method
	 */
	private void setRejectionReason(String reason) {
		if (reason == null || reason.equals("")) {
			rejectionReason = null;
			return;
		}
		if(reason.equals(DUPLICATE_NAME)) {
			rejectionReason = Rejection.valueOf("DUPLICATE");
			return;
		}
		if(reason.equals(INFEASIBLE_NAME)) {
			rejectionReason = Rejection.valueOf("INFEASIBLE");
			return;
		}
		if(reason.equals(TOO_LARGE_NAME)) {
			rejectionReason = Rejection.valueOf("TOO_LARGE");
			return;
		}
		if(reason.equals(OUT_OF_SCOPE_NAME)) {
			rejectionReason = Rejection.valueOf("OUT_OF_SCOPE");
			return;
		}
		if(reason.equals(INAPPROPRIATE_NAME)) {
			rejectionReason = Rejection.valueOf("INAPPROPRIATE");
			return;
		}
		else {
			throw new IllegalArgumentException("Invalid Rejection Reason");
		}
	}
	
	/**
	 * Returns the developer of the Requirement
	 * @return the developer
	 */
	public String getDeveloper() {
		return developer;
	}
	
	/**
	 * Returns the summary of the Requirement
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Returns the acceptanceTestId of the Requirement
	 * @return the acceptanceTestId
	 */
	public String getAcceptanceTestId() {
		return acceptanceTestId;
	}

	/**
	 * This method sets the acceptanceTestId and is used in the inner class of Submitted where the summary can be edited
	 * @param acceptanceTestId is a String that the acceptanceTestId is set to
	 * @throws IllegalArgumentException if the acceptanceTestId is null.
	 */
	public void setAcceptanceTestId(String acceptanceTestId) {
		if (acceptanceTestId == null) {
			throw new IllegalArgumentException();
		}
		this.acceptanceTestId = acceptanceTestId;
	}

	/** 
	 * This method sets the developer of the requirement and is used in the inner class of Working where it assigns a developer
	 * This method is used to error check to make sure the user does not input an invalid developer
	 * @param developer the developer to set
	 * @throws IllegalArgumentException if the developer is null in certain states
	 */
	public void setDeveloper(String developer) {
		if ((state.getStateName().equals(WORKING_NAME) || state.getStateName().equals(COMPLETED_NAME) || state.getStateName().equals(VERIFIED_NAME)) && (developer == null 
				|| developer.equals(""))) {
			throw new IllegalArgumentException();
		}
		this.developer = developer;
	}
	/** 
	 * This method incorporates the inner classes and uses the state pattern to update the current state of the Requirement.
	 * This method will also check if each state that it transitions to is valid and will throw an exception if an invalid error is 
	 * passed through.
	 * @param command will have parameters that will allow the state to be updated with those values
	 * @throws UnsupportedOperationException for invalid state transitions, for example a accepted state cannot go to submitted
	 */
	public void update(Command command) {
		if (state.getStateName().equals(REJECTED_NAME)) {
			if(command.getCommand() == CommandValue.REVISE) {
				state = new Submitted();
				state.updateState(command);
				return;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}	
		if (state.getStateName().equals(VERIFIED_NAME)) {
			if(command.getCommand() == CommandValue.ASSIGN) {
				state = workingState;
				state.updateState(command);
				return;
			}
			else if(command.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				state.updateState(command);
				return;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		if (state.getStateName().equals(COMPLETED_NAME)) {
			if(command.getCommand() == CommandValue.PASS) {
				state = verifiedState;
				state.updateState(command);
				return;
			}
			else if(command.getCommand() == CommandValue.FAIL) {
				state = workingState;
				return;
			}
			else if(command.getCommand() == CommandValue.ASSIGN) {
				state = workingState;
				state.updateState(command);
				return;
			}
			else if(command.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				state.updateState(command);
				return;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		if (state.getStateName().equals(WORKING_NAME)) {
			if(command.getCommand() == CommandValue.COMPLETE) {
				state = completedState;
				state.updateState(command);
				return;
			}
			else if(command.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				state.updateState(command);
				return;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		if (state.getStateName().equals(ACCEPTED_NAME)) {
			if(command.getCommand() == CommandValue.ASSIGN) {
				state = workingState;
				state.updateState(command);
				return;
			}
			else if(command.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				state.updateState(command);
				return;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		if (state.getStateName().equals(SUBMITTED_NAME)) {
			if(command.getCommand() == CommandValue.ACCEPT) {
				state = acceptedState;
				state.updateState(command);
				return;
			}
			else if(command.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				state.updateState(command);
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		

	}
	/** 
	 * This method takes all the fields that make up a requirement and creates them in a Req object so that 
	 * it can be saved to a file. 
	 * @return a Req object 
	 */
	public Req getXMLReq() {
		Req r = new Req();
		r.setDeveloper(developer);
		r.setEstimate(estimate);
		r.setId(requirementId);
		r.setPriority(priority);
		r.setRejection(this.getRejectionReasonString());
		r.setSummary(summary);
		r.setTest(acceptanceTestId);
		if(state == submittedState) {
			r.setState(SUBMITTED_NAME);
		}
		if(state == acceptedState) {
			r.setState(ACCEPTED_NAME);
		}
		if(state == workingState) {
			r.setState(WORKING_NAME);
		}
		if(state == completedState) {
			r.setState(COMPLETED_NAME);
		}
		if(state == verifiedState) {
			r.setState(VERIFIED_NAME);
		}
		if(state == rejectedState) {
			r.setState(REJECTED_NAME);
		}
		return r;
	}
	/** 
	 * This method is used to set the counter so that the requirementId will one more than the highest id number.
	 * @param count is the number that will be set as the counter
	 * @throws IllegalArgumentException if the count is less than 0
	 */
	public static void setCounter(int count) {
		if (count < 0) {
			throw new IllegalArgumentException();
		}
		counter = count;
	}
	
	/** 
	 * This is the inner class for submitted state and is called upon in the updated method of Requirement. 
	 * This class has two methods that are implemented in all the inner classes since it is from an inheritance.
	 */
	private class Submitted implements RequirementState { 
		/**
		 * This is the method implemented for each RequirementState. Depending on the command object that is passed through,
		 * it will update the Requirement and all its fields depending on which RequirementState which it is in. For this class,
		 * this method will be updating the summary and acceptanceTestId.
		 */
		@Override
		public void updateState(Command command) {
			summary = command.getSummary();
			setAcceptanceTestId(command.getAcceptanceTestId());
			rejectionReason = command.getRejectionReason();
		}
		/**
		 * This method returns the String name of the current state. 
		 * It is used in the updated method of Requirement
		 * @return String of state name
		 */
		@Override
		public String getStateName() {
			return SUBMITTED_NAME;
		}
	}
	/** 
	 * This is the inner class for accepted state and is called upon in the updated method of Requirement. 
	 * This class has two methods that are implemented in all the inner classes since it is from an inheritance.
	 */
	private class Accepted implements RequirementState { 
		/**
		 * This is the method implemented for each RequirementState. Depending on the command object that is passed through,
		 * it will update the Requirement and all its fields depending on which RequirementState which it is in. For this class,
		 * it will be updating the estimate and priority
		 */
		@Override
		public void updateState(Command command) {
			estimate = command.getEstimate();
			priority = command.getPriority();
		}
		/**
		 * This method returns the String name of the current state. 
		 * It is used in the updated method of Requirement
		 * @return String of state name
		 */
		@Override
		public String getStateName() {
			return ACCEPTED_NAME;
		}
	}
	/** 
	 * This is the inner class for working state and is called upon in the updated method of Requirement. 
	 * This class has two methods that are implemented in all the inner classes since it is from an inheritance.
	 */
	private class Working implements RequirementState { 
		/**
		 * This is the method implemented for each RequirementState. Depending on the command object that is passed through,
		 * it will update the Requirement and all its fields depending on which RequirementState which it is in. For this class,
		 * it will set the developer
		 */
		@Override
		public void updateState(Command command) {
			setDeveloper(command.getDeveloperId());
		}
		/**
		 * This method returns the String name of the current state. 
		 * It is used in the updated method of Requirement
		 * @return String of state name
		 */
		@Override
		public String getStateName() {
			return WORKING_NAME;
		}
	}
	/** 
	 * This is the inner class for completed state and is called upon in the updated method of Requirement. 
	 * This class has two methods that are implemented in all the inner classes since it is from an inheritance.
	 */
	private class Completed implements RequirementState { 
		/**
		 * This is the method implemented for each RequirementState. Depending on the command object that is passed through,
		 * it will update the Requirement and all its fields depending on which RequirementState which it is in. This class will not be 
		 * updating anything but it must be implemented in all inner classes.
		 */
		@Override
		public void updateState(Command command) {
			setState(COMPLETED_NAME);
		}
		/**
		 * This method returns the String name of the current state. 
		 * It is used in the updated method of Requirement
		 * @return String of state name
		 */
		@Override
		public String getStateName() {
			return COMPLETED_NAME;
		}
	}
	/** 
	 * This is the inner class for verified state and is called upon in the updated method of Requirement. 
	 * This class has two methods that are implemented in all the inner classes since it is from an inheritance.
	 */
	private class Verified implements RequirementState { 
		/**
		 * This is the method implemented for each RequirementState. Depending on the command object that is passed through,
		 * it will update the Requirement and all its fields depending on which RequirementState which it is in. This class
		 * will not be updating anything but it must be implemented in all inner classes.
		 */
		@Override
		public void updateState(Command command) {
			System.out.println("Never used but needs to be implented in each inner state");
		}
		/**
		 * This method returns the String name of the current state. 
		 * It is used in the updated method of Requirement
		 * @return String of state name
		 */
		@Override
		public String getStateName() {
			return VERIFIED_NAME;
		}
	}
	/** 
	 * This is the inner class for rejected state and is called upon in the updated method of Requirement. 
	 * This class has two methods that are implemented in all the inner classes since it is from an inheritance.
	 */
	private class Rejected implements RequirementState { 
		/**
		 * This is the method implemented for each RequirementState. Depending on the command object that is passed through,
		 * it will update the Requirement and all its fields depending on which RequirementState which it is in. This class
		 * will be setting the priority, estimate, developer to null or 0.
		 */
		@Override
		public void updateState(Command command) {
			priority = command.getPriority();
			estimate = command.getEstimate();
			setDeveloper(command.getDeveloperId());
			rejectionReason = command.getRejectionReason();
		}
		/**
		 * This method returns the String name of the current state. 
		 * It is used in the updated method of Requirement
		 * @return String of state name
		 */
		@Override
		public String getStateName() {
			return REJECTED_NAME;
		}
	}
}
