package com.mov.ratings.grouping;
import java.util.Scanner;
import java.util.Iterator;
import java.util.List;
//import java.util.Map;
import java.util.Map.Entry;
//import java.util.HashMap;
public class AllGroupCall {
public static void main (String args[]) {
	Data users = new Data();
	Scanner Scan = new Scanner(System.in);
	System.out.println(" 1: List of Movies by Year:");
	System.out.println(" 2: List Of Users :");
	System.out.println(" 3: Top 100 Rated Movies :");
	System.out.print("   Choose One Option Above : ");
	int Input = Scan.nextInt();
	switch(Input) {
	case 1: for (Iterator<Entry<Integer, List<String>>> iterator = users.getMoviesList().entrySet().iterator(); iterator.hasNext();) {
		Entry<Integer, List<String>> m = iterator.next();
		System.out.println(m.getKey()+" : "+m.getValue());
	}  
	;
		break ;
	case 2: 
		for (Iterator<Entry<Integer, List<String>>> iterator = users.getUsersList().entrySet().iterator(); iterator.hasNext();) {
			Entry<Integer, List<String>> m = iterator.next();
			System.out.println(m.getKey()+" : "+m.getValue());
		}  
		;
		break;
	case 3: 
		users.getTopList();
		;
		break;
     default: System.out.println("Please select one of the Above Option !");
        break;
	}   
	Scan.close();
}
}
