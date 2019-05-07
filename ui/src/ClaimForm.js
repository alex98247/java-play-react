import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';
import {connect} from "react-redux";
import * as actions from "./actions";

class ClaimForm extends Component {

  emptyClaim = {
    user_id: '',
    created_at: '',
    solved: false,
    solved_at: '',
    comment: '',
    theme: ''
  };

  state = {claim: this.emptyClaim};

  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    const {claim} = this.state;
    claim.user_id = this.props.credentials.id;
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let claim = {...this.state.claim};
    claim[name] = value;
    this.setState({claim});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {claim} = this.state;

    await fetch('/api/claim', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(claim),
      credentials: 'include'
    }).then(() => setTimeout(() => window.location.href = '/', 1 * 1000));
  }


  render() {
    const {claim} = this.state;

    return (
      <Form onSubmit={this.handleSubmit}>
        <FormGroup>
          <Label for="theme">Тема</Label>
          <Input type="text" name="theme" id="theme" value={claim.theme || ''} onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Label for="comment">Комментарий</Label>
          <Input type="textarea" rows="5" name="comment" id="comment" value={claim.comment || ''}
                 onChange={this.handleChange}/>
        </FormGroup>
        <FormGroup>
          <Button color="primary" className="btn" type="submit">Отправить</Button>
        </FormGroup>
      </Form>
    )
  }
}

function mapStateToProps(state) {
  return {
    credentials: state.credentials
  };
}

export default connect(mapStateToProps, actions)(ClaimForm);
