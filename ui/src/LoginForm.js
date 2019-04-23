import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';

class LoginForm extends Component {

  credentials = {
    username: '',
    password: '',
    token: '',
    status: 200
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
    }).then(async res => {
      if (await res.ok) {
        const body = await res.text();
        var token = JSON.parse(body);
        credentials.token = token.token;
        credentials.password = "";
        credentials.status = 200;
        this.setState({credentials});
        setTimeout(() => window.location.href = '/', 1 * 1000);
      } else {
        credentials.status = 403;
        this.setState({credentials});
      }
    });

  }


  render() {
    const {credentials} = this.state;

    return (
      <Form onSubmit={this.handleSubmit}>
        <FormGroup>
          <Label for="username">Username</Label>
          <Input type="text" name="username" id="username" value={credentials.username || ''}
                 onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Label for="password">Password</Label>
          <Input type="text" name="password" id="password" value={credentials.password || ''}
                 onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Button color="primary" className="btn" type="submit">Войти</Button>
        </FormGroup>
        {(credentials.status == 200) ? null : <ErrorMessage />}
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

export default LoginForm;
