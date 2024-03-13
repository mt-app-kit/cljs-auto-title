
(ns auto-title.api
    (:require [auto-title.env :as env]
              [auto-title.views :as views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Demo
;
; 

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (auto-title.env/*)
(def get-actual-title env/get-actual-title)

; @redirect (auto-title.views/*)
(def sensor views/sensor)
