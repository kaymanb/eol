(ns eol.game-test
  (:require [clojure.test :refer :all]
            [eol.game :refer :all]))

(deftest game-test
  (testing "Game logic"

    (testing "initial-level"
      (let [c (get (-> (initial-level 1 1) (peek) (peek)) :char)]
      (is (= c \#))))
        
    
    (testing "handle-input"
      ;; Stub for now...
      (is (= 1 1)))

    (testing "initial-state"
      ;; Stub for now...
      (is (= 1 1)))))
