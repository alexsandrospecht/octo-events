(ns octo-events.db
  (:require [clojure.java.jdbc :as j]
            [clj-postgresql.core :as pg]))

(def db (pg/pool
          :host "postgres"
          :user "user"
          :dbname "events"
          :password "password"))

(defn query-events [issue]
  (j/query db ["SELECT * FROM events WHERE issue = ?" issue]))

(defn insert-event [issue]
  (j/insert! db "events" issue))
