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
(defn create-screen
  "Create a new screen."
  ([] (create-screen (create-terminal)))
  ([terminal] (TerminalScreen. terminal)))

(defn get-dimensions
  "Return the dimensions of the screen as [rows cols]."
  [screen]
  (let [dims (.getTerminalSize screen)
        cols (.getColumns dims)
        rows (.getRows dims)]
    [cols rows]))

(defn set-char
  "Set a character at the given row and column."
  [screen row col c] 
  (.setCharacter screen row col (TextCharacter. c)))

(defmacro in-screen
  "Start the screen and perform the body, then close."
  [screen & body]
  `(let [screen# ~screen]
    (.startScreen screen#)
    (.setCursorPosition screen# ~nil)
    (try ~@body
      (finally (.stopScreen screen#)))))

;; Input
(defn get-input
  "Block until keystroke on input screen and return."
  [screen]
  (.readInput screen))

