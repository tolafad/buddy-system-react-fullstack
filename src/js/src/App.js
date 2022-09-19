import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import ListUserComponent from './components/ListUserComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import UpdateUserComponent from './components/UpdateUserComponent';
import ViewUserComponent from './components/ViewUserComponent';
import GenerateBuddyComponent from './components/GenerateBuddyComponent';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Routes> 
                          <Route path = "/" exact element = {<GenerateBuddyComponent/>}></Route>
                          <Route path = "/gen-buddy" exact element = {<GenerateBuddyComponent/>}></Route>
                          <Route path = "/users" exact element = {<ListUserComponent/>}></Route>
                          <Route path = "/add-user" exact element = {<UpdateUserComponent/>}></Route>
                          <Route path = "/view-user/:id" exact element = {<ViewUserComponent/>}></Route>
                          <Route path = "/update-user/:id" exact element = {<UpdateUserComponent/>}></Route>
                    </Routes>
                </div>
              <FooterComponent />
        </Router>
    </div>
    
  );
}

export default App;