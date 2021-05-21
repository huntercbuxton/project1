import React, { useEffect, useState } from 'react';

function Account(props) {
 
    const [userData, setUserData] = useState([]);

    useEffect(() => { getUsers(); }, []);

    async function getUsers() {
        const response = await fetch('http://localhost:7777/dashboard/?fromUser=' + localStorage.getItem('token'));
        setUserData(await response.json());
    }
   
    const updateUserInfo = (event) => {
        event.preventDefault();
        var form = event.target;
        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                // const responseData = JSON.parse(this.responseText);
                const data = this.response.json();
                console.log(data); 
            } 
        };
        xhr.open('POST', form.getAttribute('action'));
        xhr.setRequestHeader("accept","application/json");
        xhr.send(formData);
    };

    return (
        <main>
            <h1>Your Info</h1>
            <section>
            { 'welcome, ' + userData['firstName'] + ' ' + userData['lastName']}
            <form onSubmit={updateUserInfo} method='post' action='http://localhost:7777/dashboard/updateaccount/'>
                <input type='hidden' name='id' value={ localStorage.getItem('token') } />
                <input type='text' name='first name' value={ userData['firstName'] } placeholder='first name' />
                <input type='text' name='last name' value={ userData['lastName'] } placeholder='last name' />
                <input type='text' name='password' value={ userData['password'] } placeholder='password' />
                <input type='text' name='email' value={ userData['email'] } placeholder='email' />
                <input type='submit' name='submit-info' value='save updates' />
            </form>
            </section>
        </main>
    );

}

export default Account;

