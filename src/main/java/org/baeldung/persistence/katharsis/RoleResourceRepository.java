package org.baeldung.persistence.katharsis;


import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;

import java.util.Optional;

import org.baeldung.persistence.dao.RoleRepository;
import org.baeldung.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleResourceRepository implements ResourceRepositoryV2<Role, Long> {

    @Autowired private RoleRepository roleRepository;

    @Override
    public Role findOne(Long id, QuerySpec querySpec) {
        return roleRepository.findByRoleId(id);
    }

    @Override
    public ResourceList<Role> findAll(QuerySpec querySpec) {
        return querySpec.apply(roleRepository.findAll());
    }

    @Override
    public ResourceList<Role> findAll(Iterable<Long> ids, QuerySpec querySpec) {
        return querySpec.apply(roleRepository.findAllIds(ids));
    }

    @Override
    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(roleRepository.getOne(id));
    }

    @Override
    public Class<Role> getResourceClass() {
        return Role.class;
    }

    @Override
    public <S extends Role> S create(S entity) {
        return save(entity);
    }

}