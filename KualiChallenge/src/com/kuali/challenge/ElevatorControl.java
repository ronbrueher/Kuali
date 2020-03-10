package com.kuali.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;

// import java.util.Scanner;

public class ElevatorControl {
	
	List<Elevator> elevators = new ArrayList<Elevator>();
	static int numElevators = 0;
	static int numFloors = 0;
	
	public static void main(String[] args) {
		// input should be the number of elevators and number of floors, on the same line, separated by a space
		// e.g. the following command line argument specifies 4 elevators and 8 floors:
		//
		// java ElevatorControl 4 8
		//
		
		numElevators = Integer.parseInt(args[0]);
		numFloors = Integer.parseInt(args[1]);
		
		System.out.println("numElevators: " + numElevators + "  numFloors: " + numFloors);
	}
	
	public void initElevators(int numElevators) {
		// initialize the Elevators and store in an array list
		for (int i = 0; i < numElevators; i++) {
			Elevator elevator = new Elevator(i + 1);
			this.elevators.add(elevator);
		}
	}
	
	public void handleElevatorRequest(int currentFloor, int elevatorNumber) {
		int minDistance = 0;
		
		// see if any elevators are moving past this floor
		for (int i = 0; i < numElevators; i++) {
			if (this.elevators.get(i).getIsMoving() == false &&
					this.elevators.get(i).getIsOccupied() == false && 
					this.elevators.get(i).getCurrentFloor() == currentFloor) {
				// elevator is not moving, unoccupied and on the current floor - open doors
				this.elevators.get(i).setIsDoorOpen(true);
			}
			else if (this.elevators.get(i).getIsMoving() && this.elevators.get(i).getIsOccupied()) {
				// elevator occupied and moving - see if it will pass this floor
				if (this.elevators.get(i).getCurrentFloor() < currentFloor && 
						this.elevators.get(i).getDestinationFloor() > currentFloor) {
					// elevator going up and will pass this floor - move elevator to this floor
					this.elevators.get(i).elevatorMove(currentFloor);
				}
				else if (this.elevators.get(i).getCurrentFloor() > currentFloor && 
						this.elevators.get(i).getDestinationFloor() < currentFloor) {
					// elevator going down and will pass this floor - move elevator to this floor
					this.elevators.get(i).elevatorMove(currentFloor);
				}
				
			}
			else {
				// find closest unoccupied elevator	
				for (int x = elevatorNumber; x < numElevators; x++) {
					if (this.elevators.get(x).getIsOccupied() == false) {
						int diff = Math.abs(this.elevators.get(x).getCurrentFloor() - currentFloor);
						
						if (diff < minDistance) {
							// this elevator is closer
							minDistance = diff;
							elevatorNumber = this.elevators.get(x).getNumber();
						}
					}
					// move the unoccuppied elevator to current floor
					this.elevators.get(elevatorNumber).elevatorMove(currentFloor);
				}
			}
		}
	}
	
	public static void reportDoorState(int elevatorNumber, boolean isOpen) {
		// reports a change in Elevator door state
		if (isOpen) {
			System.out.printf("Elevator #%d door is open", elevatorNumber);
		}
		else {
			System.out.printf("Elevator #%d door is closed", elevatorNumber);
		}
	}
	
	public static void reportFloorState(int elevatorNumber, int floorNumber) {
		// reports a change in Elevator floor state
		System.out.printf("Elevator #%d just passed floor #%d", elevatorNumber, floorNumber);
	}
	
	
	

}