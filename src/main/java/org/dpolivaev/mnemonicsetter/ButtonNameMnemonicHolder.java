package org.dpolivaev.mnemonicsetter;

import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;

public class ButtonNameMnemonicHolder implements INameMnemonicHolder {
	final private AbstractButton btn;

	public ButtonNameMnemonicHolder(final AbstractButton btn) {
		super();
		this.btn = btn;
	}

	public String getText() {
		return btn.getText();
	}

	public void setDisplayedMnemonicIndex(final int mnemoSignIndex) {
		btn.setDisplayedMnemonicIndex(mnemoSignIndex);
	}

	public void setMnemonic(final char charAfterMnemoSign) {
		final int keyCode = KeyEvent.getExtendedKeyCodeForChar(charAfterMnemoSign);
		setMnemonic(keyCode);
	}

	public void setMnemonic(final int keyCode) {
		btn.setMnemonic(keyCode);
	}

	public void setText(final String text) {
		btn.setText(text);
	}

	@Override
	public int getMnemonic() {
		return btn.getMnemonic();
	}

	@Override
	public String toString() {
		final int mnemonic = getMnemonic();
		final String text = getText();
		return "ButtonNameMnemonicHolder [getText()=" + text + (mnemonic != 0 ? ", getMnemonic()=" + KeyEvent.getKeyText(mnemonic) + "]" : "");
	}

	@Override
	public boolean hasAccelerator() {
		return false;
	}
	
	
}