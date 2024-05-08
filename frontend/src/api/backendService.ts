import { LOGIN_ENDPOINT } from "./backendRoutes";

export async function login(login: LoginRequestDto): Promise<LoginResponseDto> {
    const response = await fetch(LOGIN_ENDPOINT, {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(login)
    })
    const data = await response.json();
    return data as LoginResponseDto;
}