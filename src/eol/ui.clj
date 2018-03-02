(ns eol.ui
  (:gen-class))

(defn game-dimensions
  "Return an object with the game ui dimensions."
  []
  {
    :height 20
    :length 80
    :origin { :x 0 :y 0 }
  })

(defn stats-dimensions
  "Return an object with the stats ui dimensions."
  []
  {
    :height 30
    :length 20
    :origin { :x 80 :y 0 }
  })


(defn msg-dimensions
  "Return an object with the message ui dimensions."
  []
  {
    :height 10
    :length 100
    :origin { :x 0 :y 20 }
  })


(defn ui-dimensions
  "Return an object describing the dimensions of the UI."
  []
  {
    :game (game-dimensions)
    :stats (stats-dimensions)
    :msg (msg-dimensions)
  })
