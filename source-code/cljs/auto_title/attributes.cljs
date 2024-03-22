
(ns auto-title.attributes
    (:require [fruits.css.api       :as css]
              [react-references.api :as react-references]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sensor-attributes
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ; {:offset-y (px)(opt)
  ;  ...}
  ;
  ; @return (map)
  ; {:data-sensor-class (keyword)
  ;  :data-sensor-id (keyword)
  ;  :ref (function)
  ;  :style (map)}
  [sensor-id {:keys [offset-y]}]
  {:data-sensor-class (-> :auto-title)
   :data-sensor-id    (-> sensor-id)
   :ref               (react-references/set-reference-f sensor-id)
   :style             (if offset-y {:transform (-> offset-y css/px css/translate-y)})})
