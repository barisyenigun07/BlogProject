import { Avatar, Box, Typography } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

const UserProfile = () => {
  const id = useParams();
  const [user, setUser] = useState();
  
  useEffect(() => {
    axios.get(`/user/${id}`)
         .then(res => setUser(res.data))
         .catch(err => alert(err))
  }, [id]);  
  
  return (
    <>
        <Box sx={{width: "100%", height: "150px"}}>
            <Box sx={{display: "flex", justifyContent: "center"}}>
                <Box>
                    {(user?.profilePhotoLink == null) ? 
                    <Avatar>{user?.username[0]}</Avatar> 
                    : 
                    <Avatar src={`http://localhost:8080/user/${id}/profile_photo/download`}
                    />}
                    <Typography></Typography>
                </Box>
                <Typography>{user?.name} @{user?.username}</Typography> <br/>
                
            </Box>
        </Box>
    </>
  )
}

export default UserProfile