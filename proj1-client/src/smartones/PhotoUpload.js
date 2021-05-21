import React from "react";
import {Link, useHistory} from "react-router-dom";


function EmployeeNav() {
  
  const history = useHistory();
  const routeToLogoutPage = () => history.push('/logout');

  const logoutAction = (event) => {
    event.preventDefault();
    var form = event.target;
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var response = JSON.parse(this.responseText);
            if (response['errCode'] === 0) {
                routeToLogoutPage();
            } else {
                alert(response['response']);
            }
        }
    };
    xhr.open('POST', form.getAttribute('action'));
    xhr.setRequestHeader("accept","application/json");
    xhr.send(formData);
};


  return (
    <header id='page-header'>
        <ul class='page-header'>
            <li><Link to='/employee' > Dashboard </Link></li>
            <li><Link to='/reimbursements' > Reimbursements </Link></li>
            <li><Link to='/account' > Account </Link></li>
        </ul>
        <form onSubmit={logoutAction} class='page-header' > 
            <input type='submit' name='submit-logout' value='logout'/>
        </form>
    </header>
  );
}

export default EmployeeNav;
