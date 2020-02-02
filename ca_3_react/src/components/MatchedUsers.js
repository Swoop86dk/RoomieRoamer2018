import React, { Component } from "react";
import "./MatchedUsers.css";
import MessageDetail from "./MessageDetail";
import facade from "../apiFacade";

class MatchedUsers extends Component {
  state = {
    matchedList: [],
    clickedUser: 0,
    chatId: 0,
    msgHistory: [],
    preparedMessage: "",
    messageState: false
  };

  componentDidMount() {
    fetch(
      "http://localhost:8084/RoomieRoamer/api/User/usermatches",
      facade.makeOptions("GET", true)
    )
      .then(response => response.json())
      .then(json => {
        console.log(json);
        this.setState({ matchedList: json });
      });
  }

  componentDidUpdate() {
    fetch(
      `http://localhost:8084/RoomieRoamer/api/chat/${
        this.state.clickedUser
      }/chat`,
      facade.makeOptions("GET", true)
    )
      .then(response => response.json())
      .then(json => {
        if (
          this.state.msgHistory.length !== json.History.length &&
          this.state.chatId === json.ID
        ) {
          this.setState({
            chatId: json.ID,
            msgHistory: json.History
          });
        }
      });
  }

  getMatchHistory = userId => {
    fetch(
      `http://localhost:8084/RoomieRoamer/api/chat/${userId}/chat`,
      facade.makeOptions("GET", true)
    )
      .then(response => response.json())
      .then(json => {
        this.setState({
          chatId: json.ID,
          clickedUser: userId,
          msgHistory: json.History
        });
      });
  };

  showUserList = () => {
    return this.state.matchedList.map(user => {
      return (
        <div
          onClick={() => {
            this.getMatchHistory(user.id);
          }}
          key={user.id}
          className="ui segment users"
        >
          <img
            className="ui avatar image"
            src="https://www.w3schools.com/howto/img_avatar.png"
            alt="avatar"
          />
          <span>{user.userName}</span>
        </div>
      );
    });
  };

  sendMessage = event => {
    event.preventDefault();
    fetch(
      `http://localhost:8084/RoomieRoamer/api/chat/${
        this.state.clickedUser
      }/send`,
      facade.makeOptions("POST", true, {
        msg: this.state.preparedMessage,
        chat: this.state.chatId
      })
    )
      .then(response => response.json())
      .then(json => {
        this.setState({messageState: json})
      });
  };

  handleChange = event => {
    this.setState({ preparedMessage: event.target.value });
  };

  render() {
    return (
      <div>
        <div className="ui bottom attached segment pushable">
          <div className="ui visible inverted left vertical sidebar menu">
            {this.showUserList()}
          </div>
          <div className="pusher">
            <div className="ui basic segment" />
            <MessageDetail
              chatHistory={this.state.msgHistory}
              pressed={this.state.clickedUser}
            />
            <div className="formChat">
              <form onSubmit={this.sendMessage}>
                <textarea
                  onChange={this.handleChange}
                  value={this.state.preparedMessage}
                  rows="8"
                  cols="50"
                  name="comment"
                />
                <button type="submit" class="ui inverted orange button">
                  SEND
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default MatchedUsers;
