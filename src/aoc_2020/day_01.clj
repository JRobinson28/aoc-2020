(ns aoc-2020.day-01
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (map #(Integer/parseInt %)(line-seq (io/reader (io/resource "day_01")))))

(defn check-sum-two
  "Loops through numbers and returns multiple of pair that sum to 2020"
  [numbers]
  (for [x numbers
        y numbers
        :when (< x y)
        :when (= 2020 (+ x y))]
    (* x y)))

(check-sum-two data)

(defn check-sum-three
  "Loops through numbers and returns multiple of trio that sum to 2020"
  [numbers]
  (for [x numbers
        y numbers
        z numbers
        :when (and (< x y) (< y z))
        :when (= 2020 (+ x y z))]
    (* x y z)))

(check-sum-three data)
