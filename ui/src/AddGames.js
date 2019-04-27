import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';

class AddGames extends Component {

  adminParams = {
    gamesCount: ''
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
    let adminParams = {...this.state.adminParams};
    adminParams[name] = value;
    this.setState({adminParams});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {adminParams} = this.state;

    await fetch('api/adminparams  ', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(adminParams),
      credentials: 'include'
    });
  }



  render() {
    const {adminParams} = this.state;

    return (
      <Form onSubmit={this.handleSubmit}>
        <FormGroup>
          <Label for="name">Count</Label>
          <Input type="text" name="name" id="name" value={adminParams.adminParams || ''} onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Button color="primary" className="btn" type="submit">Add Games</Button>
        </FormGroup>
      </Form>
    )
  }
}

export default AddGames;
