(defproject bible "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.4.0"]
                           [org.clojure/tools.logging "0.2.3"]
                           [noir "1.3.0-beta3"]]
            :jvm-opts ["-Xmx1g"]
            :min-lein-version "2.0.0"
            :main bible.server)
