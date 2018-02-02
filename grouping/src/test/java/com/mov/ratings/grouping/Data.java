package com.mov.ratings.grouping;
import com.google.common.collect.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
public class Data {
	private List<Object> list;

	public HashMap<Integer,List<String>> getUsersList()
	{
//	Scanner scn = new Scanner(System.in);	
//	Defining Age and Occupation 
	String Age[] = {"1","Under 18","18","18-24","25","25-34","35","35-44","45","45-49","50","50-55","56","56+"};
	String Ocp[] = {"0","other/not specified","1","academic/educator","2","artist","3","clerical/admin","4","college/grad student","5","customer service","6","doctor/health care","7","executive/managerial","8","farmer","9","homemaker","10","K-12 student","11","lawyer","12","programmer","13","retired","14","sales/marketing","15","scientist","16","self-employed","17","technician/engineer","18","tradesman/craftsman","19","unemployed","20","writer"};
  //Creating Age and Ocp maps
	HashMap<Integer,String> age = createMap(Age);
	HashMap<Integer,String> ocp = createMap(Ocp);
	HashMap<Integer,List<String>> Users = new HashMap<Integer, List<String>>();
		String fileName = "D:\\\\ml-1m\\Users.dat";
		String line = null;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
               String[] Split = line.split("::");
               List<String> list = new ArrayList<String>();
               for(int i=1;i<Split.length;i++) {
            	   if(i==2) {
            		  Split[i] =age.get(Integer.parseInt(Split[i]));  
            	   }
            	   else if(i==3){
            		   Split[i] =ocp.get(Integer.parseInt(Split[i])); 
            	   }
            	  list.add(Split[i]); 
               }
               Users.put(Integer.parseInt(Split[0]),list);
            }   
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	return Users;
	}

public HashMap<Integer,String> createMap(String[] Age)
	{
		HashMap<Integer,String> Mp=new HashMap<Integer,String>();
		for(int i=0;i< Age.length;i++) {
			Mp.put(Integer.parseInt(Age[i]),Age[i+1]);
			i++;
		}
		return Mp;
	}
public HashMap<Integer,List<String>> getMoviesList()

{
	String fileName = "D:\\\\ml-1m\\Movies.dat";
	String line = null;
	HashMap<Integer,List<String>> Mov = new HashMap<Integer, List<String>>();
	try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);
        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null) {
        	String[] Split = line.split("::");
            List<String> list = new ArrayList<String>();
            for(int i=1;i<Split.length;i++) {
         	  list.add(Split[i]); 
            }
            Mov.put(Integer.parseInt(Split[0]),list);
        }   
        // Always close files.
        bufferedReader.close();   
      fileReader.close();  
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }
return Mov;
	}
public void getTopList(){
	 ListMultimap<Integer,Integer> Ratings =  ArrayListMultimap.create();
	String fileName = "D:\\\\ml-1m\\Ratings.dat";
	String line = null;
	try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);
        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null) {
           String[] Split = line.split("::");
           for(int i=0;i<Split.length;i++) {
       		 Ratings.put(Integer.parseInt(Split[1]),Integer.parseInt(Split[2]));
		   }
        }   
        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }
	sortRatings(Ratings);
}
private void sortRatings(ListMultimap<Integer, Integer> Ratings){
	HashMap<Integer,String> Rated =  new HashMap<Integer,String>();
Iterator<Integer> Iter= Ratings.keySet().iterator();
while(Iter.hasNext())
{
	DecimalFormat df = new DecimalFormat ();
	df.setMaximumFractionDigits (1);
	df.setMinimumFractionDigits (1);
	Float Sum=0.f;
	int count=0;
	int Key=Iter.next();
	List<Integer> list = Ratings.get(Key);
for (int value : list) {
   Sum=Sum+value;
   count++;
}
Rated.put(Key,String.valueOf(df.format(Sum/count)));
}
Map<Integer, String> map = sortByValues(Rated); 
Set<?> set2 = map.entrySet();
Iterator<?> iterator2 = set2.iterator();
int i=0;
while(iterator2.hasNext()) {
     @SuppressWarnings("rawtypes")
	Map.Entry me2 = (Map.Entry)iterator2.next();
     if(i<250){
     System.out.print(me2.getKey() + ": ");
     System.out.println(me2.getValue());
     i++;
     }
}
}

private HashMap<Integer, String> sortByValues(HashMap<Integer, String> map) {
	
	list = new LinkedList<Object>(map.entrySet());
    // Defined Custom Comparator here
    Collections.sort(list, new Comparator<Object>() {
         @SuppressWarnings({ "rawtypes", "unchecked" })
		public int compare(Object o1, Object o2) {
            return ((Comparable) ((Map.Entry) (o2)).getValue())
               .compareTo(((Map.Entry) (o1)).getValue());
         }
    });
    // Here I am copying the sorted list in HashMap
    // using LinkedHashMap to preserve the insertion order
    HashMap<Integer, String> sortedHashMap = new LinkedHashMap<Integer, String>();
    for (Iterator<Object> it = list.iterator(); it.hasNext();) {
           @SuppressWarnings("unchecked")
		Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) it.next();
           sortedHashMap.put(entry.getKey(), entry.getValue());
    } 
    return sortedHashMap;
    } 
}