(ns octo-events.util
  (:require [cheshire.core :refer :all]))

(defn response
  [status & body]
  {:status  status
   :headers {"Content-Type" "application/json"}
   :body    body})

(defn- error-message [message]
  (generate-string {:reason message} {:pretty true}))

(defn key-not-found []
  (error-message "Invalid body! Issue not found!"))

(defn resource-not-found []
  (error-message "Error, resource not found!"))