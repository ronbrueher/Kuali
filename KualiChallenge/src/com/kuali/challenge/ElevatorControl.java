package com.kuali.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.LinkedList;

// import java.util.Scanner;

public class ElevatorControl {

	public static void main(String[] args) {
		int numFeatures = 5;
		int topFeatures = 2;
		List<String> possibleFeatures = new ArrayList();
		int numFeatureRequests = 3;
		List<String> featureRequests = new ArrayList();	
		
		possibleFeatures.add("aaa");
		possibleFeatures.add("bbb");
		possibleFeatures.add("ccc");
		possibleFeatures.add("ddd");
		possibleFeatures.add("eee");
		
		featureRequests.add("Best services provided by aaa");
		featureRequests.add("bbb has great services");
		featureRequests.add("aaa provides better services than all other");
        
		ArrayList<String> result = popularNFeatures(numFeatures, topFeatures, possibleFeatures, numFeatureRequests, featureRequests);
		
		
		
		
		
//		Scanner scan = new Scanner(System.in);
//		int i = scan.nextInt();
//		double d = scan.nextDouble();
//		String s = scan.nextLine();
//		s = scan.nextLine();
//
//		System.out.println("String: " + s);
//		System.out.println("Double: " + d);
//		System.out.println("Int: " + i);
		
//		for(int i = 0; i < args.length; i++) 
//		{
//	         System.out.println("args[" + i + "]: " + args[i]);
//		}
//		
//		
//		args[0] = args[0].replace("[", "");
//		args[0] = args[0].replace("]", "");
//		String[] arr = args[0].split(",");
//		String num = args[1];
//		
//		System.out.println("hello world");
	}
	
	public static ArrayList<String> popularNFeatures(int numFeatures, int topFeatures, List<String> possibleFeatures, int numFeatureRequests, List<String> featureRequests)
	{
		HashMap<String, Integer> featuresMap = new HashMap<String, Integer>();
		
		// load hashmap with the possible features as keys
		for (int i = 0; i < numFeatures; i++) {
			featuresMap.put(possibleFeatures.get(i), 0);
		}
		
		// loop through feature requests and update count of features in hashmap
		for (String request: featureRequests){
			// see if feature request contains possible features
			for ( String key : featuresMap.keySet() ) {
				if (request.indexOf(key) != -1)
				{
					// increment feature count
					featuresMap.put(key, featuresMap.get(key) + 1);
				}
			}
		}
		
		// Create a list from elements of HashMap 
        LinkedList<Entry<String, Integer>> featureList = 
               new LinkedList<Map.Entry<String, Integer> >(featuresMap.entrySet()); 
  
        // Sort the list 
        Collections.sort(featureList, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
		
        ArrayList<String> resultList = new ArrayList();
        int count = 0;
        for (int x = featureList.size() - 1; count < topFeatures; x--) {
        	resultList.add(featureList.get(x).getKey());
        	count++;
        }
		return resultList;
	}
	
	
}