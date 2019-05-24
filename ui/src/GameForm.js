import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label, Container} from 'reactstrap';

class GameForm extends Component {

  emptyGame = {
    name: '',
    popularity: '',
    created_at: ''
  };

  state = {game: this.emptyGame};

  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let game = {...this.state.game};
    game[name] = value;
    this.setState({game});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {game} = this.state;

    const response = await fetch('/api/game', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(game),
      credentials: 'include'
    }).then(() => setTimeout(() => window.location.href = '/', 1 * 1000));
  }



  render() {
    const {game} = this.state;

    return (
      <Container>
        <h1>Edit Game</h1>
      <Form onSubmit={this.handleSubmit}>
        <FormGroup>
          <Label for="name">Name</Label>
          <Input type="text" name="name" id="name" value={game.name || ''} onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Label for="popularity">Popularity</Label>
          <Input type="text" name="popularity" id="popularity" value={game.popularity || ''} onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Label for="created_at">Created_at</Label>
          <Input type="text" name="created_at" id="created_at" value={game.created_at || ''} onChange={this.handleChange}>
          </Input>
        </FormGroup>
        <FormGroup>
          <Button color="primary" className="btn" type="submit">Save</Button>
        </FormGroup>
      </Form>
      </Container>
    )
  }
}

export default GameForm;
