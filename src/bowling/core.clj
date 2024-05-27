(ns bowling.core)

(defn spare? [rolls]
  (= 10 (reduce + (take 2 rolls))))

(defn strike? [rolls]
  (= 10 (first rolls)))

(defn ->first-frame [rolls]
  (if (or (spare? rolls) (strike? rolls))
    (take 3 rolls)
    (take 2 rolls)))

(defn ->rest-rolls [rolls]
  (if (strike? rolls)
    (drop 1 rolls)
    (drop 2 rolls)))

(defn ->frames [rolls]
  (if (empty? rolls)
    []
    (cons
      (->first-frame rolls)
      (lazy-seq (->frames (->rest-rolls rolls))))))

(defn score [rolls]
  (reduce + (flatten (take 10 (->frames rolls)))))