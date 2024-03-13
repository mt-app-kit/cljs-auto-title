
(ns auto-title.views
    (:require [auto-title.side-effects :as side-effects]
              [auto-title.attributes :as attributes]
              [fruits.random.api :as random]
              [reagent.core      :as reagent]
              [intersection-observer.api :as intersection-observer]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sensor-lifecycles
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [sensor-id sensor-props]
  (reagent/create-class {:component-did-mount    (fn [_ _]   (side-effects/sensor-did-mount    sensor-id sensor-props))
                         :component-will-unmount (fn [_ _]   (side-effects/sensor-will-unmount sensor-id sensor-props))
                         :component-did-update   (fn [_ _ %] (side-effects/sensor-did-update   sensor-id sensor-props %))
                         :reagent-render         (fn [_ _]   (let [callback-f (fn [%] (side-effects/sensor-intersects sensor-id sensor-props %))]
                                                                  [:hr (attributes/sensor-attributes sensor-id sensor-props)]
                                                                  [intersection-observer/sensor sensor-id {:callback-f callback-f}]))}))

(defn sensor
  ; @description
  ; Scroll sensor element that keeps track of section titles of the page and automatically sets the actual title
  ; value (in the auto title state) depending on which sensor is closest to the viewport upper boundary from the outside.
  ;
  ; @param (keyword)(opt) sensor-id
  ; @param (map) sensor-props
  ; {:offset-y (px)(opt)
  ;  :title (multitype-content)(opt)
  ;  :title-placeholder (multitype-content)(opt)}
  ;
  ; @usage
  ; [sensor {:title "My title"}]
  ;
  ; @usage
  ; [sensor :my-sensor {:title "My title"}]
  ([sensor-props]
   [sensor (random/generate-keyword) sensor-props])

  ([sensor-id sensor-props]
   (fn [_ sensor-props] ; Keeps the sensor ID even if it's updated.
       [sensor-lifecycles sensor-id sensor-props])))
