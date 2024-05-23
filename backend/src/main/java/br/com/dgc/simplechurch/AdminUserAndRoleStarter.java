package br.com.dgc.simplechurch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.dgc.simplechurch.role.model.Role;
import br.com.dgc.simplechurch.role.service.RoleService;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.service.UserService;

@Component
public class AdminUserAndRoleStarter implements ApplicationRunner {

    private UserService userService;
    private RoleService roleService;
    Logger logger = LoggerFactory.getLogger(AdminUserAndRoleStarter.class);

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
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Initiating admin creation");

        User user = this.userService.readUserByEmail(ADMIN_EMAIL).orElse(null);
        Role role = this.roleService.readRoleByName(ADMIN_ROLE_NAME).orElse(null);

        if (user == null) {
            logger.info("User doesn't exist. Starting creation");
            user = this.userService
                    .createUser(new User(ADMIN_FIRST_NAME, ADMIN_LAST_NAME, ADMIN_EMAIL, ADMIN_PASSWORD));
        }
        if (role == null) {
            logger.info("Role doesn't exist. Starting creation");
            role = this.roleService.createRole(new Role(ADMIN_ROLE_NAME));
        }

        if (user != null && role != null) {
            logger.info("Associating admin user and role");
            this.userService.addRoleToUser(user.getId(), role.getId());
        }

        logger.info("Finished Admin creation");
    }

}
