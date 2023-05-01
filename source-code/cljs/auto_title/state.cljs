
(ns auto-title.state
    (:require [reagent.api :refer [ratom]]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @atom (metamorphic-content)
(defonce TITLE (ratom nil))

; @atom (boolean)
(defonce VISIBLE? (ratom false))

; @ignore
;
; @atom (map)
(defonce SENSORS (ratom {}))
