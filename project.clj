(defproject github-colorful-contributions-graph "1.4.0"
  :description "A Chrome extension for customizing the colors of the GitHub contributions' graph."
  :url "http://github.com/g1eny0ung/github-colorful-contributions-graph"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.8.1"]
                 [cljsjs/react-color "2.13.8-0"]]

  :plugins [[lein-figwheel "0.5.19"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-ancient "0.6.15"]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/github_colorful_contributions_graph"]
                :figwheel {:on-jsload "github-colorful-contributions-graph.core/on-js-reload"}
                :compiler {:main github-colorful-contributions-graph.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/github_colorful_contributions.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src/github_colorful_contributions_graph"]
                :compiler {:output-to "resources/public/js/compiled/github_colorful_contributions.js"
                           :main github-colorful-contributions-graph.core
                           :externs ["externs/chrome.js" "externs/chrome_extensions.js"]
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]}
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.10"]
                                  [figwheel-sidecar "0.5.19"]
                                  [cider/piggieback "0.4.2"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
