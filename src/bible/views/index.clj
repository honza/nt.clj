(ns bible.views.index
  (:require [bible.views.common :as common])
  (:use [noir.core :only [defpage]]
        [cheshire.core]
        [bible.core :only [get-verse get-verse-preview books make-select]]))

(def form [:form {:method "get"}
           (make-select)
            [:input {:type "text" :name "chapter"}]
            [:input {:type "text" :name "verse"}]
            [:input {:type "submit"}]])

(defpage [:get "/"] {:keys [book chapter verse]}
  (common/layout
    [:div
      form
      [:p (get-verse-preview
            (get-verse {:book book :chapter chapter :verse verse}))]]))

(defpage [:get "/api/:book/:chapter/:verse"] {:keys [book chapter verse]}
  (generate-string
    (get-verse {:book book :chapter chapter :verse verse})))
