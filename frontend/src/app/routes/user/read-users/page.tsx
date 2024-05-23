'use client'
import { readAllUsers } from "@/api/backendService";
import DataTable from "@/components/data-table/dataTable";
import { Auth } from "@/context/auth/AuthContext";
import CircularProgress from "@mui/material/CircularProgress/CircularProgress";
import { useContext, useEffect, useState } from "react";

export default function ReadUsers() {
    const auth = useContext(Auth);
    const [users, setUsers] = useState([] as ReadUserResponseDto[]);

    useEffect(() => {
        readAllUsers(auth!.user.accessToken).then((data) => {
            setUsers(data);
        })
    }, [auth])

    if (!users || users.length === 0)
        return <CircularProgress />

    return (
        <>
            <DataTable columns={[
                { field: 'id', headerName: "Id" },
                { field: 'name', headerName: "Name" },
            ]} rows={users} paginationDefault={{ page: 0, pageSize: 4 }} pageSizeOptions={[4, 10, 15, 20]} />
        </>
    )
}