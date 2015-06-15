(ns pij-to-clj.week-1.day-1.going-up
  (:gen-class))

;; Read an arbitrarily long sequence of positive numbers
;; from the user, until -1 is entered. At that point,
;; print ”Yes” if the numbers were consecutive and
;; increasing and ”No” otherwise. Sequences ”1,2,3,4,-1”
;; and ”5,6,7,8,9,10,11,- 1” should output ”Yes”, but
;; ”2,3,5,6,7,-1”, ”10,9,8,7,-1”, and ”1,1,2,3,4,5,-1”
;; should output ”No”.

(defn get-input
  []
  (do (print "input positive numbers, -1 to stop ")
      (flush)
      (read-string (read-line))))

(defn consecutive-asc?
  [[h & tl]]
  (or (not tl)
      (and (= 1 (- (first tl) h))
           (recur tl))))

(def list (take-while #(not (= % -1)) (repeatedly get-input)))

(if (consecutive-asc? list)
  (println "Yes")
  (println "No"))
