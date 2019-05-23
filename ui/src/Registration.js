import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';

//Role


class Registration extends Component {

  emptyForm = {
    login: '',
    password: '',
    role: 'user',
    created_at: new Date()
  };

  constructor(props) {
    super(props);
    this.state = {
      user: this.emptyForm,
      password2: ''
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const name = target.name;
    if (name === "password2") {
      let password2 = target.value;
      this.setState({password2: password2})
    } else {
      let user = {...this.state.user};
      user[name] = target.value;
      this.setState({user: user});
    }
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {user, password2} = this.state;
    if (user.password === password2) {
      await fetch('/api/user', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(user),
        credentials: 'include'
      });
      this.props.history.push('/user');
    }
  }

  render() {
    const {user, password2} = this.state;

    let errorPassword =<h1></h1>;
    if(user.password !== user.password2) {
      errorPassword = <h1 style={{color: 'red'}}>Passwords must match</h1>;
    }

    return <div>
      <Container>
        <h2>Registration</h2>
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="number">Name</Label>
            <Input type="text" name="" id="login" value={user.login || ''}
                   onChange={this.handleChange} autoComplete="login"/>
          </FormGroup>
          <FormGroup>
            <Label for="model">Password</Label>
            <Input type="text" name="password" id="password" value={user.password || ''}
                   onChange={this.handleChange} autoComplete="password"/>
          </FormGroup>
          <FormGroup>
            <Label for="year">Password</Label>
            <Input type="text" name="passwordCheck" id="passwordCheck" value={password2 || ''}
                   onChange={this.handleChange} autoComplete="password2"/>
          </FormGroup>
          <FormGroup>
            {{errorPassword}}
            <Button className="btn_name" color="primary" type="submit">Save</Button>
            <Button className="btn_name" color="secondary" tag={Link} to="/user">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(Registration);
