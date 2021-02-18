package nl.guyonmaissan.Garage.service;


import nl.guyonmaissan.Garage.model.Role;

import java.util.Collection;
import java.util.Map;

public interface RoleService {

    Collection<Role> getAllRole();
    Role getRoleById(Long id);
    Collection<Role> getRole(String name);
    long createRole(Role role);
    void updateRole(Long id, Role role);
    void partialUpdateRole(Long id, Map<String, String> fields);
    void deleteRole(Long id);
}
