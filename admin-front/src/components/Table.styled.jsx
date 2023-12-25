import {styled} from "styled-components";
import {boxShadow, darkBrown, heavyFont, lightGreen, white} from "../styles/Variables.styled";

const tableContainer = styled.div`
    padding: 1.1rem 0.8rem;
    width: 100%;
`
const tableBox = styled.table`
    border: 1px #a39485 solid;
    font-size: .9em;
    width: 100%;
    border-collapse: collapse;
    border-radius: 2px;
    overflow: hidden;
`

const tableHead = styled.thead`
    font-weight: ${heavyFont};
    color: ${white};
    background: ${lightGreen};
`

const tableBody = styled.tbody`
    width: 100%;
    vertical-align: inherit;
    border-color: inherit;
    border-styled: solid;
    border-width: 0;
    text-align: center;
`

const tableTr = styled.tr`
    border-color: inherit;
    border-styled: solid;
    border-width: 0;
`

const tableTh = styled.th`
    padding: 1em .5em;
    vertical-align: middle
`

const tableTd = styled.td`
    padding: 1em .5em;
    vertical-align: middle;
    border-bottom: 1px solid rgba(0,0,0,.1);
    background: ${white};
`

export const TableContainer = styled.div`
    padding: 1.1rem 0.8rem;
    width: 100%;
`
export const TableBox = styled.table`
    border: 1px #a39485 solid;
    font-size: .9em;
    width: 100%;
    border-collapse: collapse;
    border-radius: 2px;
    overflow: hidden;
`

export const TableHead = styled.thead`
    font-weight: ${heavyFont};
    color: ${white};
    background: ${lightGreen};
`

export const TableBody = styled.tbody`
    width: 100%;
    vertical-align: inherit;
    border-color: inherit;
    border-styled: solid;
    border-width: 0;
    text-align: center;
`

export const TableTr = styled.tr`
    border-color: inherit;
    border-styled: solid;
    border-width: 0;
`

export const TableTh = styled.th`
    padding: 1em .5em;
    vertical-align: middle
`

export const TableTd = styled.td`
    padding: 1em .5em;
    vertical-align: middle;
    border-bottom: 1px solid rgba(0,0,0,.1);
    background: ${white};
`

export const ResponsiveTable = styled.div`
    width: 100%;
  @media all and (max-width: 768px) {
    table,
    thead,
    tbody,
    th,
    td,
    tr {
      display: flex;
    }

    th {
      text-align: right;
    }

    table {
      position: relative;
      padding-bottom: 0;
      border: none;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }

    thead {
      float: left;
      white-space: nowrap;
    }

    tbody {
      float: left;
      overflow-x: auto;
      overflow-y: hidden;
      position: relative;
      white-space: nowrap;
    }

    tr {
      display: inline-block;
      vertical-align: top;
    }

    th {
      border-bottom: 1px solid #a39485;
    }

    td {
      border-bottom: 1px solid #e5e5e5;
    }
  }
`
export const LeftTableContainer = styled.div`
    height: 100%;
    width: 100%;

    table,
    thead,
    tbody,
    th,
    td,
    tr {
        display: flex;
        align-items: center;
    }

    th {
        text-align: right;
    }

    table {
        position: relative;
        padding-bottom: 0;
        border: none;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }

    thead {
        float: left;
        white-space: nowrap;
    }

    tbody {
        overflow-x: auto;
        overflow-y: hidden;
        position: relative;
        white-space: nowrap;
    }

    tr {
        display: inline;
        vertical-align: top;
    }

    th {
        border-bottom: 1px solid #a39485;
    }

    td {
        border-bottom: 1px solid #e5e5e5;
    }
`

export const OverFlowContainer = styled(TableContainer)`
    height: 70vh;
    overflow-x: hidden;
    overflow-y: auto;
`
export const LeftTable = styled(TableBox)`

`
export const LeftTableThead = styled(TableHead)`

`
export const LeftTableTbody = styled(TableBody)`

`

export const LeftTableTh = styled(TableTh)`

`
export const LeftTableTr = styled(TableTr)`

`
export const LeftTableTd = styled(TableTd)`

`

export const SaveButton = styled.button`
    width: 8rem;
    height: 2.4rem;
    border-radius: 4px;
    right: 0.8rem;
    color: ${white};
    background-color: ${lightGreen};
    font-weight: ${heavyFont};
    cursor: pointer;
`