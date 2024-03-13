
(ns auto-title.env
    (:require [common-state.api :as common-state]
              [dom.api :as dom]
              [react-references.api :as react-references]
              [multitype-content.api :as multitype-content]
              [fruits.math.api :as math]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-actual-title
  ; @description
  ; Returns the last set title (from the auto title state).
  ;
  ; @return (multitype-content)
  []
  (common-state/get-state :auto-title :actual-title))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn detect-closest-sensor
  ; @ignore
  ;
  ; @description
  ; Returns the ID of the sensor that is the closest to the upper horizon of the viewport from the outside.
  ;
  ; @usage
  ; (detect-closest-sensor)
  ; =>
  ; [:my-sensor -420]
  ;
  ; @return (vector)
  ; [(keyword) sensor-id
  ;  (px) sensor-y]
  []
  (let [mounted-sensors (common-state/get-state :auto-title :mounted-sensors)]
       (letfn [(f0 [[closest-id closest-y] sensor-id _]
                   (let [sensor-reference (react-references/get-reference sensor-id)
                         sensor-y         (dom/get-element-relative-top   sensor-reference)]
                        (if closest-id (cond (-> sensor-y math/not-negative?) [closest-id closest-y]
                                             (-> closest-y (< sensor-y))      [sensor-id  sensor-y]
                                             (-> closest-y (>= sensor-y))     [closest-id closest-y])
                                       (cond (-> sensor-y math/negative?)     [sensor-id  sensor-y]))))]
              (reduce-kv f0 [] mounted-sensors))))

(defn detect-closest-title
  ; @ignore
  ;
  ; @description
  ; Returns the title of the sensor that is the closest to the upper horizon of the viewport from the outside.
  ;
  ; @usage
  ; (detect-closest-title)
  ; =>
  ; "My title"
  ;
  ; @return (multitype-content)
  []
  (let [[sensor-id _] (detect-closest-sensor)]
       (if sensor-id (let [title             (common-state/get-state :auto-title :mounted-sensors sensor-id :title)
                           title-placeholder (common-state/get-state :auto-title :mounted-sensors sensor-id :title-placeholder)]
                          (multitype-content/compose title title-placeholder)))))
