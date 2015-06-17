(ns pij-to-clj.week-1.day-1.you-said-high-i-said-low
  (:gen-class))

;; Modify your former program so that it outputs ”Yes” when
;; the numbers are consecutive, regardless of whether they
;; go up or down. For example, both ”2,3,4,5,6,-1” and
;; ”10,9,8,7,-1” should now result in ”Yes”.

(defn get-input
  []
  (do (print "input positive numbers, -1 to stop ")
      (flush)
      (read-string (read-line))))

(defn abs
  [n]
  (max n (- n)))

(defn consecutive?
  [[h & tl]]
  (or (not tl)
      (and (= 1 (abs (- (first tl) h)))
           (recur tl))))

(defn get-list
  []
  (doall (take-while #(not (neg? %)) (repeatedly get-input))))

(defn print-consecutive?
  [list]
  (if (consecutive? list)
    (println "Yes")
    (println "No")))

;; (print-consecutive? (get-list))
