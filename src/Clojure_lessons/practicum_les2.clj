(ns Clojure-lessons.practicum-les2)

;; opdracht 1
(apply max [1 2 5 3 4])

;;opdracht 2

((fn [x] (* x 3)) 4) ;;== 4 * 3 = 12
(#(- % 7) 19) ;;== 19 - 7 = 12   
((partial / 36) 3) ;; == 36 / 3 = 12

((fn [x] (* x x)) 3)

(filter #(< %1 5) [1 6 5 2 3])

;;opdracht 3
(defn myfunc ([x y] (* x y)) ([x y others](+ x y others)))

;;opdracht 4

(defn giveLast [list] (peek (reverse list)))

(defn getStrings [& args] (filter string? args))
(getStrings "ello" "haai" 1 2 3 "oi")

(loop [c 0, n 10] (if (= c n) n (do (print c) (recur (inc c) n))))

;;opdracht 5
(filter zero? [1 2 0 3 4 0 5 6]) ;;=> '(0 0)
(filter (comp not zero?)[1 2 0 3 4 0 5 6]) ;;=> '(1 2 3 4 5 6) 

;;opdracht 6
;; Met parial kan je een bestaande functies aanroepen en daar een gedeelte van de arguments aan meegeven. Hiermee
;; creeer je een nieuwe functie.

(= '(3 4 5) (map (partial + 2) [1 2 3]))

(= "first second third" (let [a "second" b "third"] ((partial print-str "first") a b)))


;; Met comp kun je een functie maken die een bepaalde activiteit over het algeheel uitvoerd. 
;; Er kan bijv een getal worden opgeteld bij de ingevulde waardes.
(= 8 (let [x 8 y 1] ((comp * max) x y)))
(= -8/3 (let [x 8 y 3] ((comp - /) x y)))
(= -5 (let [x 8 y 3] ((comp + - -) x y)))

;;opdracht 7
;;complement neemt een bestaande functie en returned de functie met dezelfde effecten maar met een tegenovergesteld waarheids waarde
;; dus als je een in deze opgave een not-ends-with? en je checkt /o op hallo dan krijg je false want hij geeft het tegenovergestelde terug.

(defn ends-with? [s c] (.endsWith s (str c)))
(def not-ends-with? (complement ends-with?))
;;(ends-with? "hallo" \o)
;;(not-ends-with? "hallo" \o)

;;opdracht 8
(defn divisible-by-three? [denom] (zero? (rem denom 3)))

;;opdracht 9
;;(defn max-except-first [list] (peek (pop (reverse (sort list)))))
;;(defn max-except-first [list] (let [sec (second (reverse (sort list)))] println sec))
;;(defn max-except-first [& list] (let [sec (apply second (reverse (sort list)))] println sec))
(defn max-except-first [list] (apply max (next list)))

;;(max-except-first [100 78 7 9 12])


;;opdracht 10

(defn square [x] (* x x))
(defn average [x y] (/ (+ x y) 2))
(defn abs [x] (if (< x 0) (- x) x))

(defn improveGuess [guess x] (average guess (/ x guess)))

(defn good-enough? [guess x] (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improveGuess guess x)
                 x)))

(defn sqrt [x] (sqrt-iter 1.0 x))
