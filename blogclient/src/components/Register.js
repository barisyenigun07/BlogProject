import TextField from '@mui/material/TextField';
import Button  from '@mui/material/Button';
import axios from 'axios';
import React, { Component } from 'react'

class Register extends Component {
  state = {
    name: null,
    email: null,
    username: null,
    password: null,
    passwordRepeat: null
  }
  
  handleOnChange = (event) => {
    const value = event.target.value;
    const field = event.target.name;
    this.setState({
        [field]: value
    })
  };
  onClickRegister = (event) => {
    event.preventDefault();
    const {name, email, username, password, passwordRepeat} = this.state;
    const body = {
        name: name,
        email: email,
        username: username,
        password: password,
        passwordRepeat: passwordRepeat
    };
    axios.post("/register",body)
    .catch(err => alert(err))
  }
  render() {
    return (
      <div>
        <form>
          <div>
            <TextField type='text' name='name' label='Name' variant='outlined' onChange={this.handleOnChange}/> <br/> <br/>
          </div>
          <div>
            <TextField type='text' name='email' label='Email' variant='outlined' onChange={this.handleOnChange}/> <br/> <br/>
          </div>
          <div>
            <TextField type='text' name='username' label='Username' variant='outlined' onChange={this.handleOnChange}/> <br/> <br/>
          </div>
          <div>
            <TextField type='password' name='password' label='Password' variant='outlined' onChange={this.handleOnChange}/> <br/> <br/>
          </div>
          <div>
            <TextField type='password' name='passwordRepeat' label='Password Repeat' variant='outlined' onChange={this.handleOnChange}/> <br/> <br/>
          <div>
            <Button type='button' name='button' variant='contained' onClick={this.onClickRegister}>Register</Button>
          </div>
            
          </div>
        </form>
      </div>
      
    )
  }
}

export default Register;
