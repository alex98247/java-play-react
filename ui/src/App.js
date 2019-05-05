import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import './App.css';
import GameList from "./GameList";
import GameForm from "./EditGame";
import LoginForm from "./LoginForm";
import ClaimForm from "./ClaimForm";
import AdminPanel from "./AdminPanel";

class App extends Component {

  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={GameList}/>
          <Route path='/games' component={GameForm}/>
          <Route path='/login' component={LoginForm}/>
          <Route path='/claim' component={ClaimForm}/>
          <Route path='/admin' component={AdminPanel}/>
        </Switch>
      </Router>
    );
  }
}

export default App;
