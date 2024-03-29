import SearchIcon from '@mui/icons-material/Search';
import NotificationsIcon from '@mui/icons-material/Notifications';
import { styled, alpha } from '@mui/material/styles';
import { AppBar, Box, IconButton, Toolbar, Typography, InputBase, Badge, Button, Stack, Avatar, Menu, MenuItem } from '@mui/material'
import  logo from '../assets/logo_transparent.png'
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
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

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [anchorElUser, setAnchorElUser] = useState(null);

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  }

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  }

  
  
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
                height: "45px",
                marginTop: "5px"
              }}
            >
              <Link to={"/post/create"} style={{textDecoration: "none", color: "white"}}>Yayınla</Link>
            </Button>
            <IconButton size='large' aria-label='show notifications' color='inherit'>
              <Badge badgeContent={4} color='error'>
                <NotificationsIcon/>
              </Badge>
            </IconButton>
            <Box>
              <IconButton onClick={handleOpenUserMenu}>
                {authUser?.profilePhotoLink == null ? <Avatar/> : <Avatar src={`http://localhost:8080/user/${authUser?.id}/profile-photo/download`}/>}
              </IconButton>
              <Menu
                sx={{mt: "45px"}}
                id='menu-appbar'
                anchorEl={anchorElUser}
                anchorOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseUserMenu}
              >
                <MenuItem key={"profile"} onClick={(e) => {
                  handleCloseUserMenu();
                  window.location.reload();
                }}>
                  <Typography><Link style={{textDecoration: "none", color: "black"}} to={`/user/${authUser?.id}`}>Profil</Link></Typography>
                </MenuItem>
                <MenuItem key={"logout"} onClick={(e) => {
                  handleCloseUserMenu();
                  dispatch(authActions.logout());
                  navigate("/login");
                }}>
                  <Typography color={"error"}>Çıkış Yap</Typography>
                </MenuItem>
              </Menu>
            </Box>
          </Stack>)}
          
      </Toolbar>
      
      </AppBar>
      <Toolbar/>
    </>
  )
}

export default Navbar
