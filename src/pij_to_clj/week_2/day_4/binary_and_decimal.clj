(ns pij-to-clj.week-2.day-4.binary-and-decimal
  (:gen-class))

;; Create a program in which you define the following methods:
;;
;; binary2decimal(String): takes from the user a binary number
;; (with digits 0 and 1) and returns the corresponding number
;; in decimal (base-10, with digits between 0 and 9). Hint: in
;; the same way that you know that 35 = 3 · 10^1 + 5 · 10^0, you
;; can find that 100011 = 1 · 2^5 + 1 · 2^1 + 1 · 2^0.
;;
;; decimal2binary(int): the opposite of the previous one: takes
;; a decimal number and returns the corresponding binary number.
;; Hint: instead of multiplying by 2, you will need to divide
;; by two this time.
;;
;; The program must offer a menu to the user with two options.
;; The first one takes a binary number from the user and
;; returns the corresponding decimal number. The second one
;; does the opposite: takes a decimal number and returns a
;; binary number. The program should use the methods defined.

(defn binary2decimal
  "takes from the user a binary number (with digits 0 and 1)
   and returns the corresponding number in decimal (base-10,
   with digits between 0 and 9)"
  [binary]
  (reduce + (map #(if (= \1 %1) (int (Math/pow 2 %2)) 0)
                 binary (iterate dec (- (count binary) 1)))))

(defn decimal2binary
 "takes a decimal number and returns the corresponding
  binary number."
 [decimal]
 (->> (iterate #(int (/ % 2)) decimal)
      (take-while #(> % 0))
      (map #(if (even? %) 0 1))
      (reverse)
      (reduce str)))

(defn get-input
  [message]
  (do (print message) (flush) (read-line)))

(defn get-binary
  []
  (let [b (get-input "Please input a binary number: ")]
    (println (str "The decimal version is " (binary2decimal b)))))

(defn get-decimal
  []
  (let [i (read-string (get-input "Please input an integer: "))]
    (println (str "The binary version is " (decimal2binary i)))))

(defn menu
  []
  (let [decision (read-string (get-input "Input 1 for binary conversionor 2 for integer conversion: "))]
    (cond
      (= decision 1) (get-binary)
      (= decision 2) (get-decimal)
      :else (do (println "invalid choice") (recur)))))
