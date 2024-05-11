interface LoginRequestDto {
    email: string,
    password: string
}

interface LoginResponseDto {
    firstName: string,
    lastName: string,
    accessToken: string,
    refreshToken: string,
    roles: string[]
}

interface RefreshRequestDto {
    accessToken: string,
    refreshToken: string
}

interface RefreshResponseDto {
    accessToken: string,
    refreshToken: string
}

interface User {
    firstName: string,
    lastName: string,
    accessToken: string,
    refreshToken: string,
    roles: string[]
}

interface RefreshRequestDto {
    accessToken: string,
    refreshToken: string
}

interface SignInRequestDto {
    firstName: string,
    lastName: string,
    email: string,
    password: string
}

interface CreateChurchRequestDto {
    name: string,
    cnpj: string
}

interface CreateChurchResponseDto {
    id: string,
    name: string
}

interface ReadChurchResponseDto {
    id: string,
    name: string,
    cnpj: string
}

interface CreateRoleRequestDto {
    name: string
}

interface CreateRoleResponseDto {
    id: string,
    name: string
}

interface ReadRoleResponseDto {
    id: string,
    name: string
}

interface CreateUserRequestDto {
    firstName: string,
    lastName: string,
    email: string,
    password: string
}

interface CreateUserResponseDto {
    id: string,
    email: string
}

interface ReadUserResponseDto {
    id: string,
    firstName: string,
    lastName: string,
    email: string
}

interface AssociateUserAndRoleResponseDto {
    id: string,
    email: string,
    roles: string[]
}