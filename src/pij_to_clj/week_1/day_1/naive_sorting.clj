(ns pij-to-clj.week-1.day-1.naive-sorting
  (:gen-class))

;; Write a program that reads three numbers and prints
;; them in order, from lowest to highest.

(defn get-input
  []
  (println "please input a number")
  (def a (read-string (read-line)))
  (println "please input another number")
  (def b (read-string (read-line)))
  (println "please input one last number")
  (def c (read-string (read-line)))
  (list a b c))


(defn print-sorted
  [a b c]
  (if (< a b)
    (if (< a c)
      (if (< b c)
        (println (str a " " b " " c))
        (println (str a " " c " " b)))
      (println (str c " " a " " b)))
    (if (< b c)
      (if (< a c)
        (println (str b " " a " " c))
        (println (str b " " c " " a)))
      (println (str c " " b " " a)))))

;; (let [[a b c] (get-input)]
;;    (print-sorted a b c))

;; Permutations:
;; (1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1)
