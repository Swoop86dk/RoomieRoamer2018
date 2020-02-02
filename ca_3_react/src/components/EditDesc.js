import React, { Component } from "react"
class EditDesc extends Component {


    constructor(props) {
        super(props);
        this.state = {
            desc: ""}
        this.state = { desc2: ""}
        };
    

    DescChange = (event) => {
        this.setState({ desc: event.target.value}); 
    }

    DescSubmit = () =>{
        fetch('http://localhost:8084/RoomieRoamer/api/User/' + this.props.id, {
  method: 'put',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({desc: this.state.desc})
  
})
.then(res => res.text())
.then(res => console.log("Dette er state"+this.state.desc))


    }

        componentWillReceiveProps =() => {
            fetch('http://localhost:8084/RoomieRoamer/api/User/'+ this.props.id +'/desc')
            .then(response => response.json())
            .then(json => {
                
                this.setState({ desc: json});
                console.log(this.props.id);
                console.log(this.state.desc2);
            })
    }

    render() {
        return (<div>
            <textarea rows="4" cols="50" value={this.state.desc} onChange={this.DescChange}>
                
                </textarea>
                <button class="ui button" onClick={this.DescSubmit}>change</button>
                </div>

        )
    }
}


export default EditDesc;