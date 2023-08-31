import { Box, Grid } from '@mui/material';
import React, { useState } from 'react'

const CreatePost = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [captionPhoto, setCaptionPhoto] = useState(null);
  const [tag, setTag] = useState(null);
  const [postType, setPostType] = useState("");
  const [content, setContent] = useState(null);

  const handleSubmit = () => {
    
  }

  return (
    <div>
      <Box>

      </Box>
      <Grid>
        
      </Grid>
    </div>
  )
}

export default CreatePost