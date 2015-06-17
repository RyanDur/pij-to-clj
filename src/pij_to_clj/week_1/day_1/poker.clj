(ns pij-to-clj.week-1.day-1.poker
  (:gen-class)
  (:require [clojure.string :refer [split]]))

;; Read five cards from the user. For each card, read
;; the rank (1 2 3 4 5 6 7 8 9 10 'J 'Q 'K]) and the
;; suit ('spades 'hearts 'diamonds 'clubs). Each of
;; the five cards must be valid before accepting the
;; next one. Once the program has the five cards, it
;; should tell the user what is the best hand she has
;; got, as per the following list (from best to worst):
;;
;; Straigh flush: all cards are of the same suite and
;; their ranks are consecutive. Note that they are
;; probably not ordered as they were entered.
;;
;; Poker: four of the five cards have the same rank.
;;
;; Full House: three of a kind plus two of a kind.
;;
;; Flush: all cards share the same suit, but are not
;; consecutive.
;;
;; Straight: all cards are consecutive,
;; but not of the same suit.
;;
;; Three of a kind: three of the five cards have the
;; same rank.
;;
;; Two pairs: two pairs (see below).
;;
;; Pair: two of the five cards have the same rank.
;;
;; Nothing: any other situation.
;;
;; If you ever launch an online poker business, this
;; could be one (very small) piece of it.
(def ranks [1 2 3 4 5 6 7 8 9 10 'J 'Q 'K])
(def suits ['spades 'hearts 'diamonds 'clubs])

(defn within?
  [[h & tl] v]
  (and (not (nil? h)) (or (= h v) (recur tl v))))

(defn to-hand
  [m [k v]]
  (if (contains? m k)
    (merge m {k (cons v (m k))})
    (merge m {k (list v)})))

(defn valid?
  [card]
  (let [[rank suit] card]
    (and (= (count card) 2)
         (within? ranks rank)
         (within? suits suit))))

(defn get-card
  []
  (do (println "input a card, \"rank\" \"suit\" ")
      (flush)
      (let [card (map read-string (split (read-line) #" "))]
        (if (valid? card) card
            (do (println "invalid card") (recur))))))

(defn matchingRanks
  [num hand]
  (count (filter #(= num (count %)) (vals hand))))

(defn consecutive?
  [[h & tl]]
  (or (not tl)
      (and (= 1 (- (.indexOf ranks (first tl))
                   (.indexOf ranks h)))
           (recur tl))))

(defn straight-flush?
  "all cards are of the same suite and
  their ranks are consecutive. Note that they are
  probably not ordered as they were entered."
  [hand]
  (and (= 1 (count (set (vals hand))))
       (consecutive? (sort (keys hand)))))

(defn poker?
  "four of the five cards have the same rank."
  [hand]
  (= 1 (matchingRanks 4 hand)))

(defn full-house?
  "three of a kind plus two of a kind."
  [hand]
  (and (= 2 (count hand))
       (#(or (= 3 %) (= 2 %)) (count (first (vals hand))))))

(defn flush?
  "all cards share the same suit, but are not consecutive."
  [hand]
  (and (= 1 (count (set (vals hand))))
       (not (consecutive? (sort (keys hand))))))

(defn straight?
  "all cards are consecutive, but not of the same suit."
  [hand]
  (and (= (count hand) 5)
       (> (count (set (vals hand))) 1)
       (consecutive? (sort (keys hand)))))

(defn three-of-a-kind?
  "three of the five cards have the same rank."
  [hand]
  (and (= 3 (count hand))
   (= 1 (matchingRanks 3 hand))))

(defn two-pair?
  "two pair of same rank"
  [hand]
  (= 2 (matchingRanks 2 hand)))

(defn pair?
  "two of the five cards have the same rank."
  [hand]
  (and (= 4 (count hand))
       (= 1 (matchingRanks 2 hand))))

(defn best-hand
  [hand]
  (cond
    (straight-flush? hand) "Straight flush"
    (poker? hand) "Four of a kind"
    (full-house? hand) "Full house"
    (flush? hand) "Flush"
    (straight? hand) "Straight"
    (three-of-a-kind? hand) "Three of a kind"
    (two-pair? hand) "Two pair"
    (pair? hand) "Pair"
    :else "You got nothin!!"))
