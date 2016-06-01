(ns devdays.handlers
    (:require [re-frame.core :as re-frame]
              [devdays.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
  :increment-counter
  (fn  [db _]
    (update db :counter inc)))

(re-frame/register-handler
  :input-changed
  (fn [db [_ input]]
    (assoc db :input input)))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
