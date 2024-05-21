(ns bowling.core)

(defn is-spare? [rolls] (= 10 (reduce + (take 2 rolls))))

(defn is-strike? [rolls] (= 10 (first rolls)))

(defn create-frame [rolls]
  (if (or (is-strike? rolls) (is-spare? rolls))
    (take 3 rolls)
    (take 2 rolls)))

(defn rest-rolls [rolls]
  (if (is-strike? rolls)
    (drop 1 rolls)
    (drop 2 rolls)))

(defn ->frames [rolls]
  (if (empty? rolls)
    []
    (cons (create-frame rolls) (lazy-seq (->frames (rest-rolls rolls))))))

(defn score-game [score]
    (reduce + (flatten (take 10 (->frames score)))))


