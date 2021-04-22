(ns aoc-2020.day-05
  (:require [clojure.java.io :as io]
            [clojure.set :as set]))

(def data (line-seq (io/reader (io/resource "day_05"))))

(defn convert
  "Takes input string and converts to binary then to decimal number"
  [input]
  (->> (map {\F 0
             \B 1
             \L 0
             \R 1} input)
       (apply str "2r")
       read-string))

(defn get-id
  "Gets the seat ID for a given boarding pass"
  [input]
  (let [row (convert (subs input 0 7))
        col (convert (subs input 7 10))
        id (+ col (* row 8))]
    id))

(defn get-my-seat
  "Returns single missing seat ID"
  [seats]
  (let [r (set (range (apply min seats) (apply max seats)))
        occupied (set seats)]
    (set/difference r occupied)))

(def ids (map get-id data))

;; Part 1
(apply max ids)

;; Part 2
(get-my-seat ids)
