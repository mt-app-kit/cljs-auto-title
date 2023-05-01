
# cljs-auto-title

### Overview

The <strong>cljs-auto-title</strong> is a simple ClojureScript/Reagent intersection
sensor that you can place right above any title element in your application in order
to detect whether they are in the viewport.
When a title sensor leaves the viewport it sets its value into a Reagent atom that
can update the title in your application header.

### deps.edn

```
{:deps {bithandshake/cljs-auto-title {:git/url "https://github.com/bithandshake/cljs-auto-title"
                                      :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/cljs-auto-title/tree/release).

### Changelog

You can track the changes of the <strong>cljs-auto-title</strong> library [here](CHANGES.md).

# Usage

### Index

- [How to place a title sensor?](#how-to-place-a-title-sensor)

- [How to display the auto title value?](#how-to-display-the-auto-title-value)

### How to place a title sensor?

The [`auto-title.api/sensor`](documentation/cljs/auto-title/API.md/#sensor) component
could be placed right above or belove any title element.

```
(defn my-app
  []
  [:div#my-app [:section [sensor {:title "My application"}]
                         [:div "My application"]]])
```

### How to display the auto title value?

The [`auto-title.api/TITLE`](documentation/cljs/auto-title/API.md/#title) Reagent
atom stores the actual title set by any sensor that leaved the viewport.

In this example the fixed positioned header element is always visible and it displays
the `My application` title if the title sensor leaved the viewport and the actual
title element is no longer visible.

```
(defn my-app
  []
  [:div#my-app [:section [sensor {:title "My application"}]
                         [:div "My application"]]])
               [:header {:style {:position :fixed}} @TITLE]
```
