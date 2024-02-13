
(ns auto-title.utils
    (:require [auto-title.side-effects :as side-effects]
              [auto-title.state        :as state]
              [reagent.tools.api :as reagent.tools]
              [time.api                :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn title-sensor-did-mount-f
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [sensor-id sensor-props]
  ; When a 'sensor' component mounts into the React tree, the previous one might
  ; currently unmounting with an ongoing disappearing effect.
  ; Therefore, the currently mounted component has to wait before it applies the 'set-title!'
  ; function. Otherwise, the disappearing title would change in the last moments of its disappearing.
  (letfn [(f0 [] (side-effects/set-title! sensor-id sensor-props))]
         (time/set-timeout! f0 150))
  (swap! state/SENSORS assoc sensor-id sensor-props)
  (side-effects/setup-intersection-observer! sensor-id sensor-props))

(defn title-sensor-will-unmount-f
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [sensor-id sensor-props]
  (swap! state/SENSORS dissoc sensor-id)
  (side-effects/remove-intersection-observer! sensor-id sensor-props))

(defn title-sensor-did-update-f
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (?) %
  [sensor-id %]
  (let [[_ sensor-props] (reagent.tools/arguments %)]
       (side-effects/update-title! sensor-id sensor-props)))
