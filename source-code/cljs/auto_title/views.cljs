
(ns auto-title.views
    (:require [auto-title.utils :as utils]
              [css.api          :as css]
              [hiccup.api       :as hiccup]
              [random.api       :as random]
              [reagent.core     :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sensor
  ; @param (keyword)(opt) sensor-id
  ; @param (map) sensor-props
  ; {:offset (px)(opt)
  ;  :placeholder (metamorphic-content)(opt)
  ;  :title (metamorphic-content)(opt)}
  ;
  ; @usage
  ; [sensor {...}]
  ;
  ; @usage
  ; [sensor :my-sensor {...}]
  ([sensor-props]
   [sensor (random/generate-keyword) sensor-props])

  ([sensor-id {:keys [offset] :as sensor-props}]
   ; The following cases have to handled:
   ; 1. More than one 'sensor' component mounted into the React tree.
   ; 2. A 'sensor' component unmounts just after when the next one mounted into the React tree.
   ; 3. A 'sensor' component updates after it has mounted into the React tree (e.g., the title changes).
   ; 4. A 'sensor' component mounts into the React tree outside the viewport.
   ; 5. A 'sensor' component mounts into the React tree inside the viewport.
   (reagent/create-class {:component-did-mount    (fn []  (utils/title-sensor-did-mount-f    sensor-id sensor-props))
                          :component-will-unmount (fn []  (utils/title-sensor-will-unmount-f sensor-id sensor-props))
                          :component-did-update   (fn [%] (utils/title-sensor-did-update-f   sensor-id %))
                          :reagent-render         (fn []  (let [element-id (hiccup/value sensor-id "auto-title-sensor")]
                                                               [:div {:style (if offset {:transform (-> offset css/px css/translate-y)})
                                                                      :id element-id}]))})))
