(ns aoc-2020.day-06
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_06"))))

(defn split-file
  "Traverses input file and returns collection of formatted groups"
  [input]
  (remove empty? (map #(s/join " " %)(partition-by empty? input))))

(defn count-group-1
  "Returns the number of unique yes answers for a given group"
  [group]
  (count (disj (set group) \space)))

(defn count-group-2
  "Returns the number of questions everyone answered yes to in a given group"
  [group]
  (let [f-map (dissoc (frequencies (s/join group)) \space)
        responses (count (s/split group #" "))]
    (->> (map #(= responses %) (vals f-map))
         (filter identity)
         count)))

;; Part 1
(reduce + (map count-group-1 (split-file data)))

;; Part 2
(reduce + (map count-group-2 (split-file data)))
