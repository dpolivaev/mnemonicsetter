package org.dpolivaev.mnemonicsetter;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.junit.Before;
import org.junit.Test;

public class UsedAltAcceleratorsFinderShould {
	private UsedAltAcceleratorsFinder usedAltAcceleratorsFinder;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	@Before
	public void setup() {
		usedAltAcceleratorsFinder = new UsedAltAcceleratorsFinder();
		menuBar = new JMenuBar();
		menu = new JMenu();
		menuBar.add(menu);
		menuItem = new JMenuItem();
		menu.add(menuItem);
	}
	
	@Test
	public void returnsEmptyCollectionIfNoAcceleratorsAreFound() throws Exception {
		assertTrue(usedAltAcceleratorsFinder.findUsedKeyCodes(menuBar).isEmpty());
	}
	@Test
	public void returnsFoundAcceleratorIfAcceleratorsAreFound() throws Exception {
		JMenuItem menuItem2 = new JMenuItem();
		JMenu menu2 = new JMenu();
		menu2.add(menuItem2);
		menu.add(menu2);
		JMenu menu3 = new JMenu();
		JMenuItem menuItem3 = new JMenuItem();
		menu3.add(menuItem3);
		menuBar.add(menu3);
		JMenuItem menuItem4 = new JMenuItem();
		menu.add(menuItem4);

		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_DOWN_MASK));
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.ALT_DOWN_MASK));
		menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK));
		menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_DOWN_MASK));
		assertTrue(usedAltAcceleratorsFinder.findUsedKeyCodes(menuBar).containsAll(Arrays.asList(KeyEvent.VK_A, KeyEvent.VK_B, KeyEvent.VK_C, KeyEvent.VK_D)));
	}
	
	@Test
	public void returnsFoundAcceleratorIfShiftIsRequired() throws Exception {
		final int extendedKeyCodeForCharA = KeyEvent.getExtendedKeyCodeForChar('A');
		menuItem.setAccelerator(KeyStroke.getKeyStroke(extendedKeyCodeForCharA, KeyEvent.ALT_DOWN_MASK|KeyEvent.SHIFT_DOWN_MASK));
		assertTrue(usedAltAcceleratorsFinder.findUsedKeyCodes(menuBar).isEmpty());
	}

	@Test
	public void returnsFoundAcceleratorIfControlIsRequired() throws Exception {
		final int extendedKeyCodeForCharA = KeyEvent.getExtendedKeyCodeForChar('A');
		menuItem.setAccelerator(KeyStroke.getKeyStroke(extendedKeyCodeForCharA, KeyEvent.ALT_DOWN_MASK|KeyEvent.CTRL_DOWN_MASK));
		assertTrue(usedAltAcceleratorsFinder.findUsedKeyCodes(menuBar).isEmpty());
	}

	@Test
	public void returnsFoundAcceleratorIfMetaIsRequired() throws Exception {
		final int extendedKeyCodeForCharA = KeyEvent.getExtendedKeyCodeForChar('A');
		menuItem.setAccelerator(KeyStroke.getKeyStroke(extendedKeyCodeForCharA, KeyEvent.ALT_DOWN_MASK|KeyEvent.META_DOWN_MASK));
		assertTrue(usedAltAcceleratorsFinder.findUsedKeyCodes(menuBar).isEmpty());
	}
}
