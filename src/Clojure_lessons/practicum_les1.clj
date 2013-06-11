(ns Clojure-lessons.practicum_les1) java.lang.String

;; opdracht 1
;;(2 3 4 5)

;;opracht 2
;;Installeren...

;Opdr 3
(/ 3 4.0)

;; opdracht 4
;; int x = 465 - 774 * 3 / 3 + 774
(def x (+ (- 465 (/ (* 774 3) 3)) 774))

;; opdracht 5 
(do (println "(def x (+ (- 465 (/ (* 774 3) 3)) 774))") x)

;Opdr 6
(if (= (def d1 (java.util.Date.))(def d2 (java.util.Date.))) "de data is gelijk" "de data zijn ongelijk")

;Opdr 7
;Cond is een soort van IF, waarbij je meerdere condities op kan geven
(cond (= 1 2) "een is gelijk aan twee"
      (< 1 2) "een is kleiner dan twee"
      :else "geen van beide")

;;(= 9 (let [x 8 y 1] (+ x y)))
;;(= 3 (let [y 1 z 2] (+ y z)))
;;(= 2 (let [z 2] z))

(let [x 8 y 1 z 2]
  (print (= 9 (+ x y))
         (= 3 (+ y z))
         (= 2 z)))

;; opdracht 8
(defn hello [name] (.replaceAll "Hello, <name>" "<name>" name))

;; opdracht 9
;;[x 7 y 3 z 1]



