package com.breakout;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.stream.JsonWriter;

public class SaveableComposite extends SaveableLoadable {

	public Board board;
	public BallSaveableLoadable ballSaveableObj;
	public PaddleSaveableLoadable paddleSaveableObj;
	public BrickSaveableLoadable brickSaveableObj;
	public ClockSaveableLoadable clockSaveableObj;

	public void addState(Board board) {

		//board.pausePlayObj.execute(board);

		this.board = board;
		ballSaveableObj = new BallSaveableLoadable();
		paddleSaveableObj = new PaddleSaveableLoadable();
		brickSaveableObj = new BrickSaveableLoadable();
		clockSaveableObj = new ClockSaveableLoadable();

		int[] ballCoords = new int[4];
		ballCoords = ballSaveableObj.get(board);

		int[] paddleCoords = new int[1];
		paddleCoords = paddleSaveableObj.get(board);

		int[] brickCoords = new int[2];
		brickCoords = brickSaveableObj.get(board);

		int[] timerData = new int[2];
		timerData = clockSaveableObj.get(board);

		// Write to JSON file using gson
		SimpleDateFormat sdfDate = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);

		String filePathForSave = "..\\team5\\Save_Games\\Save_"
				+ strDate.toString() + ".json";
		String filePathForReplayUndo = "..\\team5\\Replay_Undo_History\\Save_"
				+ strDate.toString() + ".json";
		try {

			FileWriter fileWriterSave = new FileWriter(filePathForSave);
			JsonWriter jsonWriterSave = new JsonWriter(fileWriterSave);

			jsonWriterSave.beginObject();

			jsonWriterSave.name("BallX").value(ballCoords[0]);

			jsonWriterSave.name("BallY").value(ballCoords[1]);

			jsonWriterSave.name("BallXDir").value(ballCoords[2]);

			jsonWriterSave.name("BallYDir").value(ballCoords[3]);

			jsonWriterSave.name("BrickX").value(brickCoords[0]);

			jsonWriterSave.name("BrickY").value(brickCoords[1]);

			jsonWriterSave.name("PaddleX").value(paddleCoords[0]);

			jsonWriterSave.name("Minutes").value(timerData[0]);

			jsonWriterSave.name("Seconds").value(timerData[1]);

			jsonWriterSave.endObject();

			jsonWriterSave.close();

			FileWriter fileWriterReplay = new FileWriter(filePathForReplayUndo);
			JsonWriter jsonWriterReplay = new JsonWriter(fileWriterReplay);

			jsonWriterReplay.beginObject();
			ArrayDeque tempBallX = new ArrayDeque();
			ArrayDeque tempBallY = new ArrayDeque();
			ArrayDeque tempPaddleX = new ArrayDeque();
			ArrayDeque tempBallXDir = new ArrayDeque();
			ArrayDeque tempBallYDir = new ArrayDeque();
			tempBallX = board.dequeBallX;
			tempBallY = board.dequeBallY;
			tempBallXDir = board.dequeBallXDir;
			tempBallYDir = board.dequeBallYDir;
			tempPaddleX = board.dequePaddleX;
			for (int i = 0; i < board.dequeBallX.size()*32; i++) {

				jsonWriterReplay.name("ballX").value(
						(int) tempBallX.removeFirst());
				jsonWriterReplay.name("ballY").value(
						(int) tempBallY.removeFirst());
				jsonWriterReplay.name("ballXDir").value(
						(int) tempBallXDir.removeFirst());
				jsonWriterReplay.name("ballYDir").value(
						(int) tempBallYDir.removeFirst());
				jsonWriterReplay.name("paddleX").value(
						(int) tempPaddleX.removeFirst());
				System.out.println(i);
			}

			jsonWriterReplay.endObject();

			jsonWriterReplay.close();

		} catch (FileNotFoundException e) {
			System.err.println("FileStreamsReadnWrite: " + e);
		} catch (IOException e) {
			System.err.println("FileStreamsReadnWrite: " + e);
		} 
		//board.pausePlayObj.execute(board);

	}

}
