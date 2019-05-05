import React, {Component} from 'react';
import {Button, Form} from 'reactstrap';

class AddGames extends Component {

  adminParams = {
    gamesCount: ''
  };

  state = {adminParams: this.adminParams};

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

    await fetch('api/addgames?token=' + this.props.credentials.token, {
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
      <Form style={{marginLeft: 10}} onSubmit={this.handleSubmit}>
        <input type="text" name="gamesCount" id="gamesCount" value={adminParams.gamesCount || ''}
               onChange={this.handleChange}/>
        <Button style={{marginLeft: 10}} color="primary" className="btn" type="submit">Add Games</Button>
      </Form>
    )
  }
}

export default AddGames;
