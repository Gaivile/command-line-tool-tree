(ns birch.core
 (:require [lumo.core :refer [*command-line-args*]]
           [clojure.string :as str]))

;; (println "Hello world!")

;; (println ">>>" (first *command-line-args*))

(def node-fs (js/require "fs"))
(def node-path (js/require "path"))

(def read-dir (.-readdirSync node-fs))
(def stat (.-statSync node-fs))
(def path-join (.-join node-path))

(defn directory? [f]
  (.isDirectory (stat f)))

(def t-branch "├──")
(def i-branch "│  ")
(def l-branch "└──")
(def spacer "   ")

(def cli-color (js/require "cli-color"))
(def blue-text (.-blue cli-color))
(def green-text (.-green cli-color))

(declare tree-entry)

(defn child-entries [path]
  (map #(tree-entry path %) (read-dir path)))

(defn tree-entry [path name]
  (let [path (path-join path name)
        is-dir (directory? path)]
    {:name name
     :directory? is-dir
   :children (if is-dir (child-entries path ))}))

(defn render-tree [{:keys [name children directory?]}]
  (cons
   (if directory?
     (blue-text name)
     (green-text name))
    (mapcat (fn [child index]
                  (let [[first & rest] (render-tree child)
                        last? (= index (dec (count children)))
                        prefix-first (if last? l-branch t-branch)
                        prefix-last (if last? spacer i-branch)]
                    (cons (str prefix-first first)
                          (map #(str prefix-last %) rest ))))
                children
                (range))))

(defn  -main [dir]
  (->> (tree-entry "" dir)
       render-tree
       (str/join "\n")
       print))
