package br.com.dgc.simplechurch.starters;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.dgc.simplechurch.role.model.Role;
import br.com.dgc.simplechurch.role.service.RoleService;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.service.UserService;
import jakarta.transaction.Transactional;

@Component
public class AdminUserAndRoleStarter implements ApplicationRunner {

    private UserService userService;
    private RoleService roleService;

    public AdminUserAndRoleStarter(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private final String ADMIN_EMAIL = "admin@admin.com";
    private final String ADMIN_FIRST_NAME = "Admin";
    private final String ADMIN_LAST_NAME = "Admin";
    private final String ADMIN_PASSWORD = "Admin";
    private final String ADMIN_ROLE_NAME = "Admin";

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        User user = this.userService
                .createUser(new User(ADMIN_FIRST_NAME, ADMIN_LAST_NAME, ADMIN_EMAIL, ADMIN_PASSWORD));
        Role role = this.roleService.createRole(new Role(ADMIN_ROLE_NAME));
        this.userService.addRoleToUser(user.getId(), role.getId());
    }

}
