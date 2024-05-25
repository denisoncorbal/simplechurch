'use client'
import { Cancel } from '@mui/icons-material';
import SaveIcon from '@mui/icons-material/Save';
import IconButton from '@mui/material/IconButton';
import TextField from '@mui/material/TextField';
import { useRouter } from 'next/navigation';
import { FormEvent, useContext } from 'react';
export default function CreateChurch() {
    const router = useRouter();
    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.target as HTMLFormElement;
        const name = form.elements.namedItem("name") as HTMLInputElement;
        const cnpj = form.elements.namedItem("cnpj") as HTMLInputElement;
        // TODO const data = await createChurch({ name: name.value, cnpj: cnpj.value }, auth!.user.accessToken);
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
                <TextField
                    required
                    id="cnpj"
                    label="CNPJ"
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