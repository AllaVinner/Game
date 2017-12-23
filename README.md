# Game

## Four in a row
  The classic game where you want to connect 4 of your coins before your opponent does.
  This game comes with 2 options:
    1. Play against a friend. Found in the "GamePlay.java" class.
    2. Play agains a kind of god computer. Found in "GamePlay_AI.java" class.
      Despite the name the computer is not built on AI (I wish). 
      Instead I tried to come up with some sort of tactic that I could program.
      So now how it works is that it creates an array of all the possible ways to win.
      A possible way to win is a set of four cells that if all contains one kind of coin that kind winns.
      Then when the game starts it updates this list and the game is kind of transformed into a kind of capture the flag where each player tries to get as many possible wins as possible.
      Then there is a pollynomial weight function which makes a win filled with 1 coin less worth the one filled with 3 coins.
      After that, the computer test all 7 choices and pic the one with the best sum of all the weights.
 
 The user interface is horrible and you have to go into either "GamePlay" or "GamePlay_AI" to choose wether or not you want to play agains the computer.
 Hope I fix this one day.
 
 ## Snake
  The fantastic game Snake. Its venom is addictive...
  
  ## Space invader
    This sucks...
