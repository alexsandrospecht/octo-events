(ns octo-events.model)

(defn issue-schema
  [issue event]
  {:issue issue :event event})
