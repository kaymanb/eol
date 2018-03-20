(ns eol.game-test
  (:require [clojure.test :refer :all]
            [eol.game :refer :all]))

(defn create-map
  [w h]
  (vec (repeat w (vec (repeat h (create-wall))))))

(deftest game-test
  (testing "Game logic"

    (testing "initial-map"
      (let [c (get (-> (initial-map 1 1) (peek) (peek)) :sprite)]
      (is (= c \#))))

    (testing "add-tile"
      (let [m (vec (repeat 5 (vec (repeat 5 (create-wall)))))
            new-m (add-tile (create-space) 0 0 m)
            new-tile (get-in new-m [0 0])]
        (is (= (get new-tile :desc) "space"))))

    (testing "add-block-tiles"
      (let [m (vec (repeat 5 (vec (repeat 5 (create-wall)))))
            new-m (add-block-tiles (create-space) 0 0 3 3 m)
            new-tile (get-in new-m [2 2])]
        (is (= (get new-tile :desc) "space"))))
            
    (testing "measure-wall"
      (let [new-l (measure-wall 8 10)]
        (is (= new-l 1))))

    (testing "measure-room"
      (let [m (create-map 5 5)
            [x y] (measure-room 3 3 m)]
        (is (= x 1))
        (is (= y 1))))

    (testing "handle-input"
      ;; Stub for now...
      (is (= 1 1)))

    (testing "initial-state"
      ;; Stub for now...
      (is (= 1 1)))))
