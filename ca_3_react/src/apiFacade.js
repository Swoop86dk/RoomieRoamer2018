const URL = "http://localhost:8084/RoomieRoamer";

function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res.json();
}

class ApiFacade {
  // get user
  fetchData = () => {
    const options = this.makeOptions("GET", true);
    return fetch(URL + "/api/info/user", options).then(handleHttpErrors);
  };

  // get admin
  fetchDataAdmin = () => {
    const options = this.makeOptions("GET", true);
    return fetch(URL + "/api/info/admin", options).then(handleHttpErrors);
  };

  setToken = token => {
    localStorage.setItem("jwtToken", token);
  };

  getToken = () => {
    return localStorage.getItem("jwtToken");
  };
  // controls whether a person if logged in already
  loggedIn = () => {
    const loggedIn = this.getToken() != null;
    return loggedIn;
  };
  // logs out the user and redirects to Home
  logout = () => {
    localStorage.removeItem("jwtToken");
    alert("Thank you for visiting! Click OK to get logged out.");
    window.location.replace("/");
  };
  // validates the user credentials and redirects the user to /userpage
  login = (user, pass) => {
    const options = this.makeOptions("POST", true, {
      username: user,
      password: pass
    });
    return fetch(URL + "/api/login", options, true)
      .then(handleHttpErrors)
      .then(res => {
        this.setToken(res.token);
      })
      .then(() => {
        var URL = "http://localhost:8084/RoomieRoamer/api/User/ur/";
        fetch(URL, facade.makeOptions("GET", true))
          .then(response => response.json())
          .then(json => {
            console.log(json);
            if (this.loggedIn && json === "user ") {
              window.location.replace("/userpage");
            }
            if (this.loggedIn && json === "admin ") {
              window.location.replace("/admin");
            }
          });
      });
    //});
  };

  isAdmin() {
    return true;
  }
  isUser() {
    return true;
  }

  makeOptions(method, addToken, body) {
    var opts = {
      method: method,
      headers: {
        "Content-type": "application/json",
        Accept: "application/json"
      }
    };
    if (addToken && this.loggedIn()) {
      opts.headers["x-access-token"] = this.getToken();
    }
    if (body) {
      opts.body = JSON.stringify(body);
    }
    return opts;
  }
}

const facade = new ApiFacade();
export default facade;
