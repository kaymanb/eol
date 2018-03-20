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

(defn add-block-tiles
  "Adds a block of tiles to the map, from ix to sx and iy to sy."
  [tile, ix, iy, sx, sy, im]
  
  ;; loop over each x and y pair, adding a tile for each
  (loop [x ix y iy m im]
    (if (>= x sx)
      (recur ix (+ y 1) m)  
      (if (>= y sy)
        m
        (recur (+ x 1) y (add-tile tile x y m))))))

(defn measure-wall
  "Returns a starting position for a wall of length l"
  [l, max-l] (+ 1 (rand-int (- max-l l 2))))

(defn measure-room
  "Finds space for a random wxh room in the map with dimensions."
  [w, h, m]
  (let [max-w (count m)
        max-h (count (get m 1))]
    [(measure-wall w max-w) (measure-wall w max-h)]))

(defn carve-room
  "Adds a room to the input map and returns the new one."
  [w, h, m]
  (let [[x y] (measure-room w h m)]
    (add-block-tiles (create-space) x y (+ x w) (+ y h) m)))

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
     (carve-room 10 10 (initial-map length height))
    ]
  }))

(defn handle-input
  "Returns a new game state based on input."
  [state, input]
    (assoc state :msg (str "You just pressed: " input)))

(defn game-loop
  "Main loop for the game."
  [screen, state]
  (loop [s state]
    (draw-game screen s) 
    (l/refresh screen)
    (recur (handle-input s (l/read-input screen)))))

