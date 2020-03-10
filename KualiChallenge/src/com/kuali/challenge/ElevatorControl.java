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

	public static void main(String[] args) {
		// input should be the number of elevators and number of floors, on the same line, separated by a space
		// e.g. the following command line argument specifies 4 elevators and 8 floors:
		//
		// java ElevatorControl 4 8
		//
		
		int numElevators = Integer.parseInt(args[0]);
		int numFloors = Integer.parseInt(args[1]);
		
		System.out.println("numElevators: " + numElevators + "  numFloors: " + numFloors);
	}
	
	public void reportDoorState(int elevatorNumber, boolean isOpen) {
		// reports a change in Elevator door state

	}
	
	public void reportFloorState(int elevatorNumber, int floorNumber) {
		// reports a change in Elevator floor state

	}
	
	
	
	class Elevator {
		// encapsulates all the state data related to an instance of Elevator
		
		private int elevatorNumber = 0;
		private int currentFloor = 0;
		private int totalFloors = 0;
		private int totalTrips = 0;
		private boolean isDoorOpen = false;
		private boolean isOccupied = false;
		
		public int elevatorMove(int requestedFloor) {
			// close the elevator door
			this.isDoorOpen = false;
			
			// increment the number of trips made by this elevator
			this.totalTrips++;
			
			if (requestedFloor < this.currentFloor) {
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
			else {
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
			
			return currentFloor;
		}
		
		private boolean changeFloorState(int requestedFloor) {
			// handles a change in Elevator floor state			
			reportFloorState(this.elevatorNumber, this.currentFloor); // report floor change for this elevator
			
			if (this.currentFloor == requestedFloor) {
				// we have arrived - open doors
				this.isDoorOpen = true;
				reportDoorState(this.elevatorNumber, true);
				return true;
			}
			return false;
		}
	}
}