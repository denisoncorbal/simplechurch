'use client'
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import SearchIcon from "@mui/icons-material/Search";
import IconButton from "@mui/material/IconButton/IconButton";
import Typography from "@mui/material/Typography/Typography";
import { useRouter } from "next/navigation";

export default function Members() {
    const router = useRouter();
    return (
        <>
            <IconButton onClick={() => router.push('/routes/members/create-member')}>
                <AddIcon />
                <Typography>Create Member</Typography>
            </IconButton>
            <IconButton onClick={() => router.push('/routes/members/read-member')}>
                <SearchIcon />
                <Typography>Read Member</Typography>
            </IconButton>
            <IconButton>
                <RemoveIcon />
                <Typography>Remove Member</Typography>
            </IconButton>
        </>
    )
}