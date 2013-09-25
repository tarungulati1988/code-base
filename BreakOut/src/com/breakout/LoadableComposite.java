package com.breakout;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import com.google.gson.stream.JsonReader;

public class LoadableComposite extends SaveableLoadable {

	public Board board;
	public BallSaveableLoadable ballSaveableObj;
	public PaddleSaveableLoadable paddleSaveableObj;
	public BrickSaveableLoadable brickSaveableObj;
	public ClockSaveableLoadable clockSaveableObj;

	public void removeState(Board board) {
		//board.pausePlayObj.execute(board);

		this.board = board;
		ballSaveableObj = new BallSaveableLoadable();
		paddleSaveableObj = new PaddleSaveableLoadable();
		brickSaveableObj = new BrickSaveableLoadable();
		clockSaveableObj = new ClockSaveableLoadable();
		
		int ballCoords[] = new int[4];
		int brickCoords[] = new int[2];
		int paddleCoords[] = new int[1];
		int timerData[] = new int[2];

		try {

			FileReader fileReader = new FileReader(
					"..//team5//Save_Games//Save_2013-09-16_06-26-58.json");

			JsonReader jsonReader = new JsonReader(fileReader);

			jsonReader.beginObject();

			while (jsonReader.hasNext()) {

				String name = jsonReader.nextName();

				if (name.equals("BallX")) {

					// text = jsonReader.nextString();
					ballCoords[0] = (int) jsonReader.nextInt();

				} else if (name.equals("BallY")) {

					// System.out.println("members: " + jsonReader.nextInt());
					ballCoords[1] = (int) jsonReader.nextInt();

				} else if (name.equals("BallXDir")) {

					// System.out.println("BallXDir: " + jsonReader.nextInt());
					ballCoords[2] = (int) jsonReader.nextInt();

				} else if (name.equals("BallYDir")) {

					// System.out.println("BallYDir: " + jsonReader.nextInt());
					ballCoords[3] = (int) jsonReader.nextInt();

				} else if (name.equals("BrickX")) {

					// System.out.println("BrickX: " + jsonReader.nextInt());
					brickCoords[0] = (int) jsonReader.nextInt();

				} else if (name.equals("BrickY")) {

					// System.out.println("BrickY: " + jsonReader.nextInt());
					brickCoords[1] = (int) jsonReader.nextInt();

				} else if (name.equals("PaddleX")) {

					// System.out.println("PaddleX: " + jsonReader.nextInt());
					paddleCoords[0] = (int) jsonReader.nextInt();

				} else if (name.equals("Minutes")) {

					// System.out.println("Minutes: " + jsonReader.nextInt());
					timerData[0] = (int) jsonReader.nextInt();

				} else if (name.equals("Seconds")) {

					// System.out.println("Seconds: " + jsonReader.nextInt());
					timerData[1] = (int) jsonReader.nextInt();

				} else {
					// use this when you are not sure about all the contents in
					// th JSON file
					jsonReader.skipValue();
				}
			}

			jsonReader.endObject();
			jsonReader.close();
			ballSaveableObj.set(board, ballCoords);
			brickSaveableObj.set(board, brickCoords);
			paddleSaveableObj.set(board, paddleCoords);
			clockSaveableObj.set(board, timerData);
			FileReader fileReaderReplay = new FileReader(
					"..//team5//Replay_Undo_History//Save_2013-09-16_06-26-58.json");

			@SuppressWarnings("resource")
			JsonReader jsonReaderReplay = new JsonReader(fileReaderReplay);

			jsonReaderReplay.beginObject();
			
			while (jsonReaderReplay.hasNext()) {

				String name = jsonReaderReplay.nextName();

				if (name.equals("ballX")) {

					board.dequeBallX.add(jsonReaderReplay.nextInt());

				} else if (name.equals("ballY")) {

					board.dequeBallY.add(jsonReaderReplay.nextInt());

				} else if (name.equals("ballXDir")) {

					board.dequeBallXDir.add(jsonReaderReplay.nextInt());

				} else if (name.equals("ballYDir")) {

					board.dequeBallYDir.add(jsonReaderReplay.nextInt());

				} else if (name.equals("paddleX")) {

					board.dequePaddleX.add(jsonReaderReplay.nextInt());

				} else {
					// use this when you are not sure about all the contents in
					// the JSON file
					jsonReaderReplay.skipValue();
				}
			}
			jsonReaderReplay.endObject();
			jsonReaderReplay.close();

		} catch (FileNotFoundException e) {
			System.err.println("FileStreamsReadnWrite: " + e);
		} catch (IOException e) {
			System.err.println("FileStreamsReadnWrite: " + e);
		}
		board.pausePlayObj.execute(board);
	}
}
