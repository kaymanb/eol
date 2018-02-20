(ns eol.lanterna-clj.core-test
  (:require [clojure.test :refer :all]
            [eol.lanterna-clj.core :refer :all]))

(deftest -test
  (testing "Terminal wrappers"
    (is (some? (create-terminal))))

  (testing "Screen wrappers"
    (testing "create-screen"
      (is (some? (create-screen)))))

    (testing "get-size"
      (let [screen (create-screen)
            [rows cols] (get-dimensions screen)]
        (is (> rows 0))
        (is (> cols 0))))
    
    (testing "in-screen"
      (let [screen (create-screen)]
      (is (= 1 (in-screen screen (let [x 1] x)))))))
