'use client'
import { associateUserAndRole, createUser, readAllRoles } from "@/api/backendService";
import { Auth } from "@/context/auth/AuthContext";
import { Cancel } from "@mui/icons-material";
import SaveIcon from '@mui/icons-material/Save';
import IconButton from "@mui/material/IconButton/IconButton";
import MenuItem from "@mui/material/MenuItem/MenuItem";
import Select, { SelectChangeEvent } from "@mui/material/Select/Select";
import TextField from "@mui/material/TextField/TextField";
import { useRouter } from "next/navigation";
import { FormEvent, useContext, useEffect, useState } from "react";

export default function CreateUser() {
    const auth = useContext(Auth);
    const router = useRouter();
    const [selectedRole, setSelectedRole] = useState('');
    const [roles, setRoles] = useState<ReadRoleResponseDto[]>([]);

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.target as HTMLFormElement;
        const firstName = form.elements.namedItem("firstName") as HTMLInputElement;
        const lastName = form.elements.namedItem("lastName") as HTMLInputElement;
        const email = form.elements.namedItem("email") as HTMLInputElement;
        const password = form.elements.namedItem("password") as HTMLInputElement;
        console.log(e);
        const createUserData = await createUser({ firstName: firstName.value, lastName: lastName.value, email: email.value, password: password.value }, auth!.user.accessToken);
        console.log(createUserData);
        const associateUserAndRoleData = await associateUserAndRole(auth!.user.accessToken, createUserData.id, selectedRole);
    }

    const handleChangeSelect = (e: SelectChangeEvent) => {
        setSelectedRole(e.target.value);
    }

    useEffect(() => {
        readAllRoles(auth!.user.accessToken).then((res) => {
            setRoles(res);
        })
    }, []);

    return (
        <>
            <form onSubmit={(e) => handleSubmit(e)}>
                <TextField
                    required
                    id="firstName"
                    label="First Name"
                    placeholder='Example'
                    focused={true}
                />
                <TextField
                    required
                    id="lastName"
                    label="Last Name"
                    placeholder='Example'
                />
                <TextField
                    required
                    id="email"
                    label="E-mail"
                    placeholder='example@example.com'
                />
                <TextField
                    required
                    id="password"
                    label="Password"
                    type="password"
                />
                <Select
                    id="role"
                    label="Role"
                    value={selectedRole}
                    onChange={handleChangeSelect}
                >
                    {roles.map((role) => {
                        return (<MenuItem key={role.id} value={role.id}>{role.name}</MenuItem>)
                    })}
                </Select>
                <IconButton color='success' type='submit'>
                    <SaveIcon />
                </IconButton>
                <IconButton color='error' onClick={() => router.push('/routes/church')}>
                    <Cancel />
                </IconButton>
            </form>
        </>
    )
}