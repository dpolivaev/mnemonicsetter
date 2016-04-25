package org.dpolivaev.mnemonicsetter;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class UsedAltAcceleratorsFinder {

	private HashSet<Integer> hashSet;
	
	static public UsedAltAcceleratorsFinder INSTANCE = new UsedAltAcceleratorsFinder();

	public Collection<Integer> findUsedKeyCodes(JMenuBar menuBar) {
		hashSet = new HashSet<>();
		for(int i = 0; i < menuBar.getMenuCount(); i++) {
			final JMenu menu = menuBar.getMenu(i);
			findUsedMenuCodes(menu);
		}
		return hashSet;
	}

	private void findUsedMenuCodes(JMenu menu) {
		for(int i = 0; i < menu.getMenuComponentCount(); i++) {
			final Component menuComponent = menu.getMenuComponent(i);
			if(menuComponent instanceof JMenu){
				findUsedMenuCodes((JMenu) menuComponent);
			}
			if(menuComponent instanceof JMenuItem){
				final KeyStroke accelerator = ((JMenuItem)menuComponent).getAccelerator();
				if(accelerator != null) {
					final int keyCode = accelerator.getKeyCode();
					if (keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z 
							&&(accelerator.getModifiers() & (KeyEvent.ALT_DOWN_MASK 
									| KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK | KeyEvent.META_DOWN_MASK)) == KeyEvent.ALT_DOWN_MASK) {
						hashSet.add(keyCode);
					}
				}
			}
		}
	}

}
