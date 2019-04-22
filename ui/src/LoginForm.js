import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';

class LoginForm extends Component {

  credentials = {
    username: '',
    password: '',
    token: ''
  };

  state = {credentials: this.credentials};

  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let credentials = {...this.state.credentials};
    credentials[name] = value;
    this.setState({credentials});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {credentials} = this.state;

    const response = await fetch('/callback?client_name=FormClient', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: "username=" + credentials.username + "&" + "password=" + credentials.password,
      credentials: 'include'
    });
    const body = await response;
    console.log(body);
  }



  render() {
    const {credentials} = this.state;

    return (
      <Form onSubmit={this.handleSubmit}>
        <FormGroup>
          <Label for="username">Username</Label>
          <Input type="text" name="username" id="username" value={credentials.username || ''} onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Label for="password">Password</Label>
          <Input type="text" name="password" id="password" value={credentials.password || ''} onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Button color="primary" className="btn" type="submit">Save</Button>
        </FormGroup>
      </Form>
    )
  }
}

export default LoginForm;
