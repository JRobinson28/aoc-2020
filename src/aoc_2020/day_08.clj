(ns aoc-2020.day-08
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_08"))))

(defn get-acc
  "Returns the final value of acc for a given instruction set."
  [instructions]
  (loop [acc 0
         i 0
         visited #{}]
    (cond (contains? visited i) (str acc)
          (= (count instructions) i) (str "end " acc)
          :else (let [task (subs (nth instructions i) 0 3)
                      num (Integer/parseInt (subs (nth instructions i) 4))]
                  (case task
                    "acc" (recur (+ acc num) (inc i) (conj visited i))
                    "jmp" (recur acc (+ i num) (conj visited i))
                    "nop" (recur acc (inc i) (conj visited i)))))))

(defn get-variations
  "Returns every possible permutation of swapping nop and jmp for a given instruction set."
  [instructions]
  (let [length   (count instructions)
        swaps {"acc" "acc"
               "nop" "jmp"
               "jmp" "nop"}]
    (loop [i 0
           variations ()]
      (cond (= i length)
            (reverse variations)
        :else (let [task (subs (nth instructions i) 0 3)
                    num (Integer/parseInt (subs (nth instructions i) 4))]
                (recur (inc i)
                       (cons (assoc (vec instructions) i (str (swaps task) " " num)) variations)))))))

;; Part 1
(get-acc-1 data)

;; Part 2
(->> data
     get-variations
     (map get-acc)
     (filter #(= (subs % 0 1) "e")))
