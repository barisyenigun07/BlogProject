import { Button } from '@mui/material';
import React, { Component } from 'react'

class VideoPost extends Component {
  render() {
    return (
      <div>
        <Button variant='contained'>
          <input type="file"/>
        </Button>
      </div>
    )
  }
}

export default VideoPost;
