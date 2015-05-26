(ns wishes.ui
  (:require 
    [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defn c-hello-world []
  [:h1 "Hello, World!"])

(defn ^:export run [] 
  (println "Started web-application")
  (reagent/render-component [c-hello-world]
    (.-body js/document)))