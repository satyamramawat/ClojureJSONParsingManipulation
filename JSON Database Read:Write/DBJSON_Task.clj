(ns clojureexercise.test
(:require [clojure.java.jdbc :as sql]))   
(require '[clojure.data.json :as json])

;; Function to count the no. of patient in JSON
(defn countoc []
  (def file (slurp "c:/users/sr062324/desktop/gistfile1.json"))
  (def countresource  
    (->> file
           (re-seq #"\bresource\b")
           count)))

;; Function to fetch the Required values from JSON
(defn fetcher[number]
  (def patient_id(get-in json ["entry" number "resource" "id"]))                 ;Fetching Patien ID 
  (def dob(get-in json ["entry" number "resource" "birthDate"]))                 ;Fetching BirthDate of Patient
  (def gender(get-in json ["entry" number "resource" "gender"]))                 ;Fetching Gender of Patient
  (def first_name(get-in json ["entry" number "resource" "name" 0 "given" 0]))   ;Fetching FirstName of Patient
  (def last_name(get-in json ["entry" number "resource" "name" 0 "family" 0])))  ;Fetching LastName of Patient
  
  
;; Function for Database connectivity and Insert into table
(defn dbconnect []
  (def db{   
         :classname "com.mysql.jdbc.Driver" 
         :subprotocol "mysql"
         :subname "//127.0.0.1:3306/testdb"
         :user "root"
         :password "password"}))
  
;;Inserting JSON Data into Database
 (defn insertdata []
  (sql/insert! db :patientinfo
  {:id patient_id :firstname first_name :lastname last_name 
   :birthdate dob :gender gender }))
  

;; Main Function
(defn dbjsonparser []
  (dbconnect)
  (countoc)
  (def json (json/read-str(slurp "c:/users/sr062324/desktop/gistfile1.json"))) ;Reading JSON file
(loop [x 0]
    (when (< x countresource)
      (fetcher x)
      (insertdata)
      (recur(inc x))))
(println "Done with the operations, Please check DB")
(println "Please Enter the input YES if you want to see the output in REPL, else NO to terminate")
(def read_input (read-line))
(case read_input "YES" (println(sql/query db["SELECT * from patientinfo"]))
      "NO" (System/exit 0)
      (println "Input is neither YES nor NO")))


;;Calling the Main Function
(dbjsonparser)

