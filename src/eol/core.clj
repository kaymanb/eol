(ns eol.core
  (:require [eol.lanterna-clj.core :as l])
  (:require [eol.ui :as ui])
  (:gen-class))

(defn draw-sec
  "Draws a section of the UI according"
  [section, screen, c]
  (let [off-x (get-in section [:origin :x])
        off-y (get-in section [:origin :y])]

    ;; Draw section to screen char by char
    (doseq [x (vec (range (get section :length)))
            y (vec (range (get section :height)))]
      (l/set-char screen (+ x off-x) (+ y off-y) c))
    (l/refresh screen)))

(defn draw-game
  "Draw the state to the screen"
  [screen]
  (let [dims (ui/ui-dimensions)]
    (draw-sec (get dims :game) screen \#)))

(defn new-game
  "Setup and start a new game"
  []
  (let [screen (l/create-screen)]
    (l/in-screen screen 
      (draw-game screen)
      (l/get-input screen))))

(defn -main
  "Executeded by 'boot start'."
  [& args]
  (new-game))

(defn something
  "I do something!"
  []
  (+ 1 0))
