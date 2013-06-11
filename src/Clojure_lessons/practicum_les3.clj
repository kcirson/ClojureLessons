(ns Clojure-lessons.practicum-les3)
(use 'clojure.string)

;;Opdracht 1
;;1
(= (list :aap :noot :mies) '(:aap :noot :mies))
(= '(1 2 3 4) (flatten '(1 2 (3 4))))
(= '(1 2 3 4) (conj '(3 4) 2 1))

;;2
(defn reverselist [list] (sort > list))
(reverselist (list 1 2 5 6 2 8 7 15 12))

;;3
(= (list :a :b :c) '(:a :b :c))

;;4
(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))

;; Opdracht 2

;;1
(= '(1 2 3 4) (conj [1 2] 3 4))
(= '(1 2 3 4) (into () '(4 3 2 1)))

;;2
(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))

;;3
(= '(1 2 3 4) (conj [1 2 3] 4))

(= '(1 2 3 4)(conj [1 2] 3 4))

;; Opdracht 3

;; 1

(zipmap [:first :second :third ] [1 2 3])

;;2
(apply hash-map (interleave [:first :second :third ] [1 2 3]))

;;3

(def tentamencijfers [{:naam "Piet" :cijfer 7}  
                      {:naam "Klaas" :cijfer 3}])
(assoc-in tentamencijfers[1 :cijfer] 10)

;;4
(get-in (assoc-in tentamencijfers[1 :cijfer] 10)[1 :cijfer])

;;5
(= 20 ((hash-map :a 10, :b 20, :c 30) :b))
(= 20 (:b {:a 10, :b 20, :c 30}))

;;6
(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))
   
;;7
(true?  ((fn [key map] (and (nil? (get map key)) (contains? map key))) :a {:a nil :b 2}))
(false? ((fn [key map] (and (nil? (get map key)) (contains? map key))) :b {:a nil :b 2}))
(false? ((fn [key map] (and (nil? (get map key)) (contains? map key))) :c {:a nil :b 2}))



;; Opdracht 4

;; 1
(remove #{:a :d} #{:a :b :c :d}) 

;; 2
(set [1 1 2 3])
;;3 elementen dus

;; 3

(apply conj #{1 2 3 4} #{5 6 7 8})

;; 4
(= #{:a :c :b :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :c :b :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))


;; 5
(= #{1 2 3 4} (conj #{1 4 3} 2))

;; 6

;;Waarom mag intersection niet ?    (fn [set1 set2] (intersection set1 set2))

(= ((fn [a b] (reduce #(if (contains? b %2) (conj %1 %2) %1) #{} a)) #{0 1 2 3} #{2 3 4 5}) #{2 3})	
(= ((fn [a b] (reduce #(if (contains? b %2) (conj %1 %2) %1) #{} a)) #{0 1 2} #{3 4 5}) #{})	
(= ((fn [a b] (reduce #(if (contains? b %2) (conj %1 %2) %1) #{} a)) #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})


;;Opdracht 5
;;1
(cons 5 [4 3 2 1 0])

;;2
(join " " ["een" "twee" "drie"])

;;3
(defn my-but-last [sq] (peek (pop (reverse sq))))
(my-but-last ["maandag" "dinsdag" "woensdag" "donderdag" "vrijdag"])

;;4
(defn removeDoubles [sq] (seq (set sq)))
(removeDoubles (seq [1 2 3 4 5 6 3 4 5]))


;;Opdracht 6

;;1
(defn repeat-fifteen [x] (repeat 15 x))

;;2
(use 'clojure.xml)
(def xmlstring "<?xml version=\"1.0\" encoding=\"utf-8\"?>
                <tentamenresultaten>
                  <resultaat>
                    <naam>Piet</naam>
                    <cijfer>7</cijfer>
                  </resultaat>
                  <resultaat>
                    <naam>Klaas</naam>
                    <cijfer>10</cijfer>
                  </resultaat>
                </tentamenresultaten>")
(def xmlinputstream (java.io.ByteArrayInputStream. (.getBytes xmlstring)))
(def parsed-xml (parse xmlinputstream))

#_(def tentamenresultaten (first (:content parsed-xml)))
(defn naam [tentamenresultaten] (first (:content (first (:content tentamenresultaten)))) )
(defn cijfer [tentamenresultaten] (first (:content (second (:content tentamenresultaten)))))

;;(hash-map (for [elt (xml-seq parsed-xml) :when (= :resultaat (:tag elt))] {(naam elt) (cijfer elt)}))
(for [elt (xml-seq parsed-xml) :when (= :resultaat (:tag elt))] (hash-map (naam elt) (cijfer elt)))

;; 3
;; Van vorige weeek: (defn divisible-by-three? [denom] (zero? (rem denom 3)))

(defn divisible-by-three? [denom] (zero? (rem denom 3)))
(for [i (range 20) :when (divisible-by-three? i)] i)

;; 4

(defn divisible-by-three? [denom] (zero? (rem denom 3)))
(filter divisible-by-three? (range 20))
  
;; Bij een for loop je door alles heen, maar bij een filter pakt ie eruit wat je nodig heb