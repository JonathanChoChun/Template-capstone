import {Button} from "reactstrap";

const TableBody = ({ columns, tableData,editClick }) => {
    //{ label: "Edit", accessor: "id", sortable: false,  callback: {handleEditClick}, type:"button"}
    return (
     <tbody>
      {tableData.map((data) => {
       return (
        <tr key={data.id}>
         {columns.map(column => {
          const tData = data[column.accessor] ? data[column.accessor] : "——";
          if (column.callback !== undefined) {
              return <td key={column.accessor}><Button className="btn-table" id={data[column.accessor]} onClick={editClick}>{column.label}</Button> </td>;
          } else {
               return <td key={column.accessor}>{tData}</td>;
          }
         })}
        </tr>
       );
      })}
     </tbody>
    );
   };
   
   export default TableBody;