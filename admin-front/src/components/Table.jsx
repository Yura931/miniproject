import {flexRender, getCoreRowModel, useReactTable} from "@tanstack/react-table";
import PaginationProvider from "./Pagination";
import {
    ResponsiveTable,
    TableBody,
    TableBox,
    TableContainer,
    TableHead,
    TableTd,
    TableTh,
    TableTr
} from "./Table.styled";

const Table = ({data, pageable, columns, handleSetParams}) => {

    const table = useReactTable({
        data, columns, getCoreRowModel: getCoreRowModel()
    });

    return (
        <ResponsiveTable>
            <TableContainer>
                <TableBox>
                    <TableHead>
                    {table.getHeaderGroups().map((headerGroup) => (
                        <TableTr key={headerGroup.id}>
                            {headerGroup.headers.map((header) => (
                                <TableTh key={header.id}>
                                    {header.isPlaceholder
                                        ? null
                                        : flexRender(
                                            header.column.columnDef.header,
                                            header.getContext()
                                        )}
                                </TableTh>
                            ))}
                        </TableTr>
                    ))}
                    </TableHead>
                    <TableBody>
                    {table.getRowModel().rows.map((row) => (
                        <TableTr key={row.id}>
                            {row.getVisibleCells().map((cell) => (
                                <TableTd key={cell.id}>
                                    {
                                        flexRender(cell.column.columnDef.cell, cell.getContext())
                                    }
                                </TableTd>
                            ))}
                        </TableTr>
                    ))}
                    </TableBody>
                </TableBox>
                <PaginationProvider
                    pageable={pageable}
                    handleSetParams={handleSetParams}
                />
            </TableContainer>
        </ResponsiveTable>
    )
}

export default Table;





/*
const data = [
    {
        id: 1,
        name: 'dh',
        age: 23,
        phone: '010-1234-1234',
        email: 'dhkang@naver.com',
        addr: 'seoul, korea',
    },
    {
        id: 2,
        name: 'mike',
        age: 23,
        phone: '010-1234-1234',
        email: 'mike@naver.com',
        addr: 'seoul, korea',
    },
];*/
