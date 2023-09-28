import SearchIcon from '@mui/icons-material/Search';
import NotificationsIcon from '@mui/icons-material/Notifications';
import { styled, alpha } from '@mui/material/styles';
import { AppBar, Box, IconButton, Toolbar, Typography, InputBase, Badge, Button, Stack, Avatar } from '@mui/material'
import  logo from '../assets/logo_transparent.png'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { authActions } from '../redux/authSlice';

const Search = styled('div')(({ theme }) => ({
  position: 'relative',
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  '&:hover': {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginRight: theme.spacing(2),
  marginLeft: 0,
  width: '100%',
  [theme.breakpoints.up('sm')]: {
    marginLeft: theme.spacing(40),
    width: 'auto',
  },
}));

const SearchIconWrapper = styled('div')(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: '100%',
  position: 'absolute',
  pointerEvents: 'none',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: 'inherit',
  '& .MuiInputBase-input': {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('md')]: {
      width: '100ch',
    },
  },
}));




const Navbar = () => {
  const { authUser } = useSelector((state) => state.auth);
  const [searchedItem, setSearchedItem] = useState("");
  
  return (
    <>
    <AppBar style={{ background: '#2E3B55' }}>
      <Toolbar sx={{justifyContent: "center"}}>
          <Stack
            direction={"row"}
            spacing={2}
            component={Link}
            to="/"
            sx={{
              alignItems: 'center',
              textDecoration: "none",
            }}
          >
            <Box
              component={"img"}
              sx={{
                height: 64,
              }}
              alt='Logo'
              src={logo}
            />
            <Typography
                variant='h6'
                
                sx={{
                  mr: 2,
                  display: {xs: 'none', md: 'flex'},
                  fontWeight: 700,
                  letterSpacing: '.3rem',
                  color: 'white',
                  
              }}>
                JOTTER
            </Typography>
            </Stack>
            
          <Search>
            <SearchIconWrapper>
              <SearchIcon/>
            </SearchIconWrapper>
            <StyledInputBase
              placeholder='Ara...'
              inputProps={{'aria-label': 'search'}}
              sx={{
                width: "700px",
              }}
              value={searchedItem}
              onChange={(e) => setSearchedItem(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  e.preventDefault();
                  console.log(searchedItem);
                  setSearchedItem("");
                }
                
              }}
            />
          </Search>
          <Box sx={{flexGrow: 1}}/>
          {(authUser == null ? 
          <Stack direction={"row"} spacing={3}>
            <Button
              color='warning'
              variant='contained'
              sx={{
                height: "45px"
              }}
            >
              <Link to={"/register"} style={{textDecoration: "none", color: "white"}}>Kayıt Ol</Link>
            </Button>
            <Button
              color='warning'
              variant='contained'
              sx={{
                height: "45px"
              }}
            >
              <Link to={"/login"} style={{textDecoration: "none", color: "white"}}>Giriş yap</Link>
            </Button>
          </Stack> 
          :
          <Stack direction={"row"} spacing={3}>
            <Button
              color='warning'
              variant='contained'
              sx={{
                height: "45px"
              }}
            >
              <Link to={"/post/create"} style={{textDecoration: "none", color: "white"}}>Yayınla</Link>
            </Button>
            <IconButton size='large' aria-label='show notifications' color='inherit'>
              <Badge badgeContent={4} color='error'>
                <NotificationsIcon/>
              </Badge>
            </IconButton>
            {authUser?.profilePhotoLink == null ? <Avatar>{authUser?.username.charAt(0)}</Avatar> : <Avatar src={`http://localhost:8080/user/${authUser?.id}/profile-photo/download`}/>}
          </Stack>)}
          
      </Toolbar>
      
      </AppBar>
      <Toolbar/>
    </>
  )
}

export default Navbar
