(ns aoc-2020.day-04
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (line-seq (io/reader (io/resource "day_04"))))

(def required-fields-1 ["byr"
                        "iyr"
                        "eyr"
                        "hgt"
                        "hcl"
                        "ecl"
                        "pid"])

(def required-fields-2 [#"byr:(19[2-9][0-9]|200[0-2])"
                        #"iyr:(201[0-9]|2020)"
                        #"eyr:(202[0-9]|2030)"
                        #"hgt:(1(5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-3])cm|(59|6[0-9]|7[0-6])in)"
                        #"hcl:#([0-9|a-f]{6})"
                        #"ecl:(amb|blu|brn|gry|grn|hzl|oth)"
                        #"pid:([0-9]{9})($| )"])

(defn validate-1
  "Checks if a single passport contains all of the required fields for part 1."
  [passport]
  (every? true? (map #(s/includes? passport %) required-fields)))

(defn validate-2
  "Checks if a single passport contains all of the required fields for part 2"
  [passport]
  (= (count required-fields-2)
     (->> (map #(re-seq % passport) required-fields-2)
          (map some?)
          (filter identity)
          count))
  )

(defn split-file
  "Traverses input file and returns collection of formatted passports"
  [input]
  (remove empty? (map #(s/join " " %)(partition-by empty? input))))

(defn check-passports
  "Counts number of valid passports in input file"
  [input validator]
  (count (filter identity (map #(validator %) input))))

;; Part 1
(check-passports (split-file data) validate-1)

;; Part 2
(check-passports (split-file data) validate-2)
