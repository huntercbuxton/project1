import React, { useEffect, useState } from 'react';

/* TUTORIAL::::     https://upmostly.com/tutorials/react-ajax-requests-fetch-data */ 

function TableView(props) {
    
    const [items, setItems] = useState([]);

    useEffect( () => { setItems(props.items) }, [] );

    //  async function getUsers() {
        // const response = await fetch('http://localhost:7777/dashboard/?fromUser=' + localStorage.getItem('token'));
        // const users = await response.json();
        // const userData = JSON.parse(await response.json());
        // setUserData(p);
    // }

    return (
        <ul>
             {items.map(item => (
             <li>{item}</li>
        ))}
        </ul> 
    );
}

export default TableView;
