(ns pij-to-clj.week-1.day-1.division
  (:gen-class))

;; Write a program that requests two numbers
;; from the user and then outputs the
;; quotient and the remainder, e.g. if the
;; user enters 7 and 3, your program should
;; ouput something like “7 divided by 3 is
;; 2, remainder 1”. You cannot use the “/”
;; or “%” operators.

(defn get-input
  []
  (println "please input a number")
  (def a (read-string (read-line)))
  (println "please input another number")
  (def b (read-string (read-line)))
  (list a b))

(defn divided
  [a b]
  (->> (iterate #(- % b) a)
       (take-while #(>= % 0))
       (reverse)))

(defn pprint
  [[h & tl]]
  (println (str a " divided by " b " is "
                (count tl) ", remainder " h)))

;; (pprint (let [[a b] (get-input)]
          ;; (divided a b)))
