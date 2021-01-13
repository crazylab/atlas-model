### Data Product Type Definition
```text
|-- data_product
    |-- Name
    |-- Machine Name
    |-- Id
    |-- Description
    |-- Monitoring Dashboard Url
    |-- Source Code URL
    |-- Owner (Array)
        |-- id
        |-- name
        |-- email
        |-- owner_type (enum)
    |-- Created At
    |-- Last Updated At
    |-- inputs (relation: set of `data`)
        |-- Data Name
        |-- Description
        |-- Total Record Count
        |-- AD Group
        |-- Source System
        |-- Version
        |-- Ports (relation: set of `port`)
            |-- Data Schema (type: `avro_schema`)
            |-- Description
            |-- Created At
            |-- Last Updated At
    |-- outputs (relation: set of `data`)
        |-- Data Name
        |-- Description
        |-- Total Record Count
        |-- AD Group
        |-- Source System
        |-- Version
        |-- Ports (relation: set of `port`)
            |-- Data Schema (type: `avro_schema`)
            |-- Description
            |-- Created At
            |-- Last Updated At
```
To build the above type definition we require the followings..
- **Custom Entity Types:** data_product, data, port, sql_port (inherits `port`), batch_port (inherits `port`), owner
- **Enum Types:** owner_type, port_type
- **Relationships:** data_product_input_data, data_porudct_output_data, data_ports

Create [data_product](atlas_model_json/models/data_product.json) entity type:
```shell script
curl -i -XPOST \
    -H 'Content-Type: application/json' \
    -H 'Accept: application/json' \
    -u username:password \
    http://<ATLAS_URL>/api/atlas/v2/types/typedefs \
    -d @atlas_model_json/models/data_product.json
```
---
### Data Product Process Definition
```text
|-- dp_process
    |-- inputs (relation: set of `port`)
    |-- outputs  (relation: set of `port`)
```
- **Custom Entity Types:** dp_process
- **Relationships:** dp_process_input_port, dp_process_output_port

Create [dp_process](atlas_model_json/models/dp_process.json) entity type:
```shell script
curl -i -XPOST \
    -H 'Content-Type: application/json' \
    -H 'Accept: application/json' \
    -u username:password \
    http://<ATLAS_URL>/api/atlas/v2/types/typedefs \
    -d @atlas_model_json/models/dp_process.json
```
---
## Entity Creation
Create Patient Info `data_product` entities with lineage as per [patient.yaml](./atlas_model_json/entities/patient.yaml)
```shell script
curl -i -XPOST \
    -H 'Content-Type: application/json' \
    -H 'Accept: application/json' \
    -u username:password \
    http://<ATLAS_URL>/api/atlas/v2/entity/bulk \
    -d @atlas_model_json/entities/patient_info_entity.json

curl -i -XPOST \
    -H 'Content-Type: application/json' \
    -H 'Accept: application/json' \
    -u username:password \
    http://<ATLAS_URL>/api/atlas/v2/entity/bulk \
    -d @atlas_model_json/entities/patient_info_entity_2.json
```
---
## Generate Entity JSON
To generate the `data_product` entity in a programmatic way, run the `Main` Java code inside the `src` directory


## Delete entity
```shell script
curl -i -XDELETE \
  -u username:password \
  http://<ATLAS_URL>/api/atlas/v2/entity/bulk?guid={GUID}&guid={GUID}
```

***TODO***
---
- Understand Atlas usage of the schema tab
- Create lineage from DP to DP while having lineage between data as well
- Create a Process where there is a reference to a data 