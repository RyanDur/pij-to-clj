(ns pij-to-clj.week-1.day-1.maximising
  (:gen-class))

;; Write a program that read a (arbitrarily long) sequence
;; of positive numbers. The sequence is ended when the
;; users enters “-1”. At that point, the program must output
;; the highest number in the sequence.

(defn get-input
  []
  (do (print "Input a number, enter -1 to stop ")
      (flush)
      (read-string (read-line))))

(defn highest
  []
  (apply max (take-while #(not (= % -1)) (repeatedly get-input))))

;; (highest)
