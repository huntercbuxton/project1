import React, { useEffect, useState } from 'react';
import TableView from './TableView';

function Tools(props) {
    
    // const [userData, setUserData] = useState([]);
    const [reimbursements, setReimbursements] = useState([ { amount: 500, status: "pending", date: "05/04/2020" }, { amount: 2, status: "pending", date: "05/01/2020" }, { amount: 666, status: "complete", date: "04/04/2020" }, { amount: 1, status: "denied", date: "01/04/2020" }]);

    // useEffect(() => { getUserData(); }, []);
    useEffect(() => { getReimbursements(); }, []);

    // async function getUserData() {
    //     const response = await fetch('http://localhost:7777/dashboard/?fromUser=' + localStorage.getItem('token'));
    //     setUserData(await response.json());
    // }

    async function getReimbursements() {
        // const response = await fetch('http://localhost:7777/tools/?fromUser=' + localStorage.getItem('token'));
        // setReimbursements(await response.json());
    }

    const handleSubmit = e => {
        e.preventDefault();
    }

    return (
        <main>
            <h1> Tools</h1>
            <section> 
                <ul>
                    {reimbursements.map(item => (
                    <li>{'amount: ' + item.amount + '      date: ' + item.date + "      status: " + item.status}</li>
                ))}
                </ul> 
            </section>
            <form onSubmit={handleSubmit}>
                <h3>submit a new reimbursement request:</h3>
                <input type='text' id='amount-input' placeholder='amount'/>
                <input type='text' id='date-input' placeholder='amount'/>
                <input type='submit' name='submit-request' value='submit request'>Submit</input>
            </form>
        </main>
    );
}
//addToRequests(document.getElementById('amount-input').value, document.getElementById('date-input'.value))

export default Tools;

