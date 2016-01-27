(ns hobbit.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  "Create the matching right side part, from the left side one"
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrisize-body-parts
   "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-parts asym-body-parts
         new-parts []]

    (if (empty? remaining-parts)
      new-parts
      (let [[part & rest] remaining-parts]
        (recur rest
               (into new-parts
                     (set [part (matching-part part)])))))))

(defn better-symmetrise
  "Same as symmetrisize-body-parts, but using reduce"
  [asym-body-parts]
    (reduce
     (fn [final-body-parts part]
       (into (set [part (matching-part part)]) final-body-parts))
     []
     asym-body-parts))
