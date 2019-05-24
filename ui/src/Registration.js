import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';

//Role


class Registration extends Component {

  emptyForm = {
    login: '',
    password: '',
    role: 'USER',
    created_at: new Date()
  };

  constructor(props) {
    super(props);
    this.state = {
      user: this.emptyForm,
      passwordCh: ''
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const name = target.name;
    if (name === "passwordCh") {
      let passwordCh = target.value;
      this.setState({passwordCh: passwordCh})
    } else {
      let user = {...this.state.user};
      user[name] = target.value;
      this.setState({user: user});
    }
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {user, passwordCh} = this.state;
    console.log(user);
    if (user.password === passwordCh) {
      await fetch('/api/user', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(user),
        credentials: 'include'
      });
      this.props.history.push('/login');
    }
  }

  render() {
    const {user, passwordCh} = this.state;

    let errorPassword =<div></div>;
    if((user.password !== passwordCh)&&(passwordCh !== '') ) {
      errorPassword = <div style={{color: 'red'}}>Passwords must match</div>;
    }

    return <div>
      <Container>
        <h2>Registration</h2>
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="login" id="login" value={user.login || ''}
                   onChange={this.handleChange} autoComplete="login"/>
          </FormGroup>
          <FormGroup>
            <Label for="password">Password</Label>
            <Input type="text" name="password" id="password" value={user.password || ''}
                   onChange={this.handleChange} autoComplete="password"/>
          </FormGroup>
          <FormGroup>
            <Label for="pass">Confirm Password</Label>
            <Input type="text" name="passwordCh" id="password" value={passwordCh || ''}
                   onChange={this.handleChange} autoComplete="passwordCh"/>
          </FormGroup>
          <FormGroup>
            <div>
            {errorPassword}
            </div>
            <Button className="btn_name" color="primary" type="submit">Save</Button>
            <Button className="btn_name" color="secondary" tag={Link} to="/user" style={{marginRight: 900}}>Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(Registration);
