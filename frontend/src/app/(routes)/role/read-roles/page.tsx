'use client'
import { readAllRoles } from "@/app/api/backend/backendService";
import DataTable from "@/app/components/data-table/dataTable";
import { ReadRoleResponseDto } from "@/app/interfaces/interfaces";
import CircularProgress from "@mui/material/CircularProgress/CircularProgress";
import { useContext, useEffect, useState } from "react";

export default function ReadRoles() {
    const [roles, setRoles] = useState([] as ReadRoleResponseDto[]);

    // TODO
    // useEffect(() => {
    //     readAllRoles(auth!.user.accessToken).then((data) => {
    //         setRoles(data);
    //     })
    // }, [auth])

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