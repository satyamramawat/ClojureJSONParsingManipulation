(ns testingclojure.JSONmanipulation)

(require '[clojure.data.json :as json])
(use 'clojure.java.io)


(defn jsonmanipulation []
  (def jsonone(json/read-str (slurp "c:/users/sr062324/desktop/json1.json"):key-fn keyword)) ;Reading First JSON File
  (def jsontwo(json/read-str (slurp "c:/users/sr062324/desktop/json2.json"):key-fn keyword)) ;Reading Second JSON File
  (def jsonthree(into [] (concat [jsonone][jsontwo])))                                       ;Binding Both JSON into one JSON file
  (println jsonthree)
  (with-open [wrtr (writer "c:/users/sr062324/desktop/json3.json")]                          ;Wrtiting to JSON
    (.write wrtr (json/write-str jsonthree))))
(jsonmanipulation)

