package com.breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;

public class DirectoryFileNameScan {

	public ArrayDeque fileNameSearch(){
		String directoryPath = "..//team7//Save_Games//";
		//String[] fileNames = {""};
		ArrayDeque fileNames = new ArrayDeque<String>();
		int i = 1;
		try {
			File dir = new File(directoryPath);
			File[] filesList = dir.listFiles();
			for (File file : filesList) {
				if (file.isFile()) {
					
					fileNames.add(file.getCanonicalFile().getName());
					//fileNames[i] = file.getCanonicalFile().getName();
					//i++;
					//System.out.println(file.getCanonicalFile().getName());
					
				} else if (file.isDirectory())
					continue;
			}
		}catch (FileNotFoundException e) {
			System.err.println("Directory children files read: " + e);
		} catch (IOException e) {
			System.err.println("Directory children files read: " + e);
		}
		
		return fileNames;
		
	}
}
