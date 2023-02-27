import { Button, TextField } from '@mui/material';
import axios from 'axios';
import React, { Component } from 'react'

class Login extends Component {
  state = {
    username: null,
    password: null
  }

  handleOnChange = (event) => {
      const name = event.target.name;
      const value = event.target.value;

      this.setState({
        [name]:value
      })
  }

  onClickLogin = (event) => {
    event.preventDefault();
    const {username, password} = this.state;
    const body = {
      username: username,
      password: password
    };

    axios.post("/login",body).then(res => {
      const token = res.data.token;
      localStorage.setItem("token",token);
      //setAuthToken(token);
    })
    .catch(err => alert(err));
  }


  render() {
    return (
      <div>
        <h1>Login Page</h1>
        <form>
          <div>
            <TextField type='text' name='username' label='Username' variant='outlined' onChange={this.handleOnChange} />
          </div>
          <div>
            <TextField type='password' name='password' label='Password' variant='outlined' onChange={this.handleOnChange}/>
          </div>
          <div>
            <Button type='submit' variant='contained' onClick={this.onClickLogin}>Login</Button>
          </div>
        </form>
      </div>
    )
  }
}

export default Login;
