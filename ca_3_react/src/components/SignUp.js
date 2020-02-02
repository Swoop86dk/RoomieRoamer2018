import React, { Component } from "react"
import "../SignUp.css";

class SignUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loggedIn: false,
            loginbuttonpressed: false,
            signupbuttonpressed: false,
        }
    }



    render() {
        return (
            <div>
            <div className="ui placeholder segment" id="devider">
  <div className="ui two column very relaxed stackable grid">
    <div className="column">
	<div id="signupform">
                <div className="ui labeled input" >
                    <div className="ui label">
                        Username
                        </div>
                    <input type="text" id="username" />
                    </div>
                
                <br />
                <div className="ui labeled input">
                    <div className="ui label">
                        Password
                        </div>
                    <input type="password" />
                    </div>
                <br />
                <div className="ui labeled input">
                    <div className="ui label">
                        numse
                        </div>
                    <input type="text" />
                    </div>
                <br />
                <select name="skills" multiple="" className="ui fluid dropdown">
  <option value="1">art</option>
<option value="2">basketball</option>
<option value="3">cyckling</option>
<option value="4">dance</option>
<option value="5">fasion</option>
<option value="6">football</option>
<option value="7">gaming</option>
<option value="8">music</option>
<option value="9">programming</option>
<option value="10">running</option>
<option value="11">singing</option>
<option value="12">skiing</option>
<option value="13">smokeing</option>
<option value="14">soccer</option>
<option value="15">swimming</option>
<option value="16">swinging</option>
<option value="17">traveling</option>
<option value="18">volleyball</option>

</select>
</div>
    </div>
    <div className="middle aligned column">
    <label>do you smoke??</label>
    <div className="ui buttons">
  <button className="ui button">nej</button>
  <div className="or"></div>
  <button className="ui button">ja</button>
</div>
<br />
<label>do you like to host parties?</label>
    <div className="ui buttons">
  <button className="ui button">nej</button>
  <div className="or"></div>
  <button className="ui button">ja</button>
</div>
<br />
<label>do you have pets?</label>
    <div className="ui buttons">
  <button className="ui button">nej</button>
  <div className="or"></div>
  <button className="ui button">ja</button>
</div>
<br />
<label>are you in a relationship?</label>
    <div className="ui buttons">
  <button className="ui button">nej</button>
  <div className="or"></div>
  <button className="ui button">ja</button>
</div>
<br />
<label>do you like loud music?</label>
    <div className="ui buttons">
  <button className="ui button">nej</button>
  <div className="or"></div>
  <button className="ui button">ja</button>
</div>
<br />
<label>do you enjoy sport?</label>
    <div className="ui buttons">
  <button className="ui button">nej</button>
  <div className="or"></div>
  <button className="ui button">ja</button>
</div>
<br />
<label>whats you budget?</label>
<select className="ui dropdown">
  <option value="1">2000-3000kr</option>
  <option value="2">3000-4000kr</option>
  <option value="3">4000-5000kr</option>
</select>
<br />
<label>where are you seeking roomate?</label>
<select className="ui dropdown">
  <option value="1">2000-3000kr</option>
  <option value="2">3000-4000kr</option>
  <option value="3">4000-5000kr</option>
</select>

<br />

<label>how do you prefer the cleaning af the appartment?</label>
<select className="ui dropdown">
  <option value="1">dont mind it do be a little messi</option>
  <option value="2">i like it clean</option>

</select>
<br />
<label>why are you looking for a roomate</label>
<select className="ui dropdown">
  <option value="1">i want to save some money</option>
  <option value="2">i want a friend</option>

</select>
    </div>
  </div>
  <div className="ui vertical divider">
    AND
    
  </div>
  
</div>
<button className="ui orange button" id="signbut">Sign Up</button>
</div>
            
        )


    }

}


export default SignUp;