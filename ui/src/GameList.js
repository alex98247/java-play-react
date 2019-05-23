import React, {Component} from 'react';
import Menu from "./Menu";
import * as actions from "./actions"
import {connect} from "react-redux";
import {Button} from "reactstrap";

class GameList extends Component {

  wishlistEmpty = {
    id: '',
    userId: '',
    gameId: '',
    created_at: '',
    is_deleted: false
  };

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

  async addtowishlist(id){
    let empty = this.wishlistEmpty;
    empty.gameId = id;
    const response = await fetch('/api/wishlist', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(empty),
      credentials: 'include'
    });
  }

  render() {

    const right = <Button variant="contained" component="span"
                          onClick={() => this.reload(this.props.pageGames.pageNumber - 1)}> previous </Button>
    const left = <Button variant="contained" component="span"
                         onClick={() => this.reload(this.props.pageGames.pageNumber + 1)}> next </Button>

    const games = this.props.pageGames.gameList;
    const gameList = games.map(game => {
      return (
        <tr>
          <td>{game.id}</td>
          <td>{game.name}</td>
          <td>{game.popularity}</td>
          <td>{(this.props.credentials.username)? <Button
            onClick={() => this.addtowishlist(game.id)}> Add </Button>:null}
          </td>
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
              <th></th>
            </tr>
            </thead>
            <tbody>
            {gameList}
            </tbody>
          </table>
          {right}
          {left}
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
