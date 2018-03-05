(ns eol.core
  (:require [eol.lanterna-clj.core :as l])
  (:require [eol.draw :refer [print-center]])
  (:require [eol.game :refer [game-loop, initial-state]])
  (:gen-class))

(defn print-title
  "Prints a title to the screen."
  [screen, title & subtitles]
  (let [[cols rows] (l/get-dimensions screen)
        start-y (- (quot rows 2) (quot (count subtitles) 2))]
    (print-center screen title start-y)
    (doseq [[idx sub] (map-indexed vector subtitles)]
      (print-center screen sub (+ start-y 2 idx)))))

(defn new-game
  "Setup and start a new game"
  []
  (let [screen (l/create-screen)]
    (l/in-screen screen 
      (print-title screen 
                   "The Evolution of Light" 
                   "A Roguelike Adventure Game"
                   "Press any key to continue...")
      (l/refresh screen)
      (l/read-input screen)
      (l/clear screen)
      (l/refresh screen)
      (game-loop screen (initial-state)))))

(defn -main
  "Executeded by 'boot start'."
  [& args]
  (new-game))

