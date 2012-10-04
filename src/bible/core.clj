(ns bible.core
  (:use
    [cheshire.core]
    [clojure.tools.logging]
    [clojure.string :only [join]]))

(def books {
    "Matthew" "matthew"
    "Mark" "mark"
    "Luke" "luke"
    "John" "john"
    "Acts" "acts"
    "Romans" "romans"
    "1 Corinthians" "cor1"
    "2 Corinthians" "cor2"
    "Galatians" "gal"
    "Ephesians" "eph"
    "Phillipians" "phil"
    "Colossians" "col"
    "1 Thessalonians" "thess1"
    "2 Thessalonians" "thess2"
    "1 Timothy" "tim1"
    "2 Timothy" "tim2"
    "Titus" "titus"
    "Philemon" "phm"
    "Hebrews" "heb"
    "James" "james"
    "1 Peter" "peter1"
    "2 Peter" "peter2"
    "1 John" "john1"
    "2 John" "john2"
    "3 John" "john3"
    "Jude" "jude"
    "Revelation" "revelation"})

(def book-order ["Matthew" "Mark" "Luke" "John" "Acts" "Romans"
                 "1 Corinthians" "2 Corinthians" "Galatians" "Ephesians"
                 "Phillipians" "Colossians" "1 Thessalonians"
                 "2 Thessalonians" "1 Timothy" "2 Timothy" "Titus" "Philemon"
                 "Hebrews" "James" "1 Peter" "2 Peter" "1 John" "2 John"
                 "3 John" "Jude" "Revelation"])

(defn make-select []
  [:select {:name "book"}
    (map
      (fn [name] [:option {:value (books name)} name])
      book-order)])

(defn load-bible-file [filename]
  (parse-string (slurp (str "books/" filename)) true))

(defn load-all-data
  "Create a map of books to their data
    {'romans' {:1 ...}}"
  []
  (info "Loading bible data")
  (let [filenames (map #(str % ".json") (vals books))]
    (into {}
      (map
        (fn [name filename] [(keyword name) (load-bible-file filename)])
          (vals books)
          filenames))))

(def nt (load-all-data))

(defn get-verse [{:keys [book chapter verse]}]
  (get-in nt [(keyword book)
              (keyword (str chapter))
              (keyword (str verse))]))

(defn get-verse-preview [verse]
  (if (empty? verse)
    "Verse not found."
    (join " " (map #(% :word) verse))))
