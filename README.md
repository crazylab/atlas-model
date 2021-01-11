Create Types:
```shell script
POST http://localhost:21000/api/atlas/v2/types/typedefs
data_product.json
```

Entity Creation
```shell script
POST http://localhost:21000/api/atlas/v2/entity/bulk
data_product_entity.json
```

Delete Entity
```shell script
DELETE /api/atlas/v2/entity/bulk?guid={GUID}&guid={GUID}
```


**Tasks**
---
- Understand Atlas usage of the schema tab
- Create lineage from DP to DP while having lineage between data as well
- Create a Process where there is a reference to a data 