(ns webservice.core
  (:require [webservice.routes :as routes]
            [ring.adapter.jetty :as jetty]))


(def handler
  (-> routes/routes))

(defn -main []
  (jetty/run-jetty handler {:port 3000}))