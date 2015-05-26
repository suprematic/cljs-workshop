(ns wishes.ui
  (:require 
    [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defn get-wishes [] [
  {:wish "Live long" :for "Everybody" :motivation :positive}
  {:wish "Go home" :for "Me" :motivation :neutral}
  {:wish "Suffer" :for "My enemies" :motivation :negative}
  {:wish "Prosper" :for "My family" :motivation :positive}])

(defn c-wish [{:keys [motivation wish for]}]
  [:div (str "I wish " for " to " wish " with " (name motivation) " motivation")]  )

(defn c-body []
  [:div
    [:h1 "Wishes"]
    (map c-wish (get-wishes))])

(defn ^:export run [] 
  (println "Started web-application")
  (reagent/render-component [c-body]
    (.-body js/document)))