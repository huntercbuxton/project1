
import React from "react";
import {Link, useHistory} from "react-router-dom";



function EmployeeNav(props) {

    return (
        <ul class='page-header'>
            <li><Link to='/' > Dashboard </Link></li>
            <li><Link to='/tools' > Tools </Link></li>
            <li><Link to='/account' > Account </Link></li>
            <li> 
            {/* <input type='submit' id='logout-btn' onSubmit={ props.logoutFunc } > Log Out </input> */}
                <button id='logout-btn' onClick={ props.logoutFunc } > Log Out </button> 
            </li>
        </ul>
    );

}

export default EmployeeNav;