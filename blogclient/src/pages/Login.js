import { Grid, Stack, Typography, TextField, Button, InputAdornment } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import LockIcon from '@mui/icons-material/Lock';
import { Box } from '@mui/system';
import { useFormik } from 'formik';
import React from 'react'

import logo from '../assets/logo_transparent.png';
import loginImage from '../assets/login-page-image.jpg';
import { login } from '../api/auth.api';
import { Link, useNavigate } from 'react-router-dom';



const Login = () => {
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      username: "",
      password: ""
    },
    onSubmit: async (values) => {
      try {
        await login(values);
        navigate("/");
      }
      catch (err) {
        alert(err);
      }
      
    }
  });
  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={7} 
          sx={{
            backgroundImage: `url(${loginImage})`,
            backgroundSize: "cover",
            position: "relative",
            display: "flex",
            justifyContent: "center",
            height: "100vh"
          }}
        >
        </Grid>
        <Grid item xs={5}>
          <Box sx={{textAlign: "center", mt: 15}}>
            <Stack spacing={2}>
              <Box>
                <img src={logo} alt='Logo' width={"100px"} height={"100px"}/>
                <Typography sx={{fontSize: "30px", color: "#000"}}>Giriş Yap</Typography>
              </Box>
              <Box>
                <form onSubmit={formik.handleSubmit}>
                  <Stack spacing={2}>
                    <TextField
                      id='username' 
                      type='text' 
                      value={formik.values.username} 
                      onChange={formik.handleChange} 
                      fullWidth 
                      label="Kullanıcı Adı" 
                      InputProps={{startAdornment: (
                      <InputAdornment position='start'>
                        <PersonIcon/>
                      </InputAdornment>
                      ),
                    }}/>
                    <TextField 
                      id='password'
                      type='password' 
                      value={formik.values.password} 
                      onChange={formik.handleChange} 
                      fullWidth 
                      label="Şifre"
                      InputProps={{startAdornment: (
                        <InputAdornment position='start'>
                          <LockIcon/>
                        </InputAdornment>
                      ),
                    }}
                    />
                    <Button 
                      variant='contained' 
                      color='error' 
                      type='submit' 
                      fullWidth
                      sx={{
                        height: "45px"
                      }}
                    >
                      Giriş Yap
                    </Button>
                  </Stack>
                </form>
              </Box>
              <Box>
                <Typography>Hesabınız yok mu? <Link to={"/register"}>Kayıt Ol!</Link></Typography>
              </Box>
            </Stack>
          </Box>
        </Grid>
      </Grid>
    </>
  )
}

export default Login
