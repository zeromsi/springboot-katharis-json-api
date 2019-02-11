# springboot-katharis-json-api
- Katharsis development team developed a repository ```ResourceRepositoryV2```, ```RelationshipRepositoryV2``` and ```BulkRelationshipRepositoryV2``` that Usages ```QuerySpec``` to return resultset in JSON API .
- An Entity specific ```Repository``` implements ```ResourceRepositoryV2```, then overrides its methods. 
- For Relationship between two entities, developer must create repository like  ```UserToRoleRelationshipRepository``` or ```RoleToUserRelationshipRepository``` that must implements ``RelationshipRepositoryV2``` or ```BulkRelationshipRepositoryV2```.
It depends on API demand. For example,
If frontend team needs Role by User, then create ```UserToRoleRelationshipRepository```.

## Repositories
### ResourceRepositoryV2
Base repository which is used to operate on resources. Each resource should have a corresponding repository imple-
mentation. It consist of five basic methods which provide a CRUD for a resource and two parameters: the first is a
type of a resource and the second is a type of the resource’s identifier.
The methods are as follows:
- findOne(ID id, QuerySpec querySpec) Search one resource with a given ID. If a resource cannot
be found, a ResourceNotFoundException exception should be thrown. It should return an entity with associated
relationships.
- findAll(QuerySpec querySpec) Search for all of the resources. An instance of QuerySpec can be used
if necessary. If no resources can be found an empty Iterable or null must be returned. It should return entities
with associated relationships.
- findAll(Iterable<ID>ids, QuerySpec querySpec) Search for resources constrained by a list of
identifiers. An instance of QuerySpec can be used if necessary. If no resources can be found an empty Iterable
or null must be returned. It should return entities with associated relationships.
- save(S entity) Saves a resource. It should not save relating relationships. A Returning resource must
include assigned identifier created for the instance of resource. This method should be able to both create a new
resource and update existing one.
- delete(ID id) Removes a resource identified by id parameter.
  
### RelationshipRepositoryV2
Each relationship defined in Katharsis (annotation @JsonApiToOne and @JsonApiToMany) must have a relationship
repository defined.
Base unidirectional repository responsible for operations on relations. All of the methods in this interface have field-
Name field as their last parameter to solve the problem of many relationships between the same resources.
- setRelation(T source, D_ID targetId, String fieldName) Sets a resource defined by tar-
getId to a field fieldName in an instance source. If no value is to be set, null value is passed.
- setRelations(T source, Iterable<D_ID> targetIds, String fieldName) Sets re-
sources defined by targetIds to a field fieldName in an instance source. This is a all-or-nothing operation, that is
no partial relationship updates are passed. If no values are to be set, empty Iterable is passed.
- addRelations(T source, Iterable<D_ID> targetIds, String fieldName) Adds rela-
tionships to a list of relationships.
- removeRelations(T source, Iterable<D_ID> targetIds, String fieldName)
moves relationships from a list of relationships.
Re-
- findOneTarget(T_ID sourceId, String fieldName, QuerySpec querySpec) Finds one
field’s value defined by fieldName in a source defined by sourceId.
- findManyTargets(T_ID sourceId, String fieldName, QuerySpec querySpec) Finds
an Iterable of field’s values defined by fieldName in a source defined by sourceId .

### BulkRelationshipRepositoryV2
BulkRelationshipRepositoryV2 extends RelationshipRepositoryV2 and provides an additional findTargets
method. It allows to fetch a relation for multiple resources at once. It is recommended to make use of this implemen-
tation if a relationship is loaded frequently (either by a eager declaration or trough the include parameter) and it is
costly to fetch that relation. RelationshipRepositoryBase provides a default implementation where findOneTarget
and findManyTargets forward calls to the bulk findTargets.

### Query parameters
Katharsis passes JSON API query parameters to repositories trough a QuerySpec parameter. It holds request parame-
ters like sorting and filtering specified by JSON API. The subsequent sections will provide a number of example.

### Filtering
Resource filtering can be achieved by providing parameters which start with filter. The format for filters:
filter[ResourceType][property|operator]([property|operator])* = "value"
- GET /tasks/?filter[name]=Super task
- GET /tasks/?filter[name][EQ]=Super task
- GET /tasks/?filter[tasks][name]=Super task
- GET /tasks/?filter[tasks][name]=Super task&filter[tasks][dueDate]=2015-10-01
QuerySpec uses the EQ operator if no operator was provided. Custom operators can be registered with
DefaultQuerySpecDeserializer.addSupportedOperator(..). The default operator can be overrid-
den by setting DefaultQuerySpecDeserializer.setDefaultOperator(...).

### Pagination
Pagination for the repositories can be achieved by providing page parameter.
page[offset|limit] = "value", where value is an integer
The format for pagination:
Example:
- GET /tasks/?page[offset]=0&page[limit]=10
### Sparse Fieldsets
Information about fields to include in the response can be achieved by providing fields parameter.
- GET /tasks/?fields=name
- GET /tasks/?fields[projects]=name,description&include=projects

### Inclusion of Related Resources
Information about relationships to include in the response can be achieved by providing include parameter. The
format for fields: include[ResourceType] = "property(.property)*"
#### Examples:
- GET /tasks/?include[tasks]=project
- GET /tasks/1/?include[tasks]=project
- GET /tasks/?include[tasks]=author
- GET /tasks/?include[tasks][]=author&include[tasks][]=comments
- GET /tasks/?include[projects]=task&include[tasks]=comments
- GET /tasks/?include[projects]=task&include=comments (QuerySpec example)
