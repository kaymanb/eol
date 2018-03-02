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
  [s x y screen]
  (-> (.newTextGraphics screen)
      (.putString x y s)))

(defn set-char
  "Set a character at the given coordinates."
  [c x y screen] 
  (.setCharacter screen x y (TextCharacter. c)))

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
  "Block until keystroke on input screen and return."
  [screen]
  (.readInput screen))

