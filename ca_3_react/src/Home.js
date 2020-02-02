import React, { Component } from "react"
import "./Home.css";
import logo from './assets/img/smalllogo.png';
import Login from './components/Login'
import SignUp from './components/SignUp'

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false,
      loginbuttonpressed: false,
      signupbuttonpressed: false,
    }
  }

  handleLoginClick = () => {
    this.setState({
      loginbuttonpressed: true
    });
  }

  handleSignUpClick = () => {
    this.setState({
      signupbuttonpressed: true
    });
  }
  handleResetClick = () => {
    this.setState({
      signupbuttonpressed: false,
      loginbuttonpressed: false

    });
  }
  // welcome message with description
  render() {
    if (this.state.loginbuttonpressed === true) {
      return (
        <div className="landing">
          
          <div className="logoAndTyped">
            <img src={logo} className="App-logo" alt="logo" />
            <h4 id="greentext"> Roomie</h4>
            <h4 id="orangetext">Roamer</h4>
            <div className="loginBut">
            <button className="ui green button" onClick={this.handleResetClick}>go back</button>
            </div>
            </div>
          <Login />
        </div>
      )
    }
    if (this.state.signupbuttonpressed === true) {
      return (
        <div className="landing">
          <div className="logoAndTyped">
            <img src={logo} className="App-logo" alt="logo" />
            <h4 id="greentext"> Roomie</h4>
            <h4 id="orangetext">Roamer</h4>
            <div className="loginBut">
            <button className="ui green button" onClick={this.handleResetClick}>go back</button>
            </div>
          <SignUp />
        </div>
        </div>
      )
    }
    else {
      return (
        <div className="landing">

          <div className="logoAndTyped">
            <img src={logo} className="App-logo" alt="logo" />
            <h4 id="greentext"> Roomie</h4>
            <h4 id="orangetext">Roamer</h4>
            <div className="loginBut">
              <button className="ui green button" onClick={this.handleLoginClick}>log In</button>
            </div>
          </div>

          <p id="maintext">
            Do you seak a roomate? <br />
            Then you came to the right place <br />
            finding a roomate has never been easier  <br />
            just start liking and find candidates

        </p>
          <div id="signupbutton">
            <button className="ui orange button" onClick={this.handleSignUpClick} >Sign Up</button>
          </div>
        </div>

      );
    }
  }
}