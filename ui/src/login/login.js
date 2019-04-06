import React from "react";
import './login.css'

class LoginForm extends React.Component {

  emptyCredentials = {
    username: '',
    password: ''
  };

  state = {credentials: this.emptyCredentials};

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let credentials = {...this.state.credentials};
    credentials[name] = value;
    this.setState({credentials: credentials});
  }

  async componentDidMount() {
    const response = await fetch('/jwt', {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/x-www-form-urlencoded'
      }});
    const body = await response.json();
    console.log(body);
  }

  async handleSubmit(event) {
    event.preventDefault();

    const {credentials} = this.state;

    var formData = new FormData();

    formData.append("username", credentials.username);
    formData.append("password", credentials.password);

    const response = await fetch('/callback?client_name=FormClient', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: 'username='+credentials.username + '&'+
        'password='+credentials.password,
      credentials: 'include'
    });
    const body = await response;
    console.log(body);
  }

  render() {
    return (
      <form role="form" onSubmit={this.handleSubmit}>
        <div className="container">
          <div className="row">
            <div className="col-xs-12 col-sm-12 col-lg-6">
              <div className="panel panel-primary">
                <div className="panel-heading">
                  <h3 className="panel-title">Авторизация на сайте</h3>
                </div>
                <div className="panel-body">
                  <div className="row">
                    <div className="col-xs-6 col-sm-6 col-md-6 separator social-login-box"><br/>
                      <a href="#" className="btn facebook btn-block" role="button">Войти с помощью Facebook</a>
                      <br/>
                      <a href="#" className="btn twitter btn-block" role="button">Войти с помощью Twitter</a>
                    </div>
                    <div className="col-xs-6 col-sm-6 col-md-6 login-box">
                      <div className="input-group">
                        <span className="input-group-addon"></span>
                        <input name="username" type="text" className="form-control" placeholder="Имя пользователя" required autoFocus onChange={this.handleChange}/>
                      </div>
                      <div className="input-group">
                        <span className="input-group-addon"></span>
                        <input name="password" type="password" className="form-control" placeholder="Ваш пароль" onChange={this.handleChange} required/>
                      </div>
                      <p>
                        <a href="#">Забыли свой пароль?</a></p>У вас нет аккаунта? <a href="#">Регистрация</a>
                    </div>
                  </div>
                </div>
                <div className="panel-footer">
                  <div className="row">
                    <div className="col-xs-6 col-sm-6 col-md-6">
                      <div className="checkbox">
                        <label>
                          <input type="checkbox" value="Remember"/>
                          Запомнить меня </label>
                      </div>
                    </div>
                    <div className="col-xs-6 col-sm-6 col-md-6">
                      <button type="submit" className="btn btn-labeled btn-success">
                        <span className="btn-label"><i className="glyphicon glyphicon-ok"></i></span>Войти
                      </button>
                      <button type="button" className="btn btn-labeled btn-danger">
                        <span className="btn-label"><i className="glyphicon glyphicon-remove"></i></span>Выход
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>

    );
  }
}

export default LoginForm;
