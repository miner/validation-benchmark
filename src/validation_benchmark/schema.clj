(ns validation-benchmark.schema
  (:require [schema.core :as s]))


(defn nil-allowed-bool [v]
  (s/validate (s/maybe s/Bool) v))


(defn nil-allowed-number [v]
  (s/validate (s/maybe s/Num) v))


(defn nil-allowed-string [v]
  (s/validate (s/maybe s/Str) v))


(defn set-of-keywords [v]
  (s/validate #{s/Keyword} v))


(defn three-tuple [v]
  (s/validate [(s/one s/Keyword "k")
               (s/one s/Str "s")
               (s/one s/Num "n")]
              v))


(defn vector-of-two-elements [v]
  (s/validate [(s/one s/Any "first") (s/one s/Any "second")] v))


(defn vector-of-variable-length [v]
  (s/validate [s/Any] v))


(defn wrapper [f valid?]
  (fn [v]
    (= (try
         (f v)
         true
         (catch Exception e false))
       valid?)))
