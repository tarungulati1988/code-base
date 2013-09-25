package com.gamemaker.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.gamecontroller.GameMakerController;

/**
 * This defines the main application class. It just instanciates the controller,
 * model and view.
 * 
 * @author Amey
 * 
 */
public class ApplicationMenuView {
	JMenuBar menuBar;
	GameMakerController controller;

	@SuppressWarnings("unused")
	private ApplicationMenuView() {
	}

	ApplicationMenuView(JMenuBar menuBar, GameMakerController controller) {
		this.controller = controller;
		this.menuBar = menuBar;
		addSupportedMenus();
	}

	private void addSupportedMenus() {
		String menuItemStr = "Menu";
		String[] subMenuItems = { MenuConstants.NEW_GAME,
				MenuConstants.LOAD_GAME, MenuConstants.ADD_SPRITE, MenuConstants.PLAY_GAME };

		JMenu menu = new JMenu(menuItemStr);
		for (String subMenu : subMenuItems) {
			JMenuItem jMenuItem = new JMenuItem(subMenu);
			jMenuItem.addActionListener(new MenuActionListener());
			menu.add(jMenuItem);
		}
		
		this.menuBar.add(menu);
	}

	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			controller.announceMenuClicked(ae.getActionCommand());
		}
	}

}
