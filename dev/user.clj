(in-ns 'user)

(defn heap []
  (let [u (.getHeapMemoryUsage (java.lang.management.ManagementFactory/getMemoryMXBean))
        used (/ (.getUsed u) 1e6)
        total (/ (.getMax u) 1e6)]
    (format "Used: %.0f/%.0f MB (%.0f%%)" used total (/ used total 0.01))))

(println "Loaded system-wide user.clj!")

(defmacro perf-tools []
  '(do
     (require '[clj-async-profiler.core :as prof])
     (require '[clj-java-decompiler.core :refer [decompile]])
     (require '[clj-memory-meter.core :as mm])
     (require '[criterium.core :as crit])

     (.refer *ns* 'time+ #'user/time+)
     (.refer *ns* 'heap #'user/heap)))
