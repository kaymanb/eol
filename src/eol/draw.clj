(ns eol.draw
  (:require [eol.lanterna-clj.core :as l])
  (:require [eol.ui :as ui])
  (:gen-class))

(defn print-center
  "Prints to the horizontal center of the screen."
  [screen, s, y]
  (let [[cols rows] (l/get-dimensions screen)
        start-x (quot (- cols (count s)) 2)]
    (l/put-string screen s start-x y)))

(defn get-offset
  "Gets the offset x and y coordinates for a section."
  [section]
  (let [off-x (get-in section [:origin :x])
        off-y (get-in section [:origin :y])]
    [off-x off-y]))

(defn draw-sec
  "Draws a section of the UI according"
  [section, screen, c]
  (let [[off-x off-y] (get-offset section)]

    ;; Draw section to screen char by char
    (doseq [x (vec (range (get section :length)))
            y (vec (range (get section :height)))]
      (l/set-char screen c (+ x off-x) (+ y off-y)))))

(defn draw-game
  "Draw the state to the screen"
  [screen, state]
  (l/put-string screen (str "You pressed " (get state :key)) 0 0))

