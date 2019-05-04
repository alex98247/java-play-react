import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import AddGames from "./AddGames";

class Menu extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    var username = this.props.credentials.username;
    return (
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <a className="navbar-brand" href="#">Menu</a>
        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div className="navbar-nav">
            <Button to={"/"} className="btn btn-success" style={{marginLeft: 10}}>Game List</Button>
            <Button tag={Link} to={"/games"} className="btn btn-success" style={{marginLeft: 10}}>Add Game</Button>
            {(username) ? null :
              <Button tag={Link} to={"/login"} className="btn btn-success" style={{marginLeft: 10}}>Login</Button>}
          </div>
          {(username) ? <UserButtons {...this.props} /> : null}
        </div>
      </nav>
    );
  }
}

class UserButtons extends Component {

  credentials = {
    id: '',
    username: '',
    password: '',
    token: '',
    status: 200,
    roles: []
  };

  constructor(props) {
    super(props);
    this.logout = this.logout.bind(this);
  }

  state = {credentials: this.credentials};


  async logout() {
    const {credentials} = this.state;
    this.props.addCredentials(credentials);

    await fetch('/logout').then(async res => console.log(await res));
  }

  render() {
    var credentials = this.props.credentials;

    return (
      <>
        <Button tag={Link} to={"/claim"} className="btn btn-success" style={{marginLeft: 10}}>Add Claim</Button>
        {(credentials.roles.indexOf('ADMIN') != -1) ? <AdminButtons {...this.props} /> : null}
        <a className="navbar-brand" style={{marginLeft: 10}} href="#">{credentials.username}</a>
        <Button onClick={this.logout} className="btn btn-success" style={{marginLeft: 10}}>Logout</Button>
      </>
    );
  }
}

class AdminButtons extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <>
        <Button tag={Link} to={"/admin"} className="btn btn-success" style={{marginLeft: 10}}>Admin Panel</Button>
        <AddGames {...this.props}/>
      </>
    );
  }
}

export default Menu;
