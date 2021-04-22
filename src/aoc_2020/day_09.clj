(ns aoc-2020.day-09
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (map #(Long/parseLong %)(line-seq (io/reader (io/resource "day_09")))))

(defn invalid-num?
  "Checks if a number is invalid, meaning it isn't the sum of 2 previous numbers."
  [nums target]
  (empty? (for [x nums
                y nums
                :when (and (= target (+ x y))
                           (< x y))]
            [x y])))

(defn get-invalid-num
  "Returns the invalid number in the given data set."
  [input]
  (loop [indexes (range 0 25)
         curr 25]
    (let [target (nth input curr)
          nums (map #(nth input %) indexes)]
      (if (invalid-num? nums target)
        target
        (recur (map inc indexes) (inc curr))))))

(defn get-sum-range
  "Returns the list of consecutive previous numbers that sum to the target number."
  [input]
  (let [target (get-invalid-num input)]
    (loop [nums input
           l 2]
      (let [parts (take l nums)
            sum (reduce + parts)]
        (cond
          (= sum target) parts
          (< sum target) (recur nums (inc l))
          :else (recur (rest nums) 2))))))

;; Part 1
(get-invalid-num data)

;; Part 2
(let [parts (get-sum-range data)]
  (+ (apply min parts) (apply max parts)))
