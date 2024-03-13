
(ns auto-title.side-effects
    (:require [reagent.tools.api :as reagent.tools]
              [common-state.api :as common-state]
              [auto-title.env :as env]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn update-title!
  ; @ignore
  ;
  ; @usage
  ; (update-title!)
  []
  (let [closest-title (env/detect-closest-title)]
       (common-state/assoc-state! :auto-title :actual-title closest-title)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sensor-did-mount
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ;
  ; @usage
  ; (sensor-did-mount :my-sensor {...})
  [sensor-id sensor-props]
  (common-state/assoc-state! :auto-title :mounted-sensors sensor-id sensor-props)
  (update-title!))

(defn sensor-did-update
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ; @param (Reagent component object) %
  ;
  ; @usage
  ; (sensor-did-update :my-sensor {...})
  [sensor-id _ %]
  (let [[_ sensor-props] (reagent.tools/arguments %)]
       (common-state/assoc-state! :auto-title :mounted-sensors sensor-id sensor-props)
       (update-title!)))

(defn sensor-will-unmount
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ;
  ; @usage
  ; (sensor-will-unmount :my-sensor {...})
  [sensor-id _]
  (common-state/dissoc-state! :auto-title :mounted-sensors sensor-id)
  (update-title!))

(defn sensor-intersects
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ; @param (boolean) sensor-intersects?
  ;
  ; @usage
  ; (sensor-intersects :my-sensor {...} true)
  [_ _ _]
  (update-title!))
