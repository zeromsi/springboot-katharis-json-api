# springboot-katharis-json-api
Katharsis is a library that helps to create JSON API easily. We can use JPA and katharsis specific repositories.
Katharsis development team developed a repository ```ResourceRepositoryV2``` that Usages ```QuerySpec``` to return JSON API resultset.
An Entity specific ```Repository``` implements ```ResourceRepositoryV2```, then overrides its methods. 
For Relationship between two entities, developer must create repository like  ```UserToRoleRelationshipRepository``` or ```RoleToUserRelationshipRepository```.
It depends on API demand. For example,
If frontend team needs Role by User, then create ```UserToRoleRelationshipRepository```.
