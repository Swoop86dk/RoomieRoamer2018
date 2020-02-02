import React, { Component } from 'react';

const urlall = 'http://localhost:8080/RoomieRoamer/api/User/allasmap';


class AdminGetAllUsers extends Component {

    constructor(props) {
        super(props);
        this.state = { data: [], dataAll: [],
            cur: 0
        };

    }
    // get all data
    getalldata = () => {
        fetch(urlall)
        .then(response => response.json())
        .then((({results: dataAll}) =>this.setState({dataAll})));
    }
    // renders list from database
    render() {
    let all = this.state.dataAll;
    console.log("all", all);
    //  console.log("items from api: " + data);
    return (
            <div >
                <table>        
                    <tr>  
                        
                        {all.map(value => <AllData key={value.name} input = {value}/>)}
                        
                    </tr>
                        <button onClick = {this.getalldata}>Get all</button>
                
                
                </table>
            </div>
    )
}
}

const AllData = (props) => <td>{props.input.name}</td>

export default AdminGetAllUsers;