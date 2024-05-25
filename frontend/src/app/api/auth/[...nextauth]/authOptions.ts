import { login } from '@/api/backendService';
import { AuthOptions } from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';


export const authOptions = {
    providers: [
        CredentialsProvider({
            id: "credentials",
            name: "credentials",
            credentials: {
                email: { label: "Email", type: "text" },
                password: { label: "Password", type: "password" }                
            },
            async authorize(credentials) {
                console.log("Credentials fornecidas: " + credentials!.email + " " + credentials!.password);
                const user = await login({ email: credentials!.email, password: credentials!.password })
                if (!user)
                    return null;
                return user;
            }
        })
    ]
} satisfies AuthOptions