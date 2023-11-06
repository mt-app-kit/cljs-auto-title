
### auto-title.api

Functional documentation of the auto-title.api ClojureScript namespace

---

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > auto-title.api

### Index

- [sensor](#sensor)

---

### sensor

```
@param (keyword)(opt) sensor-id
@param (map) sensor-props
{:offset (px)(opt)
 :placeholder (metamorphic-content)(opt)
 :title (metamorphic-content)(opt)}
```

```
@usage
[sensor {...}]
```

```
@usage
[sensor :my-sensor {...}]
```

<details>
<summary>Source code</summary>

```
(defn sensor
  ([sensor-props]
   [sensor (random/generate-keyword) sensor-props])

  ([sensor-id {:keys [offset] :as sensor-props}]
   (reagent/lifecycles {:component-did-mount    (fn []  (utils/title-sensor-did-mount-f    sensor-id sensor-props))
                        :component-will-unmount (fn []  (utils/title-sensor-will-unmount-f sensor-id sensor-props))
                        :component-did-update   (fn [%] (utils/title-sensor-did-update-f   sensor-id %))
                        :reagent-render         (fn []  (let [element-id (hiccup/value sensor-id "auto-title-sensor")]
                                                             [:div {:style (if offset {:transform (-> offset css/px css/translate-y)})
                                                                    :id element-id}]))})))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [auto-title.api :refer [sensor]]))

(auto-title.api/sensor ...)
(sensor                ...)
```

</details>

---

<sub>This documentation is generated with the [clj-docs-generator](https://github.com/bithandshake/clj-docs-generator) engine.</sub>

