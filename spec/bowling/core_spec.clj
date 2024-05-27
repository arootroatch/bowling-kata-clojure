(ns bowling.core-spec
  (:require [speclj.core :refer :all]
            [bowling.core :refer :all]))

(describe "bowling"
  (context "scoring"
           (it "gutter game is zero points"
             (should= 0 (score (repeat 0))))
           (it "all ones is 20 points"
             (should= 20 (score (repeat 1))))
           (it "spare adds next roll to score"
             (should= 18 (score [5 5 4])))
           (it "strike adds next two rolls to score"
             (should= 28 (score [10 5 4])))
           (it "perfect game is 300"
             (should= 300 (score (repeat 10)))))
  (context "->frames"
           (it "zero rolls is zero frames"
             (should= [] (->frames [])))
           (it "2 rolls is one frame"
             (should= [[1 1]] (->frames [1 1])))
           (it "3 rolls is 2 frames"
             (should= [[1 1] [1]] (->frames [1 1 1])))
           (it "spare adds next roll to the frame"
             (should= [[5 5 4] [4]] (->frames [5 5 4])))
           (it "strike adds next two rolls to the frame"
             (should= [[10 5 4 ] [5 4]] (->frames [10 5 4])))))



