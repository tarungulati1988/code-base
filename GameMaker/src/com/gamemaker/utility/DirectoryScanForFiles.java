package com.gamemaker.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;

/*
 * 
 * helper class to scan a given directory structure and fetch all the children file names
 * a call to fileNameSearch(String folderName) is made with passing the folder name
 * as a string
 * this method returns an arrayDeque containing all the file names of the files 
 * present under the directory
 * 
 */
public class DirectoryScanForFiles {

	/*
	 * 
	 * method: fileNameSearch return type: ArrayDeque arguments: String
	 * folderName this method is used to look under a specified directory and
	 * return an arrayDeque containing all the file names of the files under the
	 * directory
	 */
	public ArrayDeque<String> fileNameSearch(String folderName) {
		String directoryPath = "..//team2//" + folderName;


		ArrayDeque<String> fileNames = new ArrayDeque<String>();
	//	int i = 1;
		try {
			File dir = new File(directoryPath);
			File[] filesList = dir.listFiles();
			for (File file : filesList) {
				if (file.isFile()) {

					fileNames.add(file.getCanonicalFile().getName());

				} else if (file.isDirectory())
					continue;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Directory children files read: " + e);
		} catch (IOException e) {
			System.err.println("Directory children files read: " + e);
		}

		return fileNames;

	}
}