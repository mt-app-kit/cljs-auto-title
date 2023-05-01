
(ns auto-title.side-effects
    (:require [auto-title.state          :as state]
              [dom.api                   :as dom]
              [hiccup.api                :as hiccup]
              [intersection-observer.api :as intersection-observer]
              [metamorphic-content.api   :as metamorphic-content]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn show-title!
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [_ _]
  (reset! state/VISIBLE? true))

(defn hide-title!
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [_ _]
  (reset! state/VISIBLE? false))

(defn set-title!
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ; {:placeholder (metamorphic-content)(opt)
  ;  :title (metamorphic-content)(opt)}
  [_ {:keys [placeholder title]}]
  (if (-> title metamorphic-content/compose empty?)
      (reset! state/TITLE placeholder)
      (reset! state/TITLE title)))

(defn clear-title!
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [_ _]
  (reset! state/TITLE nil))

(defn update-title!
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [sensor-id sensor-props]
  (set-title! sensor-id sensor-props))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn setup-intersection-observer!
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  ; {:placeholder (metamorphic-content)(opt)
  ;  :title (metamorphic-content)(opt)}
  [sensor-id {:keys [placeholder title] :as sensor-props}]
  (letfn [(f [intersecting?] (if intersecting? (hide-title! sensor-id sensor-props)
                                               (show-title! sensor-id sensor-props)))]
         (let [element-id (hiccup/value sensor-id "auto-title-sensor")]
              (intersection-observer/setup-observer! element-id f))))

(defn remove-intersection-observer!
  ; @ignore
  ;
  ; @param (keyword) sensor-id
  ; @param (map) sensor-props
  [sensor-id _]
  (let [element-id (hiccup/value sensor-id "auto-title-sensor")]
       (intersection-observer/remove-observer! element-id)))
