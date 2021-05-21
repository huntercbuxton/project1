import React, { useState } from 'react';

function AppContainer(props) {

    const services_url = 'http://localhost:7777/';

    const [loginState, setLoggedIn] = useState(false);


    return 
    (
        <div id='v-spacer'>
            <footer class='page, -footer'>
                <span>&copy; Hunter Buxton 221  </span>
            </footer>
        </div>        
    );

}


export default AppContainer;