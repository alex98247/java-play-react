import React, {Component} from 'react';

class Error extends Component {
  constructor(props){
    super(props);
  }
  render() {
    return (<h1>this.props.code</h1>);
  }
}
