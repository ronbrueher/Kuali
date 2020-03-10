package com.kuali.challenge;


class Elevator {
	// encapsulates all the state data related to an instance of Elevator		
	private int elevatorNumber = 0;
	private int currentFloor = 0;
	private int destinationFloor = 0;
	private int totalFloors = 0;
	private int totalTrips = 0;
	private boolean isDoorOpen = false;
	private boolean isOccupied = false;
	private boolean isMoving = false;
	
	public Elevator (int elevatorNumber) {
		// constructor for new elevator
		this.elevatorNumber = elevatorNumber;
	}
	
	public void setIsDoorOpen(boolean state) {
		this.isDoorOpen = state;
	}
	
	public boolean getIsMoving() {
		return this.isMoving;
	}
	
	public boolean getIsOccupied() {
		return this.isOccupied;
	}
	
	public int getNumber() {
		return this.elevatorNumber;
	}
	
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	public int getDestinationFloor() {
		return this.destinationFloor;
	}
	
	public int elevatorMove(int requestedFloor) {
		// close the elevator door and prepare to move
		this.isDoorOpen = false;
		this.destinationFloor = requestedFloor;
		
		// increment the number of trips made by this elevator
		this.totalTrips++;
		if (totalTrips > 99) {
			// take this elevator out of service for maintenance
			this.elevatorNumber = 0;
		}
		
		if (requestedFloor < this.currentFloor) {
			this.isMoving = true;		// elevator is moving to another floor
			
			// need to go down to requested floor				
			while (this.currentFloor > 1) {
				
				this.currentFloor--; 	// go down a floor
				this.totalFloors++;		// increment count of floors for this elevator
				
				// change the floor and report state
				if (changeFloorState(requestedFloor) == true) {
					// we arrived at the requested floor
					break;
				}
			}
		}
		else if (requestedFloor > this.currentFloor) {
			this.isMoving = true;		// elevator is moving to another floor
			
			// need to go up to requested floor
			while (this.currentFloor < totalFloors) {
				
				this.currentFloor++; 	// go up a floor
				this.totalFloors++;		// increment count of floors for this elevator
				
				// change the floor and report state
				if (changeFloorState(requestedFloor) == true) {
					// we arrived at the requested floor
					break;
				}
			}
		}
		else {
			// elevator is already at this floor - open doors
			this.isMoving = false;
			this.isDoorOpen = true;
		}
		
		return currentFloor;
	}
	
	private boolean changeFloorState(int requestedFloor) {
		// handles a change in Elevator floor state			
		ElevatorControl.reportFloorState(this.elevatorNumber, this.currentFloor); // report floor change for this elevator
		
		if (this.currentFloor == requestedFloor) {
			// we have arrived - open doors
			this.isMoving = false;
			this.isDoorOpen = true;
			ElevatorControl.reportDoorState(this.elevatorNumber, true);
			return true;
		}
		return false;
	}
}