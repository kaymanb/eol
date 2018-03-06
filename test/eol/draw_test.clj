(ns eol.draw-test
  (:require [clojure.test :refer :all]
            [eol.draw :refer :all]))

(deftest draw-test
  (testing "Drawing to screen"

    (testing "get-offset"
      (is (= (get-offset { :origin {:x 20 :y 20}}) [20, 20])))))
