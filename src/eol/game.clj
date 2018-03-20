(ns eol.game
  (:require [eol.lanterna-clj.core :as l])
  (:require [eol.draw :refer [draw-game]])
  (:require [eol.ui :refer [game-dimensions]])
  (:gen-class))

(defn create-space
  "Returns the state for an empty space."
  []
  {
    :sprite \.
    :desc "space"
  })

(defn create-wall
  "Returns the state for a wall."
  [] 
  { 
    :sprite \#
    :desc "wall"
  })

(defn add-tile
  "Adds the input tiles to the map at each coordinate."
  [tile, x, y, m]
  (assoc m x
    (assoc (get m x) y tile)))

(defn add-room
  "Adds a room to the input map."
  [m]
  ("stub"))

(defn initial-map
  "Returns the tileset for the inital level."
  [length, height]
  (vec (repeat length
    (vec (repeat height (create-wall))))))

(defn initial-state
  "Return initial game state."
  [] 
  (let [dims (game-dimensions)
        length (get dims :length)
        height (get dims :height)]
  {
    :msg "You emerge in a dark and spooky dungeon..."
    :curr-level 0
    :levels [
     (initial-map length height) 
    ]
  }))

(defn handle-input
  "Returns a new game state based on input."
  [state, input]
    (assoc state :msg (str "You just pressed: " input)))

(defn game-loop
  "Main loop for the game."
  [screen, state]
  ;; Remember state is an atom
  (loop [s state]
    (draw-game screen s) 
    (l/refresh screen)
    (recur (handle-input s (l/read-input screen)))))
