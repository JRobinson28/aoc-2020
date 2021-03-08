(ns aoc-2020.day-03-p2
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (vec (line-seq (io/reader (io/resource "day_03")))))

(def height (count data))

(def width (count (first data)))

(defn coords
  "Returns coordinates passed for given x and y increments"
  [dx dy]
  (for [i (range)
        :let [y (* dy i)
              x (* dx i)]
        :while (< y height)]
    [y (mod x width)]))

(defn count-trees
  "Counts number of trees hit in dataset"
  [input dx dy]
  (->> (coords dx dy)
       (filter #(= (get-in input %) \#))
       count))

;; Part 1
(count-trees data 3 1)

;; Part 2
(def slopes [[1 1] [3 1] [5 1] [7 1] [1 2]])

(reduce * (map #(apply count-trees data %) slopes))
