(ns eol.game
  (:require [eol.lanterna-clj.core :as l])
  (:require [eol.draw :refer [draw-game]])
  (:gen-class))

(defn initial-state
  "Return initial game state"
  [] {:key \g})

(defn handle-input
  "Returns a new game state based on input."
  [state, input]
    (assoc state :key input))

(defn game-loop
  "Main loop for the game."
  [screen, state]
  (loop [s state]
    (let [new-state (handle-input state (l/read-input screen))]
      (l/clear screen)
      (draw-game screen new-state)
      (l/refresh screen)
      (recur new-state))))
