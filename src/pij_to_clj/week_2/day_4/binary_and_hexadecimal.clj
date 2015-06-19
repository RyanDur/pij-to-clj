(ns pij-to-clj.week-2.day-4.binary-and-hexadecimal
  (:gen-class))

;; Binary numbers can be quite long. A 32-bit memory address
;; looks like 1001 0101 0110 1010 1011 0010 1001 1010, which
;; is difficult to handle. That is why memory addresses and
;; other binary numbers are usually written as hexadecimal
;; numbers. A hexadecimal number is a base-16 number, with
;; digits between 0 and f (f is equivalent to decimal 15, e
;; is equivalent to decimal 14, and so on). A hexadecimal
;; number is equivalent to a four-digit binary number, which
;; makes them quite compact. The former address reads 956ab29a,
;; which is easier to read and write. To prevent confusion
;; with decimal numbers, hexadecimal numbers are usually
;; prefixed by “0x”, as in 0x95 (which is 149) or 0xff
;; (which is 255).
;;
;; Write a program that takes a String. The string can be a
;; decimal or a hexadecimal number (starting by “0x”). Your
;; program must recognise the number as what it is and convert
;; it to the other base. Use two methods for conversion as in
;; the former exercise.

(def hex [0 1 2 3 4 5 6 7 8 9 'A 'B 'C 'D 'E 'F])

(defn hex2dec
  [hexadecimal]
  (reduce + (map #(* (.indexOf hex %) (int (Math/pow 16 %2)))
                 (map read-string (map str hexadecimal))
                 (iterate dec (- (count hexadecimal) 1)))))

(defn dec2hex
  [decimal]
  (let [integer (read-string decimal)]
    (->> (iterate #(int (/ % 16)) integer)
         (take-while #(> % 0))
         (map #(hex (mod % 16)))
         (reverse)
         (cons "0x")
         (reduce str))))

(defn get-input
  [message]
  (do (print message) (flush) (read-line)))

(defn menu
  []
  (let [input (get-input "Input an integer or hexadecimal: ")]
    (let [[_ in] (re-matches #"^0x(.*)" input)]
      (if in (println (str "the decimal is " (hex2dec in)))
          (println (str "the hex is " (dec2hex input)))))))
