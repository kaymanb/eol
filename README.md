# Evolution of Light

A Roguelike Adventure Game written in Clojure.

## Installation

Clone from https://github.com/kaymanb/eol

## Ideas

At this early stage, I'm mostly going to use the README for taking notes on the
progress of the game. Once things get finallized, these should move into Docs.

### How does the game work?

The game is a recursive loop. We store all the game state in a single object. We prompt
the user from an action, then return a new game state based on that action. The
new state is rendered to the screen, and the loop is called again with the new
state as input.

But are we really going to store everything in state? YES I think we should, at
least everything relevant to the game. How the state is rendered to the screen
is completely seperate (something like, where does the :msg go?), but what the
message IS should be in state. 

With this in mind, it would be cool for everything to be a function. Each
"thing" takes in a state and returns a new one. Walls do nothing. Monsters
might move or attack depending on where they are. Items might enchant other
items, or cast spells. 

How about when things have choice? For example, the player could move OR throw
an item. In fact, the player is the only thing with a choice, and he makes it
with a key press. Everything else should have everything it needs to decide
which new state to return given the current state. 

## Roadmap

- Update README.md
- Randomly generate rooms
- How to solve the inheritence problem
- Functions to clear message and stats section

## Other

I once planned on writting this game in Python. I didn't get very far, but what I did get done still exists under the name [evolution_of_light_python](https://github.com/kaymanb/evolution_of_light_python).

