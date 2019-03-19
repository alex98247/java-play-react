import React from 'react'
import { Provider } from 'react-redux'
import { createStore } from 'redux'
import AppView from './appview'
import * as ReactRouterDOM from "react-router-dom";
import LoginForm from "./login/login";

const Router = ReactRouterDOM.BrowserRouter;
const Route = ReactRouterDOM.Route;
const Switch = ReactRouterDOM.Switch;

var reducer = require("./reducer.js");
const store = createStore(reducer)

store.dispatch({
  type: "SET_STATE",
  state: {
    credentials: ["iPhone 7 Plus", "Samsung Galaxy A5"]
  }
});

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {title: ''};
  }

    render()
    {
      return (
        <Provider store={store}>
          <Router>
            <Switch>
              <Route exact path="/" component={AppView}/>
              <Route exact path="/login" component={LoginForm}/>
            </Switch>
          </Router>
        </Provider>
      );
  }
}

export default App;
