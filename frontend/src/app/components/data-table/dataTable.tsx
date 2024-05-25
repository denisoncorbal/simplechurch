import { DataGrid, GridColDef } from '@mui/x-data-grid';

interface DataTableProps {
    columns: GridColDef[],
    rows: any[],
    paginationDefault: {
        page: number,
        pageSize: number
    }
    pageSizeOptions: number[]
}

export default function DataTable(props: DataTableProps) {
    return (
        <div style={{ height: 350, width: '100%' }}>
            <DataGrid
                rows={props.rows}
                columns={props.columns}
                initialState={{
                    pagination: {
                        paginationModel: { page: props.paginationDefault.page, pageSize: props.paginationDefault.pageSize },
                    },
                }}
                pageSizeOptions={props.pageSizeOptions}
                checkboxSelection
            />
        </div>
    );
}
