
(ns auto-title.api
    (:require [auto-title.env   :as env]
              [auto-title.views :as views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Demo
;
; @usage
; (defn my-header-bar
;   []
;   [:div {:style {:left 0 :position :fixed :top 0 :width :100%}}
;         (get-actual-title)])
;
; (defn my-section
;   [background-color title]
;   [:div {:style {:background-color background-color :height :50vh}}
;         [sensor {:title title}]
;         [:h1 title]])
;
; (defn my-ui
;   []
;   [:<> [my-header-bar]
;        [my-section "#fff" "My section #1"]
;        [my-section "#ddd" "My section #2"]
;        [my-section "#fff" "My section #3"]
;        [my-section "#ddd" "My section #4"]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (auto-title.env/*)
(def get-actual-title env/get-actual-title)

; @redirect (auto-title.views/*)
(def sensor views/sensor)
