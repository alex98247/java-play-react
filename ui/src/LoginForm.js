import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';
import "./actions.js"
import {connect} from "react-redux";
import * as actions from "./actions";
import {Redirect} from "react-router-dom";

class LoginForm extends Component {

  credentials = {
    id: '',
    username: '',
    password: '',
    token: '',
    status: 200,
    roles: []
  };

  state = {credentials: this.credentials, loggedIn: false};

  constructor(props, context) {
    super(props, context);
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

    await fetch('/callback?client_name=FormClient', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: "username=" + credentials.username + "&" + "password=" + credentials.password,
      credentials: 'include'
    }).then(res => this.resultAction(res, credentials));

  }

  async resultAction(res, credentials) {
    if (await res.ok) {
      const body = await res.text();
      console.log(body);
      const token = JSON.parse(body);
      credentials.token = token.token;
      credentials.password = "";
      credentials.status = 200;
      credentials.roles = token.roles;
      this.props.addCredentials(credentials);
      setTimeout(() => this.setState({credentials: credentials, loggedIn: true}), 1 * 1000);
    } else {
      credentials.status = 403;
      this.setState({credentials});
    }
  }


  render() {
    const {credentials, loggedIn} = this.state;

    return (
      <Form onSubmit={this.handleSubmit}>
        {(loggedIn)? <Redirect to='/' /> : null}
        <FormGroup>
          <Label for="username">Username</Label>
          <Input type="text" name="username" id="username" value={credentials.username || ''}
                 onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Label for="password">Password</Label>
          <Input type="password" name="password" id="password" value={credentials.password || ''}
                 onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Button color="primary" className="btn" type="submit">Войти</Button>
        </FormGroup>
        {(credentials.status == 200) ? null : <ErrorMessage/>}
      </Form>

    )
  }
}

class ErrorMessage extends Component {
  render() {
    return (
      <div className="alert alert-danger" role="alert">
        <strong>Bad credentials</strong>
      </div>
    );
  }
};

function mapStateToProps(state) {
  return {
    credentials: state.credentials
  };
}

export default connect(mapStateToProps, actions)(LoginForm);
