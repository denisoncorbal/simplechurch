package br.com.dgc.simplechurch.role.controller.dto;

import br.com.dgc.simplechurch.role.controller.dto.request.CreateRoleRequestDto;
import br.com.dgc.simplechurch.role.controller.dto.response.CreateRoleResponseDto;
import br.com.dgc.simplechurch.role.controller.dto.response.ReadRoleResponseDto;
import br.com.dgc.simplechurch.role.model.Role;

public class RoleMapper {
    public static Role createRoleRequestDtoToRole(CreateRoleRequestDto createRoleRequestDto) {
        return new Role(createRoleRequestDto.getName());
    }

    public static CreateRoleResponseDto roleToCreateRoleResponseDto(Role role) {
        return new CreateRoleResponseDto(role.getId(), role.getName());
    }

    public static ReadRoleResponseDto roleToReadRoleResponseDto(Role role) {
        return new ReadRoleResponseDto(role.getId(), role.getName());
    }
}
