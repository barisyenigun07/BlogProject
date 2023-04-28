
import { Box, Button } from '@mui/material'
import React, { useState } from 'react'

const FileUploader = () => {
  const [file, setFile] = useState(null);
  const handleUpload = (e) => {
    setFile(e.target.files[0]);
  }
  return (
    <Box>
      <Button>
        <input type='file' onChange={handleUpload}/>
        Upload File
      </Button>
    </Box>
  )
}

export default FileUploader