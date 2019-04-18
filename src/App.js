import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import GameList from "./GameList";
import EditGame from "./EditGame";

class App extends Component {

  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={GameList}/>
          <Route path='/games' component={EditGame}/>
        </Switch>
      </Router>
    );
  }
}

export default App;
