'use client'
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import SearchIcon from '@mui/icons-material/Search';
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { useRouter } from "next/navigation";

export default function Role() {
    const router = useRouter();
    return (
        <>
            <IconButton onClick={() => router.push('/routes/role/create-role')}>
                <AddIcon />
                <Typography>Create Role</Typography>
            </IconButton>
            <IconButton onClick={() => router.push('/routes/role/read-roles')}>
                <SearchIcon />
                <Typography>Read Role</Typography>
            </IconButton>
            <IconButton>
                <RemoveIcon />
                <Typography>Remove Role</Typography>
            </IconButton>
        </>
    )
}