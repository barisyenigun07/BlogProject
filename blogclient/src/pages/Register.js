import React, { useEffect } from 'react'
import { Grid, Typography, Box, TextField, Stack, Button, InputAdornment, Divider, CircularProgress } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import EmailIcon from '@mui/icons-material/Email';
import LockIcon from '@mui/icons-material/Lock';
import LockPersonIcon from '@mui/icons-material/LockPerson';
import { useFormik } from 'formik';


import logo from '../assets/logo_transparent.png';
import registerImage from '../assets/register-page-image.jpg'
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { registerUser } from '../redux/authActions';
import SnackbarMessage from '../components/SnackbarMessage';
import { messageActions } from '../redux/messageSlice';

const Register = () => {
  const navigate = useNavigate();
  const { loading, registerSuccess } = useSelector((state) => state.auth);
  const { message } = useSelector((state) => state.message);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(messageActions.clearMessage());
  }, [dispatch]);

  const formik = useFormik({
    initialValues: {
      name: "",
      username: "",
      email: "",
      password: "",
      passwordRepeat: ""
    },
    onSubmit: async (values) => {
      dispatch(registerUser(values))
        .unwrap()
        .then(() => {
          setTimeout(() => {
            navigate("/login");
            window.location.reload();
          }, 1000);
        })
        .catch(() => {

        })
      
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
                      {loading ? <CircularProgress/> : null}
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
      <SnackbarMessage
        open={message !== ""}
        message={message}
        severity={registerSuccess ? 'success' : 'error'}
      />
    </>
  )
}

export default Register