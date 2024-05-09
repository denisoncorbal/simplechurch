'use client'
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import SearchIcon from '@mui/icons-material/Search';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { useRouter } from 'next/navigation';

export default function Church() {
    const router = useRouter();
    return (
        <>
            <IconButton onClick={() => router.push('/routes/church/create-church')}>
                <AddIcon />
                <Typography>Create Church</Typography>
            </IconButton>
            <IconButton onClick={() => router.push('/routes/church/read-churches')}>
                <SearchIcon />
                <Typography>Read Church</Typography>
            </IconButton>
            <IconButton>
                <RemoveIcon />
                <Typography>Remove Church</Typography>
            </IconButton>
        </>
    )
}