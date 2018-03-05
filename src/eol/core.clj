(ns eol.core
  (:require [eol.lanterna-clj.core :as l])
  (:require [eol.game :refer [game-loop]])
  (:gen-class))

(defn print-title
  "Prints a title to the screen."
  [title, screen]
  (let [[cols rows] (l/get-dimensions screen)
        start-x (quot (- cols (count title)) 2)
        start-y (quot rows 2)]
    (l/put-string screen title start-x start-y)))


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

