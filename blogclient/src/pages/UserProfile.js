import { Avatar, Box, Button, Stack, Tab, Tabs, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux';
import { Link, useParams } from 'react-router-dom'
import { getUser } from '../api/user.api';

const UserProfile = () => {
  const { id } = useParams();
  const [user, setUser] = useState({id: 0, name: "", username: "", email: "", profilePhotoLink: "", captionPhotoLink: ""});
  const [posts, setPosts] = useState([]);
  
  const { authUser } = useSelector((state) => state.auth);

  useEffect(() => {
    const fetchUser = async () => {
      const userData = await getUser(id);
      setUser(userData);
    }
    
    fetchUser();
  }, []);  
  
  return (
    <>
      <Box sx={{
        position: "relative",
        margin: -1,
      }}>
        {user.captionPhotoLink !== "" ? (
          <Box
            component={"img"}
            alt='Caption'
            height={"400px"}
            width={"100%"}
            image={`http://localhost:8080/user/${user.id}/caption-photo/download`}
          />
        ): 
        (
          <Box
            sx={{
              height: "400px",
              width: 1,
              backgroundColor: "#c5cdd6",
            }}
            
          />
        )}
        <Box
          position={"absolute"}
          top={0}
          left={0}
        >
          {(user.profilePhotoLink !== "") ? 
            <Avatar 
              src={`http://localhost:8080/user/${user.id}/profile-photo/download`} 
              alt='Profile' 
              sx={{width: "150px", height: "150px", marginTop: "325px", marginLeft: "120px"}}
            /> 
            : 
            <Avatar 
              sx={{width: "150px", height: "150px", marginTop: "325px", marginLeft: "120px"}}
            />
          }
        </Box>
        <Box 
          sx={{backgroundColor: "#2E3B55"}}
        >
          <Box 
            sx={{
              display: "flex", justifyContent: "space-around"
            }}
            >
            <Box>
              <Typography 
                sx={{
                  color: "white"
                }} 
                variant='h3'
                >
                  {user.name}
                </Typography>
              <Typography 
                variant='h4' 
                sx={{
                  color: "white", opacity: 0.4
                }}
              >
                @{user.username}
              </Typography>
              <Stack mt={1} spacing={2} direction={"row"}>
                <Typography sx={{color: "white", "&:hover": {color: "#FFC107"}}}>21,9k Takipçi</Typography>
                <Typography sx={{color: "white", "&:hover": {color: "#FFC107"}}}>218 Takip Edilen</Typography>
              </Stack>
            </Box>
            <Box sx={{marginTop: "5px"}}>
              {(user.id === authUser.id) ? 
                <Button 
                  variant='contained' 
                  color='warning' 
                  sx={{borderRadius: "20px"}}
                >
                  <Link to={"/"} style={{textDecoration: "none", color: "white"}}>Profili Düzenle</Link>
                </Button> 
                : 
                <Button 
                  color='warning' 
                  variant='contained' 
                  sx={{borderRadius: "20px"}}
                  onClick={() => {
                    
                  }}
                >
                  Takip Et
                </Button>
              }
            </Box>
          </Box>
          <Box>
            <Tabs value={0} variant='fullWidth'>
              <Tab sx={{color: "white"}} label="Makale"/>
              <Tab sx={{color: "white"}} label="Video"/>
              <Tab sx={{color: "white"}} label="Podcast"/>
            </Tabs>
          </Box>
        </Box>
      </Box>
    </>
  )
}

export default UserProfile