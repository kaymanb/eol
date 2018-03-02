(ns eol.ui-test
  (:require [clojure.test :refer :all]
            [eol.ui :refer :all]))

(deftest ui-test
  (testing "UI dimensions"
    
    (testing "Game dimensions"
      (is (= (get (game-dimensions) :height) 20))
      (is (= (get (game-dimensions) :length) 80)))

    (testing "Stats dimensions"
      (is (= (get (stats-dimensions) :height) 30))
      (is (= (get (stats-dimensions) :length) 20)))

    (testing "Message dimensions"
      (is (= (get (msg-dimensions) :height) 10))
      (is (= (get (msg-dimensions) :length) 100)))

    (testing "Overall UI dimensions"
      (let [dims (ui-dimensions)]
        (is (= (get-in dims [:game :height]) 20))
        (is (= (get-in dims [:stats :height]) 30))))))
      
    

