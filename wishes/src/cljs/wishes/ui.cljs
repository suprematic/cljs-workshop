(ns wishes.ui
  (:require 
    [clojure.walk :as cw]
    [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce serverURI "wishes")

(def wishes-model (atom nil))

(defn refresh-wishes []
  (let [request (js/XMLHttpRequest.)]
    (.open request "GET" serverURI false)
    (set! (.-onload request) 
      (fn []
        (let [response (.-responseText request)
              wishes (cw/keywordize-keys (js->clj (.parse js/JSON response)))
              ]
          (println (str "Got response: " response))
          (println (str "Wishes      : " wishes))
          (reset! wishes-model wishes))))
    (.send request)))

(defn c-wish [{:keys [motivation wish for]}]
  [:div {:key (gensym)} 
    (str "I wish " for " to " wish " with " (name motivation) " motivation")]  )

(defn c-wishes []
  (map c-wish @wishes-model))

(defn c-body []
  [:div
    [:h1 "Wishes"]
    (c-wishes)])

(defn ^:export run [] 
  (println "Started web-application")
  (reagent/render-component [c-body]
    (.-body js/document))
  (refresh-wishes))