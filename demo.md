**Lineage Scenarios**
---
`demographic` --> Spark Job 1 --> `patient_count_by_location` 


`demographic`, `health_record` --> Spark Job 2 --> `patient_count_by_disease` 


***
**Tag Propagation**
---
For this example we will use PII as a classifier.

- When demographic data is marked as PII, it should mark all the downstream system as PII.
    - demographic
    - patient_count_by_disease
    - patient_count_by_location
    - all the port associated with these data 