
(ns auto-title.state
    (:require [reagent.core :refer [atom] :rename {atom ratom}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @atom (metamorphic-content)
(defonce PLACEHOLDER (ratom nil))

; @atom (metamorphic-content)
(defonce TITLE (ratom nil))

; @atom (boolean)
(defonce VISIBLE? (ratom false))

; @ignore
;
; @atom (map)
(defonce SENSORS (ratom {}))
