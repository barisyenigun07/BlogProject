import { Avatar, Box, Button, Stack, Tab, Tabs, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux';
import { Link, useParams } from 'react-router-dom'
import { getUser } from '../api/user.api';
import TabPanel from '../components/TabPanel';
import PostCard from '../components/PostCard';
import { getPostsByUser } from '../api/post.api';

const UserProfile = () => {
  const { id } = useParams();
  const [user, setUser] = useState({id: 0, name: "", username: "", email: "", profilePhotoLink: null, captionPhotoLink: null});
  const [posts, setPosts] = useState([]);
  const [tabValue, setTabValue] = useState(0);
  
  const { authUser } = useSelector((state) => state.auth);

  const handleTabs = (e, value) => {
    setTabValue(value);
  }

  

  useEffect(() => {
    const fetchUser = async () => {
      const userData = await getUser(id);
      setUser(userData);
    }

    fetchUser();
  }, []);
  
  useEffect(() => {
    const getPosts = async () => {
      const postsApi = await getPostsByUser(id);
      setPosts(postsApi);
    }

    getPosts();
  }, []);
  
  return (
    <>
      <Box sx={{position: "relative", margin: -1}}>
        {user.captionPhotoLink != null ? (
          <img src={`http://localhost:8080/user/${user.id}/caption-photo/download`} alt='Caption' width={"100%"} height={"400px"}/>
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
          {(user.profilePhotoLink != null) ? 
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
        <Box sx={{backgroundColor: "#2E3B55"}}>
          <Box sx={{display: "flex", justifyContent: "space-around"}}>
            <Box>
              <Typography sx={{color: "white"}} variant='h3'>
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
                <Typography sx={{color: "white", "&:hover": {color: "#DC700C"}}}>21,9k Takipçi</Typography>
                <Typography sx={{color: "white", "&:hover": {color: "#DC700C"}}}>218 Takip Edilen</Typography>
              </Stack>
            </Box>
            <Box sx={{marginTop: "5px"}}>
              {(authUser && user.id === authUser.id) ? 
                <Button 
                  variant='contained' 
                  color='warning' 
                  sx={{borderRadius: "20px"}}
                >
                  <Link to={"/update-profile"} style={{textDecoration: "none", color: "white"}}>Profili Düzenle</Link>
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
            <Tabs sx={{marginTop: 3}} value={tabValue} variant="fullWidth" onChange={handleTabs} centered>
              <Tab sx={{color: "white"}} label="Makale"/>
              <Tab sx={{color: "white"}} label="Video"/>
              <Tab sx={{color: "white"}} label="Podcast"/>
            </Tabs>
            <TabPanel value={tabValue} index={0}>
              <Box sx={{display: "flex", justifyContent: "center", backgroundColor: "white"}}>
                <Stack spacing={5} mt={5}>
                {posts.filter((post) => post?.postType === "ARTICLE").map((article) => {
                  return (
                    <PostCard post={article}/>
                  )
                })}
                </Stack>
              </Box>
            </TabPanel>
            <TabPanel value={tabValue} index={1}>
              <Box sx={{display: "flex", justifyContent: "center", backgroundColor: "white"}}>
                <Stack spacing={5} mt={5}>
                  {posts.filter((post) => post?.postType === "VIDEO").map((video) => {
                    return (
                      <PostCard post={video}/>
                    )
                  })}
                </Stack>
              </Box>
            </TabPanel>
            <TabPanel value={tabValue} index={2}>
              <Box sx={{display: "flex", justifyContent: "center", backgroundColor: "white"}}>
                <Stack spacing={5} mt={5}>
                {posts.filter((post) => post?.postType === "PODCAST").map((podcast) => {
                  return (
                    <PostCard post={podcast}/>
                  )
                })}
                </Stack>
              </Box>
            </TabPanel>
          </Box>
          
        </Box>
      </Box>
    </>
  )
}

export default UserProfile