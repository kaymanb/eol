(ns eol.core
  (:require [eol.lanterna-clj.core :as l])
  (:require [eol.ui :as ui])
  (:gen-class))

(defn get-offset
  "Gets the offset x and y coordinates for a section."
  [section]
  (let [off-x (get-in section [:origin :x])
        off-y (get-in section [:origin :y])]
    [off-x off-y]))
  
(defn draw-sec
  "Draws a section of the UI according"
  [section, screen, c]
  (let [off-x (get-in section [:origin :x])
        off-y (get-in section [:origin :y])]

    ;; Draw section to screen char by char
    (doseq [x (vec (range (get section :length)))
            y (vec (range (get section :height)))]
      (l/set-char c (+ x off-x) (+ y off-y) screen))))

(defn draw-game
  "Draw the state to the screen"
  [screen, state]
    (l/put-string screen (str "You pressed " (get state :key)) 0 0))

(defn print-title
  "Prints a title to the screen."
  [title, screen]
  (let [[cols rows] (l/get-dimensions screen)
        start-x (quot (- cols (count title)) 2)
        start-y (quot rows 2)]
    (l/put-string screen title start-x start-y)))

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

(defn new-game
  "Setup and start a new game"
  []
  (let [screen (l/create-screen)]
    (l/in-screen screen 
      (print-title "The Evolution of Light..." screen)
      (l/refresh screen)
      (l/read-input screen)
      (game-loop screen {:key \g}))))


(defn -main
  "Executeded by 'boot start'."
  [& args]
  (new-game))

