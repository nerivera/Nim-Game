# Nim-Game

VIEW AT https://Nim.electricshadow.repl.run

This is a simple text game played on the Java console where the user and the computer compete to see who can force the other to take the last stone.
Each game randomly starts with 15-30 stones. On a turn, one can choose to take 1-3 stones from the pile. The person who takes the last stone loses.
The strategy is to repeatedly play so that the number of stones your opponent has is one more than a multiple of four (this is how the computer plays on hard difficulty). That way, you can take four minus the number of stones your opponent took on each turn, causing them to land on one stone by the end of the game.

Made by Nilo E Rivera (@nerivera) July 2018
