# Connect4ServerClient
Connect 4 game for 2 players.
## Server
Server is application that control game, ask players for their moves and sent changes to players.
Application shows log table and game state.
## Client
Client is application for players that connects with server with localhost ip and port 6666.
App waits for player moves and response to server messages.
## ConnectionProviderFake
This class inherits from ConnectionProvider.
ConnectionProvider methods are converted into functions so that you can communicate with the server/client using the console.
(This pattern helps with debugging)
## Gameplay Screens

### Players connected
<p align="center">
  <img src="https://github.com/Foxtrot007-ai/Connect4ServerClient/blob/main/Screens/Screenshot_1.png"  >
</p> 

### First move
<p align="center">
  <img src="https://github.com/Foxtrot007-ai/Connect4ServerClient/blob/main/Screens/Screenshot_2.png"  >
</p> 

### Some game state
<p align="center">
  <img src="https://github.com/Foxtrot007-ai/Connect4ServerClient/blob/main/Screens/Screenshot_3.png"  >
</p> 

### End state
<p align="center">
  <img src="https://github.com/Foxtrot007-ai/Connect4ServerClient/blob/main/Screens/Screenshot_4.png"  >
</p> 
