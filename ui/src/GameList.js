import React, {Component} from 'react';
import Menu from "./Menu";
import * as actions from "./actions"
import {connect} from "react-redux";
import {Button} from "reactstrap";

class GameList extends Component {

  constructor(props) {
    super(props);
  }

  async reload() {
    const response = await fetch('/api/games/' + this.props.pageGames);
    console.log(response);
    const body = await
      response.json();
    this.props.setGames(body);
  }

  async componentWillMount() {
    await reload();
  }

  render() {

    const games = this.props.pageGames.gameList;
    const gameList = games.map(game => {
      return (
        <tr>
          <td>{game.id}</td>
          <td>{game.name}</td>
          <td>{game.popularity}</td>
        </tr>);
    });

    return (
      <div>
        <Menu {...this.props}/>
        <table style={{marginBottom: 0}} className="table table-dark">
          <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Popularity</th>
          </tr>
          </thead>
          <tbody>
          {gameList}
          </tbody>
        </table>
        <div align="center" width="100%" className="bg-dark">
        </div>
      </div>
    );
  }
};

class Left extends Component {
  render() {
    return (
      <Button variant="contained" component="span" onClick={() => this.reload()}> previous </Button>
    );
  }
};

function mapStateToProps(state) {
  console.log(state);
  return {
    credentials: state.credentials,
    pageGames: state.pageGames
  };
}

export default connect(mapStateToProps, actions)(GameList);
