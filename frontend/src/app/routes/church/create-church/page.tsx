'use client'
import { Cancel } from '@mui/icons-material';
import SaveIcon from '@mui/icons-material/Save';
import Box from '@mui/material/Box';
import FormGroup from '@mui/material/FormGroup';
import IconButton from '@mui/material/IconButton';
import InputLabel from '@mui/material/InputLabel';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import { useRouter } from 'next/navigation';
export default function CreateChurch() {
    const router = useRouter();
    return (
        <FormGroup sx={{
            display: 'flex',
            flexDirection: 'column',
            gap: '1rem'
        }}>
            <Typography sx={{
                alignSelf: 'center'
            }} typography={'h3'}>Create church</Typography>
            <Box sx={{
                display: 'flex',
                gap: '1rem'
            }}>
                <InputLabel sx={{
                    display: 'inline-block',
                    lineHeight: '3rem'
                }} htmlFor='name'>Name:</InputLabel>
                <TextField id='name'></TextField>
            </Box>
            <Box sx={{
                display: 'flex',
                gap: '1rem'
            }}>
                <InputLabel sx={{
                    display: 'inline-block',
                    lineHeight: '3rem'
                }} htmlFor='cnpj'>CNPJ:</InputLabel>
                <TextField id='cnpj'></TextField>
            </Box>
            <Box sx={{
                display: 'flex',
                gap: '1rem',
                alignSelf: 'center'
            }}>
                <IconButton color='success'>
                    <SaveIcon />
                </IconButton>
                <IconButton color='error' onClick={() => router.push('/routes/church')}>
                    <Cancel />
                </IconButton>
            </Box>
        </FormGroup>
    )
}