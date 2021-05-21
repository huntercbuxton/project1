import React, { useState } from 'react';
import ReactDOM from 'react-dom'
import Home from './Home';
import Login from './Login';



function SmartOne(props) {

    const tokenString = localStorage.getItem('token');
    console.log('the saved tokenString was: ' + tokenString);
    const [ loggedIn, setLoggedIn ] = useState(tokenString);

    const updateAndSave = (newVal) => {
        setLoggedIn(newVal);
        localStorage.setItem('token', newVal);
    }

    console.log('the updayed loggedIn was: ' + loggedIn);

    if (!loggedIn) {
      return (
          <div id='v-spacer'>
              <button onClick={ () => updateAndSave("nonempty string") } > Log In</button>
              <Login  />
          </div>
      );
    }

    return (
        <div id='v-spacer'>
             <EmployeeNav />
            <button onClick={ () => updateAndSave() } > LogOut </button>
            { props.component }
        </div>
    );

} 

export default  SmartOne;

// ReactDOM.render(
//     // Try changing to isLoggedIn={true}:
//     <SmartOne loggedIn={false} />,
//     document.getElementById('root')
// );