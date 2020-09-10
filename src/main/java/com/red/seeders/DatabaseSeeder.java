package com.red.seeders;

import com.red.model.Role;
import com.red.model.User;
import com.red.services.role.RoleService;
import com.red.services.user.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseSeeder {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public DatabaseSeeder() {
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedCreateAdmin();
    }

    private void seedRoleTable() {

        List<Role> rs = new ArrayList<>();
        Iterable<Role> iterable = roleService.findAll();

        iterable.forEach(rs::add);

        if (rs.size() < 1) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");

            Role userRole = new Role();
            userRole.setName("ROLE_USER");

            roleService.save(adminRole);
            roleService.save(userRole);

            logger.info("Role table seeded");
        } else {
            logger.trace("Role Seeding Not Required");
        }
    }

    private void seedCreateAdmin() {
        Optional<User> u = userService.findByEmail("hai@mail.com");
        if (!u.isPresent()) {
            String password = bCryptPasswordEncoder.encode("123456");
            User user = new User();
            user.setFirstName("Le");
            user.setLastName("Hai");
            user.setEmail("hai@mail.com");
            user.setPassword(password);

            Role role = roleService.findByName("ROLE_ADMIN");
            if (role != null) {
                List<Role> list = new ArrayList<>();
                list.add(role);
                user.setRoles(list);
            }
            user.setEnabled(true);
            user.setAccountNonLocked(true);
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);

            userService.save(user);
        }
    }
}