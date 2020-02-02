import React, { Component } from "react";
import facade from "./apiFacade";
import "./UserPage.css";
import { Link } from "react-router-dom";
import EditDesc from './components/EditDesc'

export default class UserPage extends Component {
  constructor(props) {
    super(props);
    this.state = { value: "" };
    this.state = { targetId: "" };
    this.state = { myId: "" };
    this.state = { pomaList: []};
    this.state = { pLId: 0};
    
  }

  // fetch to "GET" the ID of the user that is currently logged in

  

  userLiked() {
    var URL =
      "http://localhost:8084/RoomieRoamer/api/User/like/" +
      this.state.myId + "/" + this.state.targetId;
    fetch(URL, facade.makeOptions("PUT", true))
      .then(response => response.json())
      .then(json => {
        console.log("I liked " + json);
      });
  }
  userIgnored() {
    var URL =
      "http://localhost:8084/RoomieRoamer/api/User/ignore/" +
      this.state.myId + "/" + this.state.targetId;
    fetch(URL, facade.makeOptions("PUT", true))
      .then(response => response.json())
      .then(json => {
        console.log("I disliked " + json);
      });
  }

  handleChangeUP = event => {
    console.log("this is event.target.value: " + event.target.value);
    this.setState({ value: event.target.value });
  };


  componentWillMount() {
    var URL = "http://localhost:8084/RoomieRoamer/api/User/uid/";
    fetch(URL, facade.makeOptions("GET", true))
      .then(response => response.json())
      .then(json => {
        console.log("Current users ID who is logged in: " + json.id);
        this.setState({ myId: json.id });
        

      });

      this.setState({pLId: 0})
      

    this.handleSubmitUP();
    
  }

  componentDidUpdate() {
    console.log("CDU");
        try {         
            document.getElementById("desc1").innerHTML =
              this.state.pomaList.results[this.state.pLId].Name +
              "</br>" +
              this.state.pomaList.results[this.state.pLId].Id +
              "</br>" +
              this.state.pomaList.results[this.state.pLId].Desc;
            
              if (this.state.pomaList.results[this.state.pLId].Id !== this.state.targetId) {
                this.setState({ targetId: this.state.pomaList.results[this.state.pLId].Id });
              }
            //console.log("THIS IS THE ID: " + this.state.targetId);
            //console.log("and id should be= " + this.state.pomaList.results[this.state.pLId].Id);
            
          }
        
        catch {
          document.getElementById("desc1").innerHTML =
            "Whoops something went wrong - CDU";
        }      
      }
  

  handleSubmitUP() {
    console.log("CWM");
    var URL = "http://localhost:8084/RoomieRoamer/api/User/poma";
    fetch(URL, facade.makeOptions("GET", true))
      .then(response => response.json())
      .then(json => {
        //console.log("JSON: " + json)
        this.setState({ pomaList: json});

        try {
          
        console.log("POMALIST ");console.log(this.state.pomaList);

          if (json === undefined) {
            document.getElementById("desc1").innerHTML =
              "HSUP No users could be found. Come back later.";
          } else {
            
            document.getElementById("desc1").innerHTML =
              this.state.pomaList.results[this.state.pLId].Name +
              "</br>" +
              this.state.pomaList.results[this.state.pLId].Id +
              "</br>" +
              this.state.pomaList.results[this.state.pLId].Desc;

            
            this.setState({ targetId: this.state.pomaList.results[this.state.pLId].Id });
            
            
            /*if (this.state.pomaList.results[pLId].Id !== this.state.targetId) {
              this.setState({ targetId: this.state.pomaList.results[pLId].Id });
            }*/
            
          }
        } catch {
          
          document.getElementById("desc1").innerHTML =
            "Whoooops something went wrong - HSU";
        }
      });
  }

  likeThat = (event) => {
    event.preventDefault()
    this.userLiked();
    this.setState({pLId: this.state.pLId+1});
    console.log("PLID"+this.state.pLId);
  };
  dislikeThat = (event) => {
    event.preventDefault()
    this.userIgnored();
    this.setState({pLId: this.state.pLId+1});
    console.log("PLID"+this.state.pLId);
  };

  render() {
    
    return (
      <div id="userpagebody">
        <div id="funktionbox">
          <p id="desc1">Loading ...</p>
          <button onClick={facade.logout}>Logout</button>
          <form onClick={this.likeThat}>
            <input id="likebtn" type="button" value="Like" />
          </form>
          <form onClick={this.dislikeThat}>
            <input id="dislikebtn" type="button" value="Dislike" />
          </form>
          <Link to="/messages">messages</Link>
          <EditDesc id={this.state.myId}/>
        </div>
      </div>
    );
  }
}

