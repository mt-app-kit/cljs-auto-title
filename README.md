
# cljs-auto-title

### Overview

The <strong>cljs-auto-title</strong> is a simple ClojureScript/Reagent intersection
sensor that you can place right above any title element in your application in order
to detect whether they are in the viewport.
When a title's sensor leaves the viewport it sets its content into a Reagent atom that
can update the title in your application header.

> UI components in this library are Reagent components. Check out the [Reagent Project](https://github.com/reagent-project/reagent).

### deps.edn

```
{:deps {bithandshake/cljs-auto-title {:git/url "https://github.com/bithandshake/cljs-auto-title"
                                      :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/cljs-auto-title/tree/release).

### Documentation

The <strong>cljc-validator</strong> functional documentation is [available here](https://bithandshake.github.io/cljs-auto-title).

### Changelog

You can track the changes of the <strong>cljs-auto-title</strong> library [here](CHANGES.md).

# Usage

> Some parameters of the following functions and some further functions are not discussed in this file.
  To learn more about the available functionality, check out the [functional documentation](documentation/COVER.md)!

### Index

- [How to place a title sensor?](#how-to-place-a-title-sensor)

- [How to display the auto title value?](#how-to-display-the-auto-title-value)

- [How to use the placeholder value?](#how-to-use-the-placeholder-value)

### How to place a title sensor?

The [`auto-title.api/sensor`](documentation/cljs/auto-title/API.md/#sensor) component
could be placed right above or below any title element.

```
(defn my-app
  []
  [:div#my-app [:section [sensor {:title "My application"}]
                         [:div "My application"]]])
```

### How to display the auto title value?

The [`auto-title.api/TITLE`](documentation/cljs/auto-title/API.md/#title) Reagent
atom stores the actual title set by any sensor that left the viewport.

In this example the fixed positioned header element is always visible and it displays
the `My application` title if the title sensor left the viewport and the actual
title element is no longer visible.

```
(defn my-app
  []
  [:div#my-app [:section [sensor {:title "My application"}]
                         [:div "My application"]]
               [:header {:style {:position :fixed}} @TITLE]])
```

### How to use the placeholder value?

The [`auto-title.api/PLACEHOLDER`](documentation/cljs/auto-title/API.md/#title)
Reagent atom stores the actual title placeholder set by any sensor that left the viewport.

```
(defn my-app
  []
  (let [app-title (get-app-title-from-state ...)]
       [:div#my-app [:section [sensor {:title app-title :placeholder "My placeholder"}]
                              [:div "My application"]]
                    [:header {:style {:position :fixed}} (or @TITLE @PLACEHOLDER)]])
```
