(ns clojureexercise.AssociativeDestructuring)

(def satyam-ramawat {:f-name "Satyam"
                 :l-name "Ramawat"
                 :phone "9999888800"
                 :address {:street "S.G.Palya"
                           :city "Bangalore"
                           :state "Karnataka"
                           :zip "560029"}
                 :height 2.0
                 :weight 20.5
                 :hobbies ["Photography" "Cricket" "Travelling"]
                 :company "Open Source"
                 :title "Software Engineer"})

(defn print-contact-info
  [{:keys [f-name l-name phone company title height weight]
    {:keys [street city state zip]} :address
           [fav-hobby second-hobby] :hobbies}]
  (println f-name l-name "is the" title "at" company)
  (println "You can reach him at" phone)
  (println "He lives at" street city state zip)
  (println "Maybe you can write to him about" fav-hobby "or" second-hobby)
  (println "Calculated BMI:"(/ weight (* height height))))

(print-contact-info satyam-ramawat)
