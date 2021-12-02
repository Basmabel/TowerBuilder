# TowerBuilder

The purpose of this program/game is to to stack several rectangles in order to build a tower that must be as high and stable as possible.

The game gives the possibility to resize the blocks constituting the tower, and also to change their color or density. It is also possible to change the speed of the block launcher 
to define the level of difficulty of the game.

As for the user Interface, a profile system was set up, where the user has the possibility to register using a real login interface with email verification (an external API was used: JavaMail).

The score and nickname will be stored in order to be able to perform a ranking of the users. To make this ranking as accurate as possible, a connection is required each time the program is opened. Two types of accounts exist: administrators and normal users. Admin accounts have the ability to ban users and reset their 
scores.

Moreover, regarding the scientific aspect of the code, several methods that calculate the equilibrium conditions of the tower were written, starting with finding the position of the barycenter of the construction, then comparing it to that of the new block to check the stability of the structure.

**** **Login to the game** ****


To connect to the Game you have two options:

- Create an account in the registration section to log in as a User.
- Use the Founder account which gives access to the Game as an administrator.

The coordinates of the Founder account are as follows:
Nickname: Founder
Password: root
