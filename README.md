Welcome to Group 19's project:  
## Nazareth: Kiki's Search for Za'atar.

##### We hope you enjoy the game we have presented so far.

##### Within the game Kiki will venture through treacherous dungeons in search of ancient Za'atar.  
##### Throughout these levels Kiki will overcome tough obstacles in search of her final reward .

##### Enjoy our latest [trailer](https://youtu.be/q4PsC01-DUM)

### Follow these instructions
 
 

1. ***INSTRUCTIONS TO BUILD GAME***

	A - Clone repository from GitLab

	B - If Maven is not installed, please refer to https://maven.apache.org/install.html

	C - If  Maven has been installed, while in file directory "Documents" of the game,using command prompt or terminal, enter command:  
	   *mvn clean install*
	
	D - Required dependencies will be installed.


2. ***INSTRUCTIONS TO RUN GAME***

	A - Be sure to complete instruction set 1

	B -  While in "Documents" file directory of game,using command prompt or terminal, set environmental variable MAVEN_OPTS with command:  
	   *On Windows : set MAVEN_OPTS="-Djava.library.path=target/natives"*   
	   *On MacOS : export MAVEN_OPTS=-Djava.library.path=target/natives*
	
	C - While still in "Documents" file directory of game,using command prompt or terminal, enter command:  
	   *mvn compile exec:java -Dexec.mainClass=RunGame*


3.  ***INSTRUCTIONS TO TEST GAME***

	A - Making sure instruction set 1 has been completed

	B - While in "Documents" file directory of game, using command prompt or terminal, enter command:    
	    *mvn clean test*	

	C - Wait for results of test


4.  ***INSTRUCTIONS TO BUILD JAR GAME*** 

	A- Complete instruction set 1

	B- While in "Documents" file directory of game, using command prompt or terminal, enter command:      
	   *mvn clean package*

	C- Next, while still in the "Documents" file directory of game, using command prompt or terminal, enter command:  
	   *java -Djava.library.path=target/natives -jar target/NAZARETH-1.2-SNAPSJOT.jar*

	D- Enjoy the game!
	
	
