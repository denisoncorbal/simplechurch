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