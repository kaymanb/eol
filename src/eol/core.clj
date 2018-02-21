(ns eol.core
  (:require [eol.lanterna-clj.core :as l])
  (:gen-class))

(defn create-state
  "Return a blank game state"
  [] {:dims {:rows 80 :cols 20}})

(defn new-game
  "Setup and start a new game"
  []
  (let [screen (l/create-screen)]
    (l/in-screen screen 
      (let [state (create-state)
            dims (get state :dims)]
        (doseq [x (vec (range (get dims :rows)))
                y (vec (range (get dims :cols)))]
          (l/set-char screen x y \#))
        (.refresh screen)
        (l/get-input screen)))))

(defn -main
  "Executeded by 'boot start'."
  [& args]
  (new-game))

(defn something
  "I do something!"
  []
  (+ 1 0))
