import React, { useEffect } from 'react'
import { Grid, Typography, Box, TextField, Stack, Button, InputAdornment, Divider } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import EmailIcon from '@mui/icons-material/Email';
import LockIcon from '@mui/icons-material/Lock';
import LockPersonIcon from '@mui/icons-material/LockPerson';
import { useFormik } from 'formik';
import { register } from '../api/auth.api';

import logo from '../assets/logo_transparent.png';
import registerImage from '../assets/register-page-image.jpg'
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

const Register = () => {
  const navigate = useNavigate();
  const {loading, authUser, error, success} = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  useEffect(() => {
    if (success) {
      navigate("/login");
    }
  }, [navigate, authUser, success]);

  const formik = useFormik({
    initialValues: {
      name: "",
      username: "",
      email: "",
      password: "",
      passwordRepeat: ""
    },
    onSubmit: async (values) => {
      
    }
  });
  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={7} 
          sx={{
            backgroundImage: `url(${registerImage})`,
            backgroundSize: "cover",
            position: "relative",
            display: "flex",
            justifyContent: "center",
            height: "101vh"
          }}
        >
        </Grid>
        <Grid item xs={5}>
          <Box sx={{textAlign: "center", mt: 15}}>
            <Stack spacing={2}>
              <Box>
                <img src={logo} alt='Logo' width={"100px"} height={"100px"}/>
                <Typography sx={{fontSize: "30px", color: "#000"}}>Kayıt Ol</Typography>
              </Box>
              <Box>
                <form onSubmit={formik.handleSubmit}>
                  <Stack spacing={2}>
                    <TextField
                      id='name' 
                      type='text' 
                      value={formik.values.name} 
                      onChange={formik.handleChange} 
                      fullWidth 
                      label="İsim Soyisim" 
                      InputProps={{startAdornment: (
                      <InputAdornment position='start'>
                        <PersonIcon/>
                      </InputAdornment>
                      ),
                    }}/>
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
                      id='email' 
                      type='text' 
                      value={formik.values.email} 
                      onChange={formik.handleChange} 
                      fullWidth 
                      label="Email" 
                      InputProps={{startAdornment: (
                      <InputAdornment position='start'>
                        <EmailIcon/>
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
                    <TextField 
                      id='passwordRepeat'
                      type='password' 
                      value={formik.values.passwordRepeat} 
                      onChange={formik.handleChange} 
                      fullWidth 
                      label="Şifre Tekrar"
                      InputProps={{startAdornment: (
                        <InputAdornment position='start'>
                          <LockPersonIcon/>
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
                      Kayıt Ol
                    </Button>
                  </Stack>
                </form>
              </Box>
              <Box>
                <Typography>Zaten hesabınız var mı? <Link style={{color: "aquamarine", textDecoration: "none"}} to={"/login"}>Giriş Yap!</Link></Typography>
              </Box>
            </Stack>
          </Box>
          <Box mt={2}>
            <Divider><Typography>Ya da</Typography></Divider>
          </Box>
        </Grid>
      </Grid>
    </>
  )
}

export default Register