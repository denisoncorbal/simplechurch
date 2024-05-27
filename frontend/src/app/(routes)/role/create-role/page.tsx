'use client'
import { createRole } from "@/app/api/backend/backendService";
import { Cancel } from "@mui/icons-material";
import SaveIcon from '@mui/icons-material/Save';
import IconButton from "@mui/material/IconButton/IconButton";
import TextField from "@mui/material/TextField/TextField";
import { useRouter } from "next/navigation";
import { FormEvent, useContext } from "react";

export default function CreateRole() {
    const router = useRouter();
    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.target as HTMLFormElement;
        const name = form.elements.namedItem("name") as HTMLInputElement;
        // TODO
        // const data = await createRole({ name: name.value }, auth!.user.accessToken);
        // console.log(data);
    }
    return (
        <>
            <form onSubmit={(e) => handleSubmit(e)}>
                <TextField
                    required
                    id="name"
                    label="Name"
                    placeholder='Example Church'
                    focused={true}
                />
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