package br.com.dgc.simplechurch.role.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dgc.simplechurch.role.controller.dto.RoleMapper;
import br.com.dgc.simplechurch.role.controller.dto.request.CreateRoleRequestDto;
import br.com.dgc.simplechurch.role.controller.dto.response.CreateRoleResponseDto;
import br.com.dgc.simplechurch.role.controller.dto.response.ReadRoleResponseDto;
import br.com.dgc.simplechurch.role.model.Role;
import br.com.dgc.simplechurch.role.service.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<CreateRoleResponseDto> createRole(@RequestBody CreateRoleRequestDto createRoleRequestDto) {
        Role role = RoleMapper.createRoleRequestDtoToRole(createRoleRequestDto);
        role = this.roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoleMapper.roleToCreateRoleResponseDto(role));
    }

    @GetMapping
    public ResponseEntity<List<ReadRoleResponseDto>> readAllRoles() {
        List<Role> roles = this.roleService.readAllRoles();
        return ResponseEntity.ok(roles.stream().map(RoleMapper::roleToReadRoleResponseDto).toList());
    }
}
