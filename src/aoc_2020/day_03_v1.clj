(ns aoc-2020.day-03
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_03"))))

(def height (count data))

(def dx 3)

(def x (take (dec height) (iterate #(+ dx %) 0)))

(def steps (inc (quot height dx)))

(defn line-repeat
  "Repeats sequence on current line"
  [line]
  (apply str (repeat steps line)))

(defn move-toboggan
  "Moves the toboggan dx squares across the current line and determines if a tree was hit"
  [line start]
  (= (nth (seq (line-repeat line)) (+ dx start)) \#))


(defn count-trees-one
  "Counts the amount of trees hit in entire descent"
  [input]
  (count (remove false? (map move-toboggan (rest input) x))))

(count-trees-one data)
