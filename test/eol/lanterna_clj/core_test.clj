(ns eol.lanterna-clj.core-test
  (:require [clojure.test :refer :all]
            [eol.lanterna-clj.core :refer :all]))

(deftest lanterna-clj-test
  (testing "Terminal wrappers"
    (is (some? (create-terminal))))

  (testing "Screen wrappers"
    (testing "create-screen"
      (is (some? (create-screen))))

    (testing "get-dimensions"
      (let [screen (create-screen)
            [rows cols] (get-dimensions screen)]
        (is (> rows 0))
        (is (> cols 0))))
  
    (testing "put-string"
      (let [screen (create-screen)]
        (put-string screen "Test" 0 0)
        (is (some? (.getBackCharacter screen 0 0)))))

    (testing "set-char"
      (let [screen (create-screen)]
        (set-char screen \% 0 0)
        (is (some? (.getBackCharacter screen 0 0)))))
    
    (testing "in-screen"
      (let [screen (create-screen)]
      (is (= 1 (in-screen screen (let [x 1] x)))))))

    (testing "refresh"
      (let [screen (create-screen)]
        (set-char screen \% 0 0)
        (refresh screen)
        (is (some? (.getFrontCharacter screen 0 0)))))

    (testing "clear"
      (let [screen (create-screen)]
        (set-char screen \f 0 0)
        (clear screen)
        (is (= " " (str (.getCharacter (.getBackCharacter screen 0 0))))))))
