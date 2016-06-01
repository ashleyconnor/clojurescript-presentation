(ns devdays.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :counter
 (fn [db]
   (reaction (:counter @db))))

(re-frame/register-sub
  :input
  (fn [db]
    (reaction (:input @db))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))
