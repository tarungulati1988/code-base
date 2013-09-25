package com.gamemaker.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to write and read the JSON objects into and from the file.
 * 
 */
public class FileManager {
	private final String filePath;
	FileReader fileReader;
	FileWriter fileWriter;
	StringBuffer stringBuffer;
	BufferedReader bufferedReader;

	public FileManager(String path) {
		filePath = path;
	}

	public void closeFile() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This function is used to load the contents from the file and return it as
	 * string
	 */
	public String load() {
		stringBuffer = new StringBuffer();
		try {
			FileReader fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();

		} catch (Exception e) {

		}
		return stringBuffer.toString();
	}

	/*
	 * This function is used to write the string data into the file
	 */
	public void save(String data) {
		try {
			fileWriter = new FileWriter(filePath);
			fileWriter.write(data);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
