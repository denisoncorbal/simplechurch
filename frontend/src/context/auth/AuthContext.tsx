'use client'
import React, { useState } from "react";

export interface UserContextType {
    user: User,
    setUser: (user: User) => void
}

export const Auth = React.createContext<UserContextType | null>(null);

export default function AuthContext({ children }: Readonly<{
    children: React.ReactNode;
}>) {
    const [user, setUser] = useState<User>({
        firstName: '',
        lastName: '',
        accessToken: '',
        refreshToken: '',
        roles: []
    });
    return (
        <Auth.Provider value={{ user: user, setUser: setUser }}>
            {children}
        </Auth.Provider>
    );
}