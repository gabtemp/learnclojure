(ns myweb.routes
  (:require [compojure.core :refer [defroutes context GET POST]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]))

(defroutes myapp
  (context ["/admin/:id" :id #"[a-fA-F0-9]+"] [id]
   (GET "/" [] "Please log in")
   (GET "/login" [] (str "Logging in id " id))
   (GET "/logout" [] (str "Logging out id " id)))

  (GET "/" [] "Hello to compojure")

  (POST "/:name" [] (fn [req] (str "Hello, " (-> req :route-params :name))))

  (route/resources "/static")
  (route/not-found "404 Not Found"))


(def handler
  (-> myapp))

(defn -main []
  (jetty/run-jetty handler {:port 3000}))

