package com.mov.ratings.grouping;
import java.util.Iterator;
import java.util.LinkedList;

public class UrlLibrary implements Iterable<String> {
LinkedList<String> lnk = new LinkedList<String>();
	
	public UrlLibrary() {
	lnk.add("ICE");
	lnk.add("CSC");
	lnk.add("MSC");
	lnk.add("MCA"); 
	}

	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return lnk.iterator();
	}
}
