'use client'
import { readAllRoles } from "@/api/backendService";
import DataTable from "@/components/data-table/dataTable";
import { Auth } from "@/context/auth/AuthContext";
import { ReadRoleResponseDto } from "@/interfaces/interfaces";
import CircularProgress from "@mui/material/CircularProgress/CircularProgress";
import { useContext, useEffect, useState } from "react";

export default function ReadRoles() {
    const auth = useContext(Auth);
    const [roles, setRoles] = useState([] as ReadRoleResponseDto[]);

    useEffect(() => {
        readAllRoles(auth!.user.accessToken).then((data) => {
            setRoles(data);
        })
    }, [auth])

    if (!roles || roles.length === 0)
        return <CircularProgress />

    return (
        <>
            <DataTable columns={[
                { field: 'id', headerName: "Id" },
                { field: 'name', headerName: "Name" },
            ]} rows={roles} paginationDefault={{ page: 0, pageSize: 4 }} pageSizeOptions={[4, 10, 15, 20]} />
        </>
    )
}