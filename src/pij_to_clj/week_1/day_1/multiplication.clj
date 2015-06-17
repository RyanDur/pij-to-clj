(ns pij-to-clj.week-1.day-1.multiplication
  (:gen-class))

;; Write a program that requests two numbers from
;; the user and then outputs its product. You
;; cannot use the “*” operator.

(defn get-input
  []
  (println "please enter a number")
  (def a (read-string (read-line)))
  (println "please enter another number")
  (def b (read-string (read-line)))
  (list a b))

(defn multiply
  [a b]
  (reduce + (repeat b a)))

;; (println (let [[a b] (get-input)]
           ;; (multiply a b)))
