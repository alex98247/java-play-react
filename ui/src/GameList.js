import React, {Component} from 'react';
import Menu from "./Menu";
import * as actions from "./actions"
import {connect} from "react-redux";
import {Button} from "reactstrap";

class GameList extends Component {

  constructor(props) {
    super(props);
  }

  async reload(page) {
    const response = await fetch('/api/games/' + page);
    const body = await
      response.json();
    this.props.setGames(body);
  }

  async componentWillMount() {
    await this.reload(this.props.pageGames.pageNumber);
  }

  render() {

    const right = <Button variant="contained" component="span" onClick={() => this.reload(this.props.pageGames.pageNumber-1)}> previous </Button>
    const left =  <Button variant="contained" component="span" onClick={() => this.reload(this.props.pageGames.pageNumber+1)}> next </Button>

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
        <div align="center" width="100%" className="bg-dark">
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
        {left}
        {right}
        </div>
      </div>
    );
  }
};

function mapStateToProps(state) {
  return {
    credentials: state.credentials,
    pageGames: state.pageGames
  };
}

export default connect(mapStateToProps, actions)(GameList);
