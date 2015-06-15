(ns pij-to-clj.prime-numbers
  (:gen-class))

;; Write a program that asks a number from the user,
;; then says whether the number is prime or not.
;; Remember that a number that is divisible by any
;; number apart from 1 and itself is prime. You can
;; use the modulo operator (if a % b is zero, then
;; a is divisible by b).

(defn prime?
  [x]
  (or (<= x 3) (not-any? #(= (mod x %) 0) (range 2 (+ 1 (Math/sqrt x))))))

(println "Please input a number")
(let [number (read-string (read-line))]
  (println  (str number (if (prime? number) " is prime" " is not prime"))))
