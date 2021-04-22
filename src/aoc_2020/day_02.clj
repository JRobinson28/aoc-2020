(ns aoc-2020.day-02
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_02"))))

(defn validate-one
  "Determines if a password is valid according to the constraints in part one"
  [line]
  (let [[r letter pw] (s/split line #" ")
        [r1 r2] (s/split r #"-")
        letter (first letter)
        pw-map (group-by identity (seq pw))
        char-count (count (pw-map letter))]
    (<= (Integer/parseInt r1) char-count (Integer/parseInt r2))))

(defn count-passwords-one
  "Returns the number of passwords that are valid in part one"
  [input]
  (count (filter validate-one input)))

(count-passwords-one data)

(defn validate-two
  "Determines if a password is valid according to the constraints in part two"
  [line]
  (let [[r letter pw] (s/split line #" ")
        [r1 r2] (s/split r #"-")
        letter (first letter)
        l1 (nth (seq pw) (dec (Integer/parseInt r1)))
        l2 (nth (seq pw) (dec (Integer/parseInt r2)))]
    (or (and (= l1 letter) (not= l2 letter))
        (and (= l2 letter) (not= l1 letter)))))

(defn count-passwords-two
  "Returns the number of passwords that are valid in part two"
  [input]
  (count (filter validate-two input)))

(count-passwords-two data)
