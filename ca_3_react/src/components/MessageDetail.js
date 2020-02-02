import React, { Component } from "react";
import "./MessageDetail.css";

class MessageDetail extends Component {
  state = { msgHistory: [] };

  componentDidUpdate() {
    if (this.state.msgHistory.length !== this.props.chatHistory.length) {
      this.setState({ msgHistory: this.props.chatHistory });
    }
  }

  renderMsgList = () => {
    return this.state.msgHistory.map(message => {
      if (message.sender !== this.props.pressed) {
        return (
          <div class="outgoing_msg">
            <div class="sent_msg">
              <p>{message.msg}</p>
            </div>
          </div>
        );
      } else {
        return (
          <div className="inc_msg">
            <img
              className="ui avatar image msg"
              src="https://www.w3schools.com/howto/img_avatar.png"
              alt="avatar"
            />
            <div className="received_width_msg">
              <p>{message.msg}</p>
            </div>
          </div>
        );
      }
    });
  };

  render() {
    return (
      <div>
        <div className="messaging">
          <div id="options-holder" className="msg_history">
            {this.renderMsgList()}
          </div>
        </div>
      </div>
    );
  }
}

export default MessageDetail;
