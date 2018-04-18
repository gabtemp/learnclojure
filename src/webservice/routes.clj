(ns webservice.routes
  (:require [compojure.core :refer [defroutes context GET POST PUT DELETE]]
            [compojure.route :as route]))


;; Enable expand param for all GET methods
(defroutes routes
           (context "/product" []
             (GET "/" [] "All products")
             (POST "/" [] "Create a single product")

             (context ["/:id" :id #"[1-9][0-9]*"] [id]
               (GET "/" [] (str "Product with id " id))
               (POST "/" [] (str "Create children for product with id " id))
               (PUT "/" [] (str "Update children for product with id " id))
               (DELETE "/" [] (str "Delete children for product with id " id))

               (GET "/children" [] (str "Children of product with id " id))

               (context "/image" []
                 (GET "/" [] (str "All images for product with id " id))
                 (POST "/" [] (str "Create image for product with id " id))

                 (context ["/:img" :img #"[1-9][0-9]*"] [img]
                   (GET "/" (str "Image " img " for product with id " id))
                   (PUT "/" (str "Image " img " for product with id " id))
                   (DELETE "/" (str "Image " img " for product with id " id))))))

           (route/not-found " 404 Not Found "))