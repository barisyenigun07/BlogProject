import TextField from '@mui/material/TextField';
import Button  from '@mui/material/Button';
import Box from '@mui/material/Box'
import axios from 'axios';
import React, { useState } from 'react';

const Register = () => {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordRepeat, setPasswordRepeat] = useState("");

  return(
      <Box 
        component={"form"}
        sx={{
          '& .MuiTextField-root': {m: 1, width: '40ch'}
        }}
        noValidate>
        <div>
          <TextField label="İsim Soyisim" variant='outlined' type={"text"} onChange={(e) => {
            e.preventDefault();
            setName(e.target.value);
          }}/>
        </div>
        <div>
          <TextField label="Kullanıcı Adı" variant='outlined' type={"text"} onChange={(e) => {
            e.preventDefault();
            setUsername(e.target.value);
          }}/>
        </div>
        <div>
          <TextField label="Email" variant='outlined' type={"email"} onChange={(e) => {
            e.preventDefault();
            setEmail(e.target.value);
          }}/>
        </div>
        <div>
          <TextField label="Şifre" variant='outlined' type={"password"} onChange={(e) => {
            e.preventDefault();
            setPassword(e.target.value);
          }}/>
        </div>
        <div>
          <TextField label="Şifre Doğrulama" variant='outlined' type={"password"} onChange={(e) => {
            e.preventDefault();
            setPasswordRepeat(e.target.value);
          }}/>
        </div>
        <div>
          <Button variant='contained' onClick={(e) => {
            e.preventDefault();
            const body = {
              name: name,
              username: username,
              email: email,
              password: password,
              passwordRepeat: passwordRepeat
            };
            axios.post("/register", body)
                .catch(err => alert(err));
          }}>Kayıt Ol</Button>
        </div>
        </Box>
        
  );
}

export default Register;
