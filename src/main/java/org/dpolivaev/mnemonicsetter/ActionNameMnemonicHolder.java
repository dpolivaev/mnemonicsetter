package org.dpolivaev.mnemonicsetter;

import java.awt.event.KeyEvent;

import javax.swing.Action;

public class ActionNameMnemonicHolder implements INameMnemonicHolder {
	final private Action action;

	public ActionNameMnemonicHolder(final Action action) {
		super();
		this.action = action;
	}

	public String getText() {
		return (String) action.getValue(Action.NAME);
	}

	public void setDisplayedMnemonicIndex(final int mnemoSignIndex) {
		action.putValue("SwingDisplayedMnemonicIndexKey", mnemoSignIndex);

	}

	public void setMnemonic(final char charAfterMnemoSign) {
		final int keyCode = KeyEvent.getExtendedKeyCodeForChar((int) charAfterMnemoSign);
		setMnemonic(keyCode);
	}

	public void setMnemonic(final int keyCode) {
		action.putValue(Action.MNEMONIC_KEY,  keyCode);
	}

	public void setText(final String text) {
		action.putValue(Action.NAME, text);
	}

	@Override
	public int getMnemonic() {
		final Object mnemonic = action.getValue(Action.MNEMONIC_KEY);
		if(mnemonic instanceof Integer)
			return ((Integer)mnemonic).intValue();
		else
			return 0;
	}

	@Override
	public boolean hasAccelerator() {
		return action.getValue(Action.ACCELERATOR_KEY) != null;
	}
}