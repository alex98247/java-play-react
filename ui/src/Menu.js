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
            <Button to={"/companies"} className="btn btn-success" style={{marginLeft: 10}}>Company List</Button>
            <Button tag={Link} to={"/games"} className="btn btn-success" style={{marginLeft: 10}}>Add Game</Button>
            <Button tag={Link} to={"/company"} className="btn btn-success" style={{marginLeft: 10}}>Add Company</Button>
          </div>
          {(username) ? <UserButtons {...this.props} /> : null}
        </div>
      </nav>
    );
  }
}

class UserButtons extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    var credentials = this.props.credentials;

    return (
      <div align="right">
        <Button tag={Link} to={"/claim"} className="btn btn-success" style={{marginLeft: 10}}>Add Claim</Button>
        {(credentials.role == 'ADMIN')? <AdminButtons {this.props} /> : null}
        <a className="navbar-brand" style={{marginLeft: 10}} href="#">{credentials.username}</a>
      </div>
    );
  }
}

class AdminButtons extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <Button tag={Link} to={"/admin"} className="btn btn-success" style={{marginLeft: 10}}>Admin Panel</Button>
        <AddGames/>
      </div>
    );
  }
}

export default Menu;
