import { Alert, Snackbar } from '@mui/material'
import React from 'react'

const SnackbarMessage = ({open, message, severity, handleClose}) => {
  return (
    <Snackbar anchorOrigin={{horizontal: "center", vertical: "bottom"}} open={open} autoHideDuration={6000} onClose={handleClose}>
      <Alert onClose={handleClose} severity={severity}>
        {message}
      </Alert>
    </Snackbar>
  )
}

export default SnackbarMessage