(ns bowling.core-spec
  (:require [speclj.core :refer :all]
            [bowling.core :refer :all]))

(describe "bowling"
  (describe "scoring"
      (it "Gutter game scores 0"
        (should= 0 (score-game (take 20 (repeat 0)))))
      (it "One pin per roll scores 20"
        (should= 20 (score-game (take 20 (repeat 1)))))
      (it "Spare adds next rolls to score"
          (should= 18 (score-game [5 5 4]))
          (should= 22 (score-game [5 5 4 4])))
      (it "Strike adds next two rolls to score"
          (should= 28 (score-game [10 5 4])))
      (it "All strikes results 300"
          (should= 300 (score-game (repeat 10))))
      (it "Spare in the last frame is extra turn"
          (should= 40 (score-game [1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 9 3])))
  )
  (describe "-> frames"
    (it "No rolls is no frames"
        (should= [] (->frames [])))
    (it "One roll result one frame"
        (should= [[1]] (->frames [1])))
    (it "two rolls result one frame"
        (should= [[1 1]] (->frames [1 1])))
    (it "three rolls result two frames"
        (should= [[1 1] [1]] (->frames [1 1 1])))
    (it "four rolls results in two frames"
        (should= [[1 1] [1 1]] (->frames [1 1 1 1])))
    (it "Spare adds next roll to frame"
        (should= [[5 5 4] [4]] (->frames [5 5 4])))
    (it "Strike adds next two rolls to frame"
        (should= [[10 5 4] [5 4]] (->frames [10 5 4])))
  )
)
