(ns eol.lanterna-clj.core
  (:import com.googlecode.lanterna.terminal.DefaultTerminalFactory)
  (:import com.googlecode.lanterna.screen.TerminalScreen)
  (:import com.googlecode.lanterna.TextCharacter)
  (:gen-class))

;; Terminal
(defn create-terminal
  "Create a new text terminal."
  []
  (-> (DefaultTerminalFactory.)
      (.setForceTextTerminal true)
      .createTerminal))

;; Screen
(defn refresh 
  "Refreshes the screen."
  [screen] (.refresh screen))

(defn create-screen
  "Create a new screen."
  ([] (create-screen (create-terminal)))
  ([terminal] (TerminalScreen. terminal)))

(defn get-dimensions
  "Return the dimensions of the screen as [cols rows]."
  [screen]
  (let [dims (.getTerminalSize screen)
        cols (.getColumns dims)
        rows (.getRows dims)]
    [cols rows]))

(defn put-string
  "Prints a string starting from given coordinates"
  [screen s x y]
  (-> (.newTextGraphics screen)
      (.putString x y s)))

(defn set-char
  "Set a character at the given coordinates."
  [screen c x y] 
  (.setCharacter screen x y (TextCharacter. c)))

(defn clear
  "Clears the screen of all characters."
  [screen]
  (.clear screen))

(defmacro in-screen
  "Start the screen and perform the body, then close."
  [screen & body]
  `(let [screen# ~screen]
    (.startScreen screen#)
    (.setCursorPosition screen# ~nil)
    (try ~@body
      (finally (.stopScreen screen#)))))

;; Input
(defn read-input
  "Block until keystroke on input screen and return the key pressed."
  [screen]
  (.getCharacter (.readInput screen)))

