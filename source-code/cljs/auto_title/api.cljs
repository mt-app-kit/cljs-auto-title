
(ns auto-title.api
    (:require [auto-title.state :as state]
              [auto-title.views :as views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; auto-title.state
(def TITLE    state/TITLE)
(def VISIBLE? state/VISIBLE?)

; auto-title.views
(def sensor views/sensor)
