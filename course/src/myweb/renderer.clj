(ns myweb.renderer
  (:require [clojure.java.io :as io]
            [selmer.parser :as tmpl]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :as jetty]))

(tmpl/set-resource-path! (io/resource "templates"))

(tmpl/render "<h1>Hello, {{ name }}</h1>" {:name "Frank"})

(tmpl/render-file "hello.html" {:name "Frank"})

(defn respond-html [s]
  {:body s
   :status 200
   :header {"Content-Type" "text/html"}})

(def respond-tmpl (comp respond-html tmpl/render-file))

(defn myapp [request]
  (respond-tmpl "hello.html" {:name (get (:params request) "name")}))

(defn -main []
  (jetty/run-jetty (wrap-params myapp) {:port 3000}))
