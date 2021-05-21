import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import Account from './components/Account';
import Tools from './components/Tools';
import SmartTwo from './components/SmartTwo';



function App() {

  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
            <Route path='/' exact >
              <SmartTwo component={ <Home /> }  currentType='employee' /> 
            </Route>
            <Route path='/tools' exact >
              <SmartTwo component={ <Tools /> } currentType='employee' /> 
            </Route>
            <Route path='/account' exact >
              <SmartTwo component={ <Account />} currentType='employee' /> 
            </Route>
          </Switch>
      </BrowserRouter>       
    </div>
  );

}

export default App;
