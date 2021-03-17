(ns aoc-2020.day-08
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_08"))))

(defn get-acc-1
  [instructions]
  (loop [acc 0
         i 0
         visited #{}]
    (cond (contains? visited i) acc
          (= (count instructions) i) acc
          :else (let [task (subs (nth instructions i) 0 3)
                      num (Integer/parseInt (subs (nth instructions i) 4))]
                  (case task
                    "acc" (recur (+ acc num) (inc i) (conj visited i))
                    "jmp" (recur acc (+ i num) (conj visited i))
                    "nop" (recur acc (inc i) (conj visited i)))))))

(defn swap-tasks
  [line]
  (let [task (subs line 0 3)
        num (subs line 4)]
    (if (= task "jmp")
      (str "nop " num)
      (str "jmp " num))))

;; Part 1
(get-acc data)
