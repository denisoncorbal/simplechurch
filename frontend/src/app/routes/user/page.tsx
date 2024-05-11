'use client'
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import SearchIcon from '@mui/icons-material/Search';
import IconButton from "@mui/material/IconButton/IconButton";
import Typography from "@mui/material/Typography/Typography";
import { useRouter } from "next/navigation";

export default function User() {
    const router = useRouter();
    return (
        <>
            <IconButton onClick={() => router.push('/routes/user/create-user')}>
                <AddIcon />
                <Typography>Create User</Typography>
            </IconButton>
            <IconButton onClick={() => router.push('/routes/user/read-users')}>
                <SearchIcon />
                <Typography>Read Users</Typography>
            </IconButton>
            <IconButton>
                <RemoveIcon />
                <Typography>Remove User</Typography>
            </IconButton>
        </>
    )
}