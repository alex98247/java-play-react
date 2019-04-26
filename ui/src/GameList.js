import React, {Component} from 'react';
import Menu from "./Menu";
import * as actions from "./actions"
import {connect} from "react-redux";

class GameList extends Component {

  state = {
    games: []
  };

  constructor(props) {
    super(props);
  }

  async componentDidMount() {
    const response = await fetch('/api/game');
    const body = await response.json();
    this.setState({games: body});
  }

  render() {

    const games = this.state.games;
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

function mapStateToProps(state) {
  return {
    credentials: state.credentials
  };
}

export default connect(mapStateToProps, actions)(GameList);
