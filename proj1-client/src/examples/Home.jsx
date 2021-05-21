import React, { useEffect, useState } from 'react';

/* TUTORIAL::::     https://upmostly.com/tutorials/react-ajax-requests-fetch-data */ 

function Home(props) {
    
    const [users, setUsers] = useState([]);

    useEffect(() => {
      getUsers();
    }, []);

    async function getUsers() {
        const response = await fetch('https://jsonplaceholder.typicode.com/users');
        const users = await response.json();
        setUsers(users);
    }

    return (
        <main>
            <ul>
                {users.map(user => (
                <li>{user.name}</li>
            ))}
            </ul> 
        </main>
    );
}

export default Home;
