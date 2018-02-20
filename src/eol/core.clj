(ns eol.core
  (:import com.googlecode.lanterna.TextCharacter)
  (:require [eol.lanterna-clj.core :as l])
  (:gen-class))

(defn new-game
  "Setup and start a new game"
  []
  (let [screen (l/create-screen)]
    (l/in-screen screen 
      (let [[rows cols] (l/get-dimensions screen)]
        (.setCharacter screen (quot rows 2) (quot cols 2) (TextCharacter. \%))
        (.refresh screen)
        (Thread/sleep 5000)))))

(defn -main
  "Executeded by 'boot start'."
  [& args]
  (new-game))

(defn something
  "I do something!"
  []
  (+ 1 0))
