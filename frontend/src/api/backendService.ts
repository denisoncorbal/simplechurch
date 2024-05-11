import { CHURCH_ENDPOINT, LOGIN_ENDPOINT, REFRESH_ENDPOINT, ROLE_ENDPOINT, SIGNIN_ENDPOINT, USER_ENDPOINT } from "./backendRoutes";

export async function login(login: LoginRequestDto): Promise<LoginResponseDto> {
    const response = await fetch(LOGIN_ENDPOINT, {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(login)
    })
    const data = await response.json();
    return data as LoginResponseDto;
}

export async function refresh(refresh: RefreshRequestDto): Promise<RefreshResponseDto> {
    const response = await fetch(REFRESH_ENDPOINT, {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(refresh)
    })
    const data = await response.json();
    return data as RefreshResponseDto;
}

export async function createChurch(church: CreateChurchRequestDto, accessToken: string): Promise<CreateChurchResponseDto> {
    const response = await fetch(CHURCH_ENDPOINT, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + accessToken
        },
        body: JSON.stringify(church)
    })
    const data = await response.json();
    return data as CreateChurchResponseDto;
}

export async function readAllChurches(accessToken: string): Promise<ReadChurchResponseDto[]> {
    const response = await fetch(CHURCH_ENDPOINT, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + accessToken
        }
    })
    const data = await response.json();
    return data as ReadChurchResponseDto[];
}

export async function createRole(role: CreateRoleRequestDto, accessToken: string): Promise<CreateRoleResponseDto> {
    const response = await fetch(ROLE_ENDPOINT, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + accessToken
        },
        body: JSON.stringify(role)
    })
    const data = await response.json();
    return data as CreateRoleResponseDto;
}

export async function readAllRoles(accessToken: string): Promise<ReadRoleResponseDto[]> {
    const response = await fetch(ROLE_ENDPOINT, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + accessToken
        }
    })
    const data = await response.json();
    return data as ReadRoleResponseDto[];
}
export async function createUser(user: CreateUserRequestDto, accessToken: string): Promise<CreateUserResponseDto> {
    const response = await fetch(SIGNIN_ENDPOINT, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + accessToken
        },
        body: JSON.stringify(user)
    })
    const data = await response.json();
    return data as CreateUserResponseDto;
}

export async function readAllUsers(accessToken: string): Promise<ReadUserResponseDto[]> {
    const response = await fetch(USER_ENDPOINT, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + accessToken
        }
    })
    const data = await response.json();
    return data as ReadUserResponseDto[];
}

export async function associateUserAndRole(accessToken: string, userId: string, roleId: string): Promise<AssociateUserAndRoleResponseDto> {
    const URL = USER_ENDPOINT + "/" + userId + "/role/" + roleId;
    const response = await fetch(URL, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + accessToken
        }
    })
    const data = await response.json();
    return data as AssociateUserAndRoleResponseDto;
}