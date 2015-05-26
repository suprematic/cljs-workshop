# ClojureScript Workshop





# Hello-World with FigWheel (https://github.com/bhauman/lein-figwheel)

* demo in-browser REPL capabilities
* demo auto-save-refresh in browser

brew install rlwrap (for better REPL)
lein new figwheel hello-world -- --reagent
cd hello-world
rlwrap lein figwheel

open http://localhost:3449/index.html in browser

In REPL:

=> (ns hello-world.core)
=> (reset! app-state {:text "Bla"})

Open ClojureScript tab in Chrome dev tools, do the same.