(defproject wishes "0.1.0-SNAPSHOT"
  
  :dependencies [
    [org.clojure/clojure "1.6.0"]
    [http-kit "2.1.18"]
    [compojure "1.1.6"]
    [org.clojure/data.json "0.2.4"]

    [org.clojure/clojurescript "0.0-2156"]
    [reagent "0.3.0"]
  ]

  :plugins[
     [lein-cljsbuild "1.0.2"]
  ]

  :source-paths ["src/clj"]
  
  :main wishes.core

  :cljsbuild {:builds  [{:id "dev"
                         :source-paths ["src/cljs"]
                         :compiler {:output-dir "www/js/compiled"
                                    :output-to  "www/js/compiled/wishes.js"
                                    :source-map "www/js/compiled/wishes.js.map"
                                    :optimizations :none
                                    :pretty-print true}}]})
