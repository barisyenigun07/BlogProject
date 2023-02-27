import { AppBar, Box, Button, Toolbar, Typography } from '@mui/material';

import React, { Component } from 'react'

class Navbar extends Component {
  state = {
    logged_in: true
  }

  render() {
    return (
      <div>
        <Box sx={{flexGrow: 1}}>
          <AppBar position='static' style={{background:'black'}}>
            <Toolbar>
              <Typography variant='h6' component="div" sx={{flexGrow : 1}}>Blog Application</Typography>
              {this.state.logged_in ? <Button color='inherit'>Log Out</Button> : <Button color='inherit'>Register</Button>}
              
              <Button color='inherit'>Login</Button>
              
            </Toolbar>
          </AppBar>

        </Box>
      </div>
    )
  }
}


export default Navbar;
