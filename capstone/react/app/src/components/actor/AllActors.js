import Table from "../table/Table";


const AllActors = ({actors}) => {

    const columns = [
        { label: "First Name", accessor: "firstName", sortable: true, sortbyOrder: "desc" },
        { label: "Last Name", accessor: "lastName", sortable: true }
    ];

    return (
        <div className="table_container">
            <h1>Sortable Actors Table</h1>
            <Table
                caption="Note the column headers are sortable."
                data={actors}
                columns={columns}
            />
        </div>
    );
};

export default AllActors;