import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';

class Menu extends Component {

  render() {
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
        </div>
      </nav>
    );
  }
}

export default Menu;
