
(ns auto-title.attributes
    (:require [react-references.api :as react-references]
              [fruits.css.api :as css]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sensor-attributes
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ; {:offset (px)(opt)
  ;  ...}
  ;
  ; @return (map)
  ; {:data-sensor-class (keyword)
  ;  :data-sensor-id (keyword)
  ;  :ref (function)
  ;  :style (map)}
  [sensor-id {:keys [offset]}]
  {:data-sensor-class (-> :auto-title)
   :data-sensor-id    (-> sensor-id)
   :ref               (react-references/set-reference-f sensor-id)
   :style             (if offset {:transform (-> offset css/px css/translate-y)})})
