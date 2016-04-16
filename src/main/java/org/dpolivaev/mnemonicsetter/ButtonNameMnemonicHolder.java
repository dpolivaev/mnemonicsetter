/*
 *  Copyright (C) 2016 Dimitry Polivaev
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.dpolivaev.mnemonicsetter;

import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;

class ButtonNameMnemonicHolder implements INameMnemonicHolder {
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