(ns wishes.core
  (:use 
     [org.httpkit.server :as http-kit :only [run-server]])
  (:require
     [clojure.data.json :as json]

     [compojure.core :as c.core]
     [compojure.route :as c.route]

     [ring.util.response :as r.util]

     ))


;; a function stopping the HTTP server
(defonce stop-server (atom nil))

(defonce wishes [
  {:wish "Live long" :for "Everybody" :motivation :positive}
  {:wish "Go home" :for "Me" :motivation :neutral}
  {:wish "Suffer" :for "My enemies" :motivation :negative}
  {:wish "Prosper" :for "My family" :motivation :positive}])

(defn get-wishes []
  (->
    (json/write-str wishes) ;;XXX why JSON? ;-) use EDN as is
    (r.util/response) 
    (r.util/content-type "application/json")))

(c.core/defroutes app-routes
  (c.core/GET  "/wishes" [] (get-wishes))
  (c.route/resources "/")
  (c.route/not-found (slurp "www/404.html")))

(defn start "Starts the HTTP server on the given port" [port]
  (println (format "Starting HTTP server on port #%s..." port))
  ;; run-server returns a function that stops the server
  (reset! stop-server (http-kit/run-server #'app-routes {:port port})))

(defn stop "Stops the HTTP server" []
  (when @stop-server
    (@stop-server)
    (reset! stop-server nil)      
    (println "HTTP server stopped")))

(defn -main [port-str] 
  (start (Integer/valueOf port-str)))