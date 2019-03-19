import React from "react";
import './login.css'

class LoginForm extends React.Component {

  render() {
    return (
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
                    <form role="form">
                      <div className="input-group">
                        <span className="input-group-addon"></span>
                        <input type="text" className="form-control" placeholder="Имя пользователя" required autoFocus/>
                      </div>
                      <div className="input-group">
                        <span className="input-group-addon"></span>
                        <input type="password" className="form-control" placeholder="Ваш пароль" required/>
                      </div>
                      <p>
                        <a href="#">Забыли свой пароль?</a></p>У вас нет аккаунта? <a href="#">Регистрация</a>
                    </form>
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
                    <button type="button" className="btn btn-labeled btn-success">
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
    );
  }
}

export default LoginForm;
