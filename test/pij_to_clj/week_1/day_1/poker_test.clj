(ns pij-to-clj.week-1.day-1.poker-test
  (:require [clojure.test :refer :all]
            [pij-to-clj.week-1.day-1.poker :refer :all]))

(deftest within?-test
  (testing "should know if it contains the element"
    (is (within? (list 1 2 3) 2))
    (is (not (within? (list 1 2 3) 4)))))

(deftest valid?-test
  (testing "should know if its a valid card"
    (is (valid? (list 3 'hearts)))
    (is (not (valid? (list 1 'cheddar))))
    (is (valid? (list 'J 'spades)))
    (is (not (valid? (list 'hearts 3))))
    (is (not (valid? (list 'J 'spades 1))))))

(deftest to-hand-test
  (testing "start a hand"
    (is (= {4 (list 'hearts)} (to-hand {} (list 4 'hearts)))))
  (testing "should join matching ranks"
    (is (= {5 (list 'spades 'hearts)}
           (to-hand {5 (list 'hearts)} (list 5 'spades)))))
  (testing "should not join unmatching ranks"
    (is (= {6 (list 'hearts) 3 (list 'hearts)}
           (to-hand {6 (list 'hearts)} (list 3 'hearts))))))

(def straight-flush {5 (list 'spades)
                     6 (list 'spades)
                     4 (list 'spades)
                     3 (list 'spades)
                     2 (list 'spades)})

(def poker {5 (list 'spades 'clubs 'hearts 'diamonds)
            4 (list 'spades)})

(def full-house {3 (list 'spades 'clubs)
                 2 (list 'spades 'clubs 'hearts)})

(def flushh {5 (list 'spades)
             10 (list 'spades)
             4 (list 'spades)
             3 (list 'spades)
             2 (list 'spades)})

(def straight {5 (list 'spades)
               6 (list 'spades)
               4 (list 'spades)
               3 (list 'hearts)
               2 (list 'spades)})

(def three-of-a-kind {5 (list 'clubs 'hearts 'diamonds)
                      4 (list 'spades)
                      3 (list 'spades)})

(def two-pair {5 (list 'clubs 'hearts)
               4 (list 'spades 'diamonds)
               3 (list 'spades)})

(def pair {6 (list 'hearts)
           5 (list 'hearts)
           4 (list 'spades 'diamonds)
           3 (list 'spades)})

(deftest straight-flush?-test
  (testing "should only detect a straight flush"
    (is (straight-flush? straight-flush))
    (is (not (straight-flush? poker)))
    (is (not (straight-flush? full-house)))
    (is (not (straight-flush? flushh)))
    (is (not (straight-flush? straight)))
    (is (not (straight-flush? three-of-a-kind)))
    (is (not (straight-flush? two-pair)))
    (is (not (straight-flush? pair)))))

(deftest poker?-test
  (testing "should only detect a poker"
    (is (poker? poker))
    (is (not (poker? straight-flush)))
    (is (not (poker? full-house)))
    (is (not (poker? flushh)))
    (is (not (poker? straight)))
    (is (not (poker? three-of-a-kind)))
    (is (not (poker? two-pair)))
    (is (not (poker? pair)))))

(deftest full-house?-test
  (testing "should only detect a full house"
    (is (full-house? full-house))
    (is (not (full-house? poker)))
    (is (not (full-house? straight-flush)))
    (is (not (full-house? flushh)))
    (is (not (full-house? straight)))
    (is (not (full-house? three-of-a-kind)))
    (is (not (full-house? two-pair)))
    (is (not (full-house? pair)))))

(deftest flush?-test
  (testing "should only detect a flush"
    (is (flush? flushh))
    (is (not (flush? poker)))
    (is (not (flush? straight-flush)))
    (is (not (flush? full-house)))
    (is (not (flush? straight)))
    (is (not (flush? three-of-a-kind)))
    (is (not (flush? two-pair)))
    (is (not (flush? pair)))))

(deftest straight?-test
  (testing "should only detect a straight"
    (is (straight? straight))
    (is (not (straight? poker)))
    (is (not (straight? straight-flush)))
    (is (not (straight? full-house)))
    (is (not (straight? flushh)))
    (is (not (straight? three-of-a-kind)))
    (is (not (straight? two-pair)))
    (is (not (straight? pair)))))

(deftest three-of-a-kind?-test
  (testing "should only detect a three of a kind"
    (is (three-of-a-kind? three-of-a-kind))
    (is (not (three-of-a-kind? poker)))
    (is (not (three-of-a-kind? straight-flush)))
    (is (not (three-of-a-kind? full-house)))
    (is (not (three-of-a-kind? flushh)))
    (is (not (three-of-a-kind? straight)))
    (is (not (three-of-a-kind? two-pair)))
    (is (not (three-of-a-kind? pair)))))

(deftest two-pair?-test
  (testing "should only detect a two-pair"
    (is (two-pair? two-pair))
    (is (not (two-pair? poker)))
    (is (not (two-pair? straight-flush)))
    (is (not (two-pair? full-house)))
    (is (not (two-pair? flushh)))
    (is (not (two-pair? straight)))
    (is (not (two-pair? three-of-a-kind)))
    (is (not (two-pair? pair)))))

(deftest pair?-test
  (testing "should only detect a pair"
    (is (pair? pair))
    (is (not (pair? poker)))
    (is (not (pair? straight-flush)))
    (is (not (pair? full-house)))
    (is (not (pair? flushh)))
    (is (not (pair? straight)))
    (is (not (pair? three-of-a-kind)))
    (is (not (pair? two-pair)))))

(def crap {5 (list 'spades)
           10 (list 'hearts)
           4 (list 'spades)
           3 (list 'spades)
           2 (list 'spades)})

(deftest best-hand-test
  (testing "should say what hand"
    (is (= "Straight flush" (best-hand straight-flush)))
    (is (= "Four of a kind" (best-hand poker)))
    (is (= "Full house" (best-hand full-house)))
    (is (= "Flush" (best-hand flushh)))
    (is (= "Straight" (best-hand straight)))
    (is (= "Three of a kind" (best-hand three-of-a-kind)))
    (is (= "Two pair" (best-hand two-pair)))
    (is (= "Pair" (best-hand pair)))
    (is (= "You got nothin!!" (best-hand crap)))))

(deftest consecutive?-test
  (testing "should be consecutive"
    (is (consecutive? (list 9 10 'J 'Q 'K)))))


(def royal-flush {'K (list 'spades)
                  'Q (list 'spades)
                  9  (list 'spades)
                  'J (list 'spades)
                  8  (list 'spades)})

(deftest sort-ranks-test
  (testing "should sort ranks"
    (is (= (list 8 9 'J 'Q 'K)
           (sort-ranks royal-flush)))))
