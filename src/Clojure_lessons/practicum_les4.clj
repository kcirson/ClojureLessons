(ns Clojure-lessons.practicum-les4)
;; Opdracht 1.1
;; A

(defn sumTo ([n]
              (if (= n 0) n (sumTo n 0)))
             ([n total]
               (if (= n 0) total (recur (- n 1) (+ n total)))
             )
)

(sumTo 10)
(sumTo 100)

;; B

(defn nul-tot [n] () ) ;; (nul-tot 6) => [0 1 2 3 4 5]
(defn som [lijst] (apply sum lijst) )
(defn sumTo [n] (som (nul-tot n)))

(sumTo 10)
(sumTo 100)

;; Opdracht 1.2

(defn times-called
  ([x start limit]
    (times-called x start limit 0))
  ([x current limit count]
    (if (>= current limit) (dec count)
      (recur x (x current) limit (inc count)))
    )
  )

(times-called (fn [x] (+ x x)) 2 1000) ;;=> 8
(times-called (fn [x] (* x x)) 2 1000) ;;=> 3
(times-called (fn [x] (Math/pow x x)) 2 1000) ;;=> 2

;; Opdracht 2 Atoms

;;2.1

(def book1 {:type :book, 
 :title "The Joy of Clojure",
 :authors "Fogus and Houser", 
 :publisher "Manning"})

(def book2 {:type :book,
 :title "Inferno"
 :authors "Dan Brown"
 :publisher "Manning"})

(def book3 {:type :book,
 :title "Davinci Code",
 :authors "Dan Brown",
 :publisher "Manning"})

(def updatedBook3 {:type :book,
 :title "Davinci Code",
 :authors "Dan Brown",
 :publisher "Doubleday"})


(println book1)
(println book2)
(println book3)
(println updatedBook3)

(def libraryAtom (atom ()))

;;This will search through the @libraryAtom and search all items for the :title of the book
(defn get-book [title] (filter #(= (:title %) title) @libraryAtom))

;;Insert book if the gt-book results in nil, else prinln an error message
(defn insert-book [book] (if (= () (get-book (:title book))) (swap! libraryAtom conj book) (println "Insert-book: that book is already in our library")))

;;Delete book if get-book not results in nil, then put everybook that is not equal to the book in a new atom and reset the atom to the new atom.
(defn delete-book [book]
  (if (= () (get-book (:title book)))
    (println "delete-book: This book is not in our library so we can't remove it")
    ;;else
    (let [newAtom (filter #(not= (:title %) (:title book)) @libraryAtom)]
    (reset! libraryAtom newAtom)
    )
  )
)

;;Update book
(defn update-book [book] 
  (if(= () (get-book(:title book)))
    (println "update-book: This book is not in our library so we can't update it")
    ;;else
    (do (delete-book book) (insert-book book))
  )
)

;;scenario
(insert-book book1)
(insert-book book2)
(insert-book book2)

(get-book (:title book1))
(get-book (:title book2))
(get-book (:title book3))

(delete-book book3)
(delete-book book2)

(update-book updatedBook3)
(insert-book book3)
(update-book updatedBook3)

(println libraryAtom)

;;Opdracht 2.2
(def stopwatch (atom {:time nil}))

(defn start! []
  (if (not= (:time @stopwatch) nil)
    "Stopwatch already started"
    (do (swap! stopwatch assoc :time (. java.lang.System (nanoTime)))
      "Stopwatch started")))

(defn stop! []
(if (= (:time @stopwatch) nil)
  "start stopwatch first"
  (let [elapsed (format "%.3f" (/ (- (. System (nanoTime)) (:time @stopwatch)) 1000000000.00))]
      (do
	      (swap! stopwatch assoc :time nil)
	      (str elapsed " seconds passed since start")))))

(start!)
(stop!)

;;Opdracht 2.3
;;Het is een hogere orde functie omdat hij een functie tot zich neemt en daarvan de uitkomst gaat opslaan in een atom