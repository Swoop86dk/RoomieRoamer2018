import React, { Component } from "react"
import facade from "../apiFacade";
import "../Login.css";
class Login extends Component {
        constructor(props) {
          super(props);
          this.state = { username: "", password: "" };
        }

        login = evt => {
            evt.preventDefault();
            facade.login(this.state.username, this.state.password);
          };
          onChange = evt => {
            this.setState({ [evt.target.id]: evt.target.value });
          };
          
    render() {
        return ( <div>
          <div id="loginform">
            <form className="ui form" onSubmit={this.login} onChange={this.onChange}>
  <div className="field">
    <label>Username</label>
    <input type="text" name="username" placeholder="username" id="username"/>
  </div>
  <div className="field">
    <label>password</label>
    <input type="password" name="password" placeholder="password" id="password" />
  </div>
  <div className="field">
  </div>
  <button className="ui button" type="submit" id="subbut">login</button>
</form>
</div>
            </div>
  

        )
    }
}

export default Login;