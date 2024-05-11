'use client'
import { login } from '@/api/backendService';
import { Auth } from '@/context/auth/AuthContext';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { useRouter } from 'next/navigation';
import { FormEvent, useContext } from 'react';
export default function Login() {
    const auth = useContext(Auth);
    const router = useRouter()
    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.target as HTMLFormElement;
        const email = form.elements.namedItem("email") as HTMLInputElement;
        const password = form.elements.namedItem("password") as HTMLInputElement;
        const data = await login({ email: email.value, password: password.value });
        auth?.setUser(data);
        router.push('/');
    }
    return (
        <>
            <form onSubmit={(e) => handleSubmit(e)}>
                <TextField
                    required
                    id="email"
                    label="E-mail"
                    placeholder='example@example.com'
                    focused={true}
                />
                <TextField
                    required
                    type='password'
                    id="password"
                    label="Password"
                />
                <Button variant="contained" type='submit'>Log in</Button>
            </form>
        </>
    )
}