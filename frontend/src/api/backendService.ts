import { CHURCH_ENDPOINT, LOGIN_ENDPOINT, REFRESH_ENDPOINT } from "./backendRoutes";

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