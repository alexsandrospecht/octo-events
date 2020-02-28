(ns octo-events.util
  (:require [cheshire.core :refer :all]))

(defn response [status & body]
  {:status  status
   :headers {"Content-Type" "application/json"}
   :body    body})

(defn key-not-found []
  (generate-string {:reason "Invalid body! Issue not found!"} {:pretty true}))

(defn resource-not-found []
  (generate-string {:reason "Error, resource not found!"} {:pretty true}))