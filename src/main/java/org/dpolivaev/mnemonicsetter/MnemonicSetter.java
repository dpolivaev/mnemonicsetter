/*
   Copyright (C) 2016 Dimitry Polivaev

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.dpolivaev.mnemonicsetter;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.AbstractButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.dpolivaev.mnemonicsetter.ButtonNameMnemonicHolder;
import org.dpolivaev.mnemonicsetter.INameMnemonicHolder;
import org.dpolivaev.mnemonicsetter.MenuItemMnemonicHolder;
import org.dpolivaev.mnemonicsetter.ItemMnemonicSetter;

/**
 * Automatically assigns mnemonics to menu items and toolbar elements.
 * 
 * Use it just like MnemonicSetter.INSTANCE.setComponentMnemonics(menubar, toolbar).
 * You can also attach it to a popup menu as a PopupMenuListener 
 * so that mnemonics are automatically calculated when the popup menu becomes visible.
 */
public class MnemonicSetter implements PopupMenuListener{
	final static private boolean IS_MAC_OS = System.getProperty("os.name").startsWith("Mac OS");
	final static public MnemonicSetter INSTANCE = new MnemonicSetter();
	

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		final Container popupMenu = (Container) e.getSource();
		setComponentMnemonics(popupMenu);
	}

	public void setComponentMnemonics(final Container... containers) {
		if(IS_MAC_OS)
			return; // Mac OS generally does not support mnemonics
		int componentCount = 0;
		for(Container container : containers) {
			componentCount += container.getComponentCount();
		}
		final Collection<Integer> keyCodesUsedInMenus = new HashSet<>();
		final ArrayList<INameMnemonicHolder> mnemonicHolders = new ArrayList<INameMnemonicHolder>(componentCount);
		for(Container container : containers) {
			if(container instanceof JMenuBar) {
				final Collection<Integer> keyCodesUsedInMenu = UsedAltAcceleratorsFinder.INSTANCE.findUsedKeyCodes((JMenuBar) container);
				keyCodesUsedInMenus.addAll(keyCodesUsedInMenu);
			}
				
			final Component[] components = container.getComponents();
			for(Component component :components)
				if(component instanceof JMenuItem) {
					final JMenuItem item = (JMenuItem) component;
					mnemonicHolders.add(new MenuItemMnemonicHolder(item));
				}
				else if(component instanceof AbstractButton) {
					final AbstractButton button = (AbstractButton) component;
					mnemonicHolders.add(new ButtonNameMnemonicHolder(button));
				}
		}
		final ItemMnemonicSetter mnemonicSetter = ItemMnemonicSetter.of(mnemonicHolders).notUsing(keyCodesUsedInMenus);
		mnemonicSetter.setMnemonics();
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
	}

}
