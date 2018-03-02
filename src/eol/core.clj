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
  [screen]
  (let [dims (ui/ui-dimensions)]
    (l/put-string "Hey!" 0 0 screen)
    (l/refresh screen)))

(defn print-title
  "Prints a title to the screen."
  [title, screen]
  (let [[cols rows] (l/get-dimensions screen)
        start-x (quot (- cols (count title)) 2)
        start-y (quot rows 2)]
    (l/put-string title start-x start-y screen)))


(defn new-game
  "Setup and start a new game"
  []
  (let [screen (l/create-screen)]
    (l/in-screen screen 
      (print-title "Evolution of Light" screen)
      (l/refresh screen)
      (l/read-input screen))))

(defn -main
  "Executeded by 'boot start'."
  [& args]
  (new-game))

(defn something
  "I do something!"
  []
  (+ 1 0))
