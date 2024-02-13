
(ns auto-title.state
    (:require [reagent.core :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------


; @atom (metamorphic-content)
(defonce TITLE (reagent/atom nil))

; @atom (metamorphic-content)
(defonce TITLE-PLACEHOLDER (reagent/atom nil))

; @atom (boolean)
(defonce VISIBLE? (reagent/atom false))

; @ignore
;
; @atom (map)
(defonce SENSORS (reagent/atom {}))
