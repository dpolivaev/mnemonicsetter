# mnemonicsetter
 Automatically assigns mnemonics to menu items and toolbar elements.

Use it just like `MnemonicSetter.INSTANCE.setComponentMnemonics(menubar, toolbar)`.
All items belonging to given elements are given different mnemonic letters if possible.

You can also attach `MnemonicSetter.INSTANCE` to a popup menu as a PopupMenuListener 
so that mnemonics are automatically calculated when the popup menu becomes visible.

### Maven configuration
```
<repository>
	<snapshots>
 		<enabled>false</enabled>
 	</snapshots>
	<id>bintray-freeplane-maven</id>
	<name>bintray</name>
	<url>https://dl.bintray.com/freeplane/freeplane</url>
</repository>


<dependency> 
	<groupId>org.dpolivaev.mnemonicsetter</groupId> 
	<artifactId>mnemonicsetter</artifactId> 
	<version>0.4</version> 
	<type>pom</type> 
</dependency>
```

### Gradle configuration
```
repositories {
		maven { url "http://dl.bintray.com/freeplane/freeplane" }
}

dependencies {		
	compile 'org.dpolivaev.mnemonicsetter:mnemonicsetter:0.4'
}	
```
