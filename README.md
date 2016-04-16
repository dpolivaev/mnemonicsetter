# mnemonicsetter
 Automatically assigns mnemonics to menu items and toolbar elements.

Use it just like MnemonicSetter.INSTANCE.setComponentMnemonics(menubar, toolbar).
All items belonging to given elements are given different mnemonic letters if possible.

You can also attach it to a popup menu as a PopupMenuListener 
so that mnemonics are automatically calculated when the popup menu becomes visible.
