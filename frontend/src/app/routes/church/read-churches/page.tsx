'use client'

import { readAllChurches } from "@/api/backendService";
import DataTable from "@/components/data-table/dataTable";
import { Auth } from "@/context/auth/AuthContext";
import CircularProgress from '@mui/material/CircularProgress';
import { useContext, useEffect, useState } from "react";

export default function ReadChurches() {
    const auth = useContext(Auth);
    const [churches, setChurches] = useState([] as ReadChurchResponseDto[]);

    useEffect(() => {
        readAllChurches(auth!.user.accessToken).then((data) => {
            setChurches(data);
        })
    }, [])

    if (!churches || churches.length === 0)
        return <CircularProgress />

    return (
        <>
            <DataTable columns={[
                { field: 'id', headerName: "Id" },
                { field: 'name', headerName: "Name" },
                { field: 'cnpj', headerName: "CNPJ" }
            ]} rows={churches} paginationDefault={{ page: 0, pageSize: 4 }} pageSizeOptions={[4, 10, 15, 20]} />
        </>
    )
}