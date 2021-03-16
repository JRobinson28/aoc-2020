(ns aoc-2020.day-07
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_07"))))

(defn bag-types
  "Converts string of bag contents into a map"
  [input]
  (let [words (re-seq #"\w+" input)]
    {(str (second words) " " (nth words 2)) (first words)}))

(defn parse-rules
  "Derives a map of parent and child bags for a line in the input file"
  [input]
  (let [[outer contents] (s/split input #" bags contain ")]
    (if (= contents "no other bags.")
      {outer {}}
      (if (> (count (s/split contents #" ")) 4)
        (let [[& conts] (s/split contents #", ")
              inner-map (reduce merge {} (map bag-types conts))]
          {outer inner-map})
        {outer (bag-types contents)}))))

(def bag-map (reduce merge {} (map parse-rules data)))

(defn contains-shinygold?
  "Checks if a bag type will eventually contain a shiny gold bag"
  [bag]
  (let [contents (keys (bag-map bag))]
    (if (nil? contents)
      false
      (if (some  #(= "shiny gold" %) contents)
        true
        (some #(= true %) (map contains-shinygold? contents))))))

(defn count-inner-bags
  "Counts the total number of innner bags for a given bag type"
  [bag-type]
  (let [contents (bag-map bag-type)]
    (if (empty? contents)
      0
      (->> (map (fn [[child-bag n]]
                  (* (Integer/parseInt n) (inc (count-inner-bags child-bag))))
                contents)
           (reduce +)))))

;; Task 1
(->> (keys bag-map)
     (map contains-shinygold?)
     (filter identity)
     count)

;; Task 2
(count-inner-bags "shiny gold")
