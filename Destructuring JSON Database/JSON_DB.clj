(ns clojureexercise.JSON_DB
(:require [clojure.java.jdbc :as sql]))  
(require '[clojure.data.json :as json])
(use 'clojure.java.io)

;;Reading JSON file into Global Variable
(def satyam-ramawat(json/read-str(slurp "c:/users/sr062324/desktop/satyam.json"):key-fn keyword))

;; Main Function
(defn person-info
  [{:keys [f-name l-name phone company title height weight street city state zip]}]
  
  ;DB Connection
  (def db{   
         :classname "com.mysql.jdbc.Driver" 
         :subprotocol "mysql"
         :subname "//127.0.0.1:3306/testdb"
         :user "root"
         :password "password"})
  
  ;Calculating BMI
  (def bmi (/ weight (* height height)))
  
  ;Inserting JSON Data into Database
  (sql/insert! db :personinfo
  {:firstname f-name :lastname l-name :phone phone 
   :street street :city city :state state :zip zip 
   :company company :title title :height height :weight weight :bmi bmi })
  (println "Data Inserted")

  ;View the content of table
  (println(sql/query db["SELECT * from personinfo"])))
  

(person-info satyam-ramawat) ;; Passed the JSON data as Argument 
