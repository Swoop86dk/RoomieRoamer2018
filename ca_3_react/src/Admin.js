import React, { Component } from 'react';
import './Admin.css';
import logo from './assets/img/smalllogo.png';
import facade from "./apiFacade";





const url = 'http://localhost:8084/RoomieRoamer/api/User/allasmap';


class Admin extends Component {

    constructor(props) {
        super(props);
        this.state = { dataAll: [] };


    }
    deleteDesc = (id) => {
        fetch('http://localhost:8084/RoomieRoamer/api/User/' + id, {
  method: 'put',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({desc: ''})
})
.then(res => res.text())
.then(res => alert(res))


    }
    // get all data
    componentDidMount() {
        fetch(url)
            .then(response => response.json())
            .then((({ Result: dataAll }) => this.setState({ dataAll })));
    }
    // renders list from database
    render() {
        let all = this.state.dataAll;
        return (
            <div id="admin">
<div className="logoAndTyped">
            <img src={logo} className="App-logo" alt="logo" />
            <h4 id="greentext"> Roomie</h4>
            <h4 id="orangetext">Roamer</h4>
            <div className="loginBut">
            <button className="ui green button" onClick={facade.logout}>logout</button>
            </div>
          </div>
                <thead id="users">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Delete Description</th>
                    </tr>
                </thead>
                
                {all.map(value => <AllData key={value.id} knap={this.deleteDesc} input={value} />)}
                

            </div>
        )
    }
}

// items to be printed from the database
const AllData = (props) => 

    <tbody id="users">
        <tr>
            <td>{props.input.id}</td>
            <td>{props.input.Name}</td>
            <td>{props.input.Desc}</td>
            <td> <button type="button" onClick={() => props.knap(props.input.id)} >Delete Desc</button> </td>
        </tr>
    </tbody>







export default Admin;