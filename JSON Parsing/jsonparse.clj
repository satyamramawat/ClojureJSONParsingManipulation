(ns testingclojure.jsonparse)
(require '[clojure.data.json :as json])
(use 'clojure.java.io)

;; Read and Write Function with validation
(defn readjson[]
(def id (Integer/parseInt(read-line)))
(def names (read-line))                

 (if (and (= (type id) java.lang.Integer) (=(type names) java.lang.String))
   (with-open [wrtr (writer "c:/users/sr062324/desktop/New.json")]                           ;wrtiting to JSON
       (.write wrtr (json/write-str {:id id :Name names})))
   (System/exit 0))
 
 (def readjson(json/read-str (slurp "c:/users/sr062324/desktop/Test.json"):key-fn keyword))  ;Reading from JSON
 (println readjson))
(readjson)

;; Get the keys function 
(defn getkey []
  (def getkeys(map key readjson)))                                                           ;Get all the keys from MAP
(getkey)

;; Print the keys function 
(defn printkeys []
  (println getkeys))
(printkeys)

;; Print the values function 
(defn printvalue [value]
(println(select-keys readjson [value]))) 

;; Generating a Values from user input
(defn generatekey[]
  (loop [x 0]
    (when (< x 5)
      (def value (read-line))
      (printvalue (keyword value))
      (recur(inc x)))))
(generatekey)


