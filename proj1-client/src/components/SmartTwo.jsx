import React, { useState } from 'react';
import EmployeeNav from "./EmployeeNav";
import PageFooter from './PageFooter';

function SmartTwo(props) {
    
    
    const tokenString = localStorage.getItem('token');
    console.log('the saved tokenString was: ' + tokenString);
    
    const [ loggedIn, setLoggedIn ] = useState(tokenString);
    const [ loginErrMsg, setLoginErrMsg] = useState("");

    const updateAndSave = (newVal) => {
        setLoggedIn(newVal);
        localStorage.setItem('token', newVal);
    }

    const logout = () => {
        updateAndSave("no");
    }

    // console.log('the updated (state) var loggedIn was: ' + loggedIn); 

    const handleSubmit = (event) => {
        event.preventDefault();
        var form = event.target;
        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                const responseData = JSON.parse(this.responseText);
                if (responseData['errCode'] !== 0 ) {
                    setLoginErrMsg(responseData['response']);
                    updateAndSave("no");
                    console.log(responseData)
                } else {
                    setLoginErrMsg('');
                    updateAndSave(responseData['response']);
                    console.log('logged in with ' + responseData['response']);
                } 
            } 
        };
        xhr.open('POST', form.getAttribute('action'));
        xhr.setRequestHeader("accept","application/json");
        xhr.send(formData);
    };

    // console.log('tright before being in (!), var loggedIn was: ' + loggedIn); 
// typeof loggedIn === 'undefined'
    if (loggedIn == "no") {
        return (
            <div id='v-spacer'>
                {/* <button onClick={ () => updaeAndSave("nonempty string") } > Log In</button> */}
                <div id='page-wrapper'>
                  <main>
                      <form id='login-form' onSubmit={handleSubmit} action='http://localhost:7777/login' method='post'>
                          <fieldset id='auth-inputs'>
                              <input type='email' name='email' placeholder='email' required />
                              <input type='password' name='password' placeholder='password' required />
                              <input type='hidden' name='accountType' value={ props.currentType } required />
                          </fieldset>
                          <p id='err-msg-label'>{ loginErrMsg }</p>
                          <fieldset class='auth-navigation'>
                              <input class='auth-navigation' type='submit' name='login-submit' value={ props.currentType + ' login' }/>
                          </fieldset>
                      </form>
                  </main>
              </div>
            </div>
        );
    }
    
    return (
         <div id='v-spacer'>
            <header id='page-header'>
                <EmployeeNav logoutFunc={ () => logout() } />
            </header>
            { props.component }
            <PageFooter />
        </div>
    );
}

export default  SmartTwo;
