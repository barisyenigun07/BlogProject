import React, { useState } from 'react'

const CustomSnackbar = () => {
  const [open, setOpen] = useState(false);
  
  const handleOpen = () => {
    setOpen(!open);
  }  
  
  return (
    <div>

    </div>
  )
}

export default CustomSnackbar