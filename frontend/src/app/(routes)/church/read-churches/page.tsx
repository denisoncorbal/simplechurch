'use client'

import { readAllChurches } from '@/app/api/backend/backendService';
import DataTable from '@/app/components/data-table/dataTable';
import { ReadChurchResponseDto } from '@/app/interfaces/interfaces';
import CircularProgress from '@mui/material/CircularProgress';
import { useContext, useEffect, useState } from "react";

export default function ReadChurches() {
    const [churches, setChurches] = useState([] as ReadChurchResponseDto[]);

    // TODO
    // useEffect(() => {
    //     readAllChurches(auth!.user.accessToken).then((data) => {
    //         setChurches(data);
    //     })
    // }, [auth])

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