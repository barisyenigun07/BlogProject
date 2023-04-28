import { AdbIcon, MenuIcon } from '@mui/icons-material'
import { AppBar, Box, Container, IconButton, Toolbar, Typography } from '@mui/material'
import React from 'react'

const Navbar = () => {
  return (
    <AppBar position='static'>
      <Container>
        <Toolbar>
          <AdbIcon sx={{display: {xs: 'none', md: 'flex'}, mr: 1}}/>
          <Typography
              variant='h6'
              noWrap
              component={"a"}
              href="/"
              sx={{
                mr: 2,
                display: {xs: 'none', md: 'flex'},
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
            }}>
              LOGO
          </Typography>
          <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
            <IconButton
                size='large'
                aria-label='account of current user'
                aria-controls='menu-appbar'
                aria-haspopup="true"
                color='inherit'
            >
              <MenuIcon/>
            </IconButton>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  )
}

export default Navbar
