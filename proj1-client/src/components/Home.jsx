import React, { useEffect, useState } from 'react';

/* TUTORIAL::::     https://upmostly.com/tutorials/react-ajax-requests-fetch-data */ 

function Home(props) {
    
    const [userData, setUserData] = useState([]);

    useEffect(() => { getUsers(); }, [] );

    async function getUsers() {
        const response = await fetch('http://localhost:7777/dashboard/?fromUser=' + localStorage.getItem('token'));
        setUserData(await response.json());
    }

    return (
        <main>
            <h1>{ 'welcome, ' + userData['firstName'] + ' ' + userData['lastName']}</h1>
        </main>
    );
}

export default Home;
