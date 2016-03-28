package org.dpolivaev.mnemonicsetter;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.dpolivaev.mnemonicsetter.ButtonNameMnemonicHolder;
import org.dpolivaev.mnemonicsetter.INameMnemonicHolder;
import org.dpolivaev.mnemonicsetter.MenuItemMnemonicHolder;
import org.dpolivaev.mnemonicsetter.MnemonicSetter;

public class MenuMnemonicSetter implements PopupMenuListener{
	final static private boolean IS_MAC_OS = System.getProperty("os.name").startsWith("Mac OS");
	final static public MenuMnemonicSetter INSTANCE = new MenuMnemonicSetter();
	

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		final Container popupMenu = (Container) e.getSource();
		setComponentMnemonics(popupMenu);
	}

	public void setComponentMnemonics(final Container menu) {
		if(IS_MAC_OS)
			return; // Mac OS generally does not support mnemonics
		final Component[] components = menu.getComponents();
		final ArrayList<INameMnemonicHolder> mnemonicHolders = new ArrayList<INameMnemonicHolder>(components.length);
		for(Component component :components)
			if(component instanceof JMenuItem) {
				final JMenuItem item = (JMenuItem) component;
				mnemonicHolders.add(new MenuItemMnemonicHolder(item));
			}
			else if(component instanceof AbstractButton) {
				final AbstractButton button = (AbstractButton) component;
				mnemonicHolders.add(new ButtonNameMnemonicHolder(button));
			}
		final MnemonicSetter mnemonicSetter = MnemonicSetter.of(mnemonicHolders);
		mnemonicSetter.setMnemonics();
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
	}

}
