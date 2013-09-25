package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.gamemaker.view.AppWindow;

public class Application {

	public static final Logger logger = Logger.getLogger(Application.class);

	private static final long serialVersionUID = 1L;

	private JFrame mainWindowFrame;

	public Application() {
		this.mainWindowFrame = new JFrame();
		new AppWindow(mainWindowFrame);
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties"); 
		logger.debug("Enter main method");
		new Application();
	}
}
