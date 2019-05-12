import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import './App.css';
import GameList from "./GameList";
import GameForm from "./GameForm";
import LoginForm from "./LoginForm";
import ClaimForm from "./ClaimForm";
import AdminPanel from "./AdminPanel";
import Registration from './Registration';

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
          <Route path='/signUp' component={Registration}/>
        </Switch>
      </Router>
    );
  }
}

export default App;
