(ns octo-events.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [cheshire.core :refer :all]
            [octo-events.db :as db]
            [octo-events.model :as m]
            [octo-events.util :as u])
  (:gen-class))

(defn extract-event [req]
  (-> (:body req)
      (slurp)
      (parse-string true)))

(defn new-event [req]
  (let [event (extract-event req)
        issue (:issue event)]

    (if issue
      (do
        (db/insert-event (m/issue-schema (:number issue) (str event)))
        {:status 201})
      (u/response 400 (u/key-not-found)))))

(defn query [req]
  (u/response 200
              (generate-string
                (->> (:id (:params req))
                     (db/query-events)
                     (map :event)
                     (map str)
                     (map clojure.edn/read-string))
                {:pretty true})))

(defroutes
  app-routes
  (POST "/events" [] new-event)
  (GET "/issues/:id/events" [] query)
  (route/not-found (u/response 404 (u/resource-not-found))))

(defn -main
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (server/run-server (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))


