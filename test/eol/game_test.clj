(ns eol.game-test
  (:require [clojure.test :refer :all]
            [eol.game :refer :all]))

(deftest game-test
  (testing "Game logic"

    (testing "initial-map"
      (let [c (get (-> (initial-map 1 1) (peek) (peek)) :sprite)]
      (is (= c \#))))

    (testing "add-tile"
      (let [m (vec (repeat 5 (vec (repeat 5 (create-wall)))))
            new-m (add-tile (create-space) 0 0 m)
            new-tile (get (get new-m 0) 0)]
        (is (= (get new-tile :desc) "space"))))

    (testing "handle-input"
      ;; Stub for now...
      (is (= 1 1)))

    (testing "initial-state"
      ;; Stub for now...
      (is (= 1 1)))))
