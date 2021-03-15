(ns aoc-2020.day-07
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_07_test"))))

(defn bag-types
  [input]
  (let [words (re-seq #"\w+" input)]
    {(str (second words) " " (nth words 2)) (first words)}))

(defn parse-rules
  [input]
  (let [[outer contents] (s/split input #" bags contain ")]
    (if (= contents "no other bags.")
      {outer {}}
      (if (> (count (s/split contents #" ")) 4)
        (let [[cont1 cont2] (s/split contents #", ")
              inner-map (merge (bag-types cont1) (bag-types cont2))]
          {outer inner-map})
        {outer (bag-types contents)}))))
