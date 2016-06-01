(ns devdays.views
    (:require [re-frame.core :as re-frame]))

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "/about"} "go to About Page"]]
       [:div [:a {:href "/input"} "go to Input Page"]]
       [:div [:a {:href "/counter"} "go to Counter Page"]]])))

;; about

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:href "/"} "go to Home Page"]]]))

;; counter

; components
; (defn counting-component []
;   (let [counter(re-frame/subscribe [:counter])]
;     (fn []
;     [:div
;      "The foo " [:code "click-count"] " has value: "
;      @counter ". "
;      [:input {:type "button" :value "Click me!"
;               :on-click #(swap! counter inc)}]])))

(defn counting-component []
  (let [counter (re-frame/subscribe [:counter])]
    (fn []
      [:div
       "The bar " [:code "click-count"] " has value: "
       @counter ". "
       [:input {:type "button" :value "Click me!"
                :on-click #(re-frame/dispatch [:increment-counter])}]])))

(defn input-component []
  (let [input (re-frame/subscribe [:input])]
    (fn []
      [:div
        "Hello : " @input
        [:div
          [:input {:type "text" :value @input :on-change #(re-frame/dispatch [:input-changed (-> % .-target .-value)])}]
        ]
      ])))

(defn counter-panel []
  (fn []
    [:div "This is the Counter Page."
      [counting-component]
      [:div [:a {:href "/"} "go to Home Page"]]]))

(defn input-panel []
  (fn []
    [:div "This is the Input Page."
      [input-component]
      [:div [:a {:href "/"} "go to Home Page"]]]))


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :counter-panel [] [counter-panel])
(defmethod panels :input-panel [] [input-panel])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
