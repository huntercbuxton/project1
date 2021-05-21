import React, { useState } from 'react';
// import { BrowserRouter as Router, Link, useHistory } from 'react-router-dom';
import PropTypes from 'prop-types';


function Login({ setToken, currentType, otherType }) {
    
    const [loginErrMsg, setLoginErrMsg] = useState("");
    // const history = useHistory();
    // const navaction = () => history.push('/employee');

    // const handleLoginSubmit = async (event) => {
    //     event.preventDefault();

    //     var form = event.target;
    //     var formData = new FormData(form);
    //     var xhr = new XMLHttpRequest();

    //     xhr.onreadystatechange = function() {
            
    //         if (this.readyState === 4 && this.status === 200) {
    //             var response = JSON.parse(this.responseText);
    //             if (response['errCode'] !== 0) {
    //                 setLoginErrMsg(response['response']);
    //                 setToken(null);
    //             } else {
    //                 setToken(response['response']);
    //             }   
    //         }
    //     };
        
    //     // setToken(token);
    //     xhr.open('POST', form.getAttribute('action'));
    //     xhr.setRequestHeader("accept","application/json");
    //     xhr.send(formData);
    // };

    const handleSubmit = async e => {
        // e.preventDefault();
        // const token = await handleLoginSubmit(e);
        // setToken(token);
      }

    return (
        <div id='page-wrapper'>
            <main>
                <form onSubmit={handleSubmit} action='http://localhost:7777/login' method='post'>
                    <fieldset id='auth-inputs'>
                        <input type='email' name='email' placeholder='email' required />
                        <input type='password' name='password' placeholder='password' required />
                        <input type='hidden' name='accountType' value={currentType} required />
                    </fieldset>
                    <p id='err-msg-label'>{ loginErrMsg }</p>
                    <fieldset id='auth-navigation'>
                        <input type='submit' name='login-submit' value='employee'/>
                        {/* <Link to={'/' + otherType + '/login'}> Switch To a { otherType } Login </Link> */}
                    </fieldset>
                </form>
            </main>
        </div>
    );
}


// Login.propTypes = {
//     setToken: PropTypes.func.isRequired
// }

export default Login;