import React, { useEffect, useState } from 'react';
import { Box, Stack, Divider, Typography } from '@mui/material';
import { getPosts } from '../api/post.api';
import PostCard from '../components/PostCard';
 
const HomePage = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    const getPostsFromApi = async () => {
      const postsFromApi = await getPosts();
      setPosts(postsFromApi);
    }

    getPostsFromApi();
  }, []);
  return (
    <>
      <Typography variant="h3">Feed Postları</Typography>
      <Divider/>
      <Box sx={{display: "flex", justifyContent: "center", marginTop: 3}}>
        <Stack spacing={2}>
          {posts.map((post) => {
            return (
              <div key={post.id}>
                <PostCard post={post}/>
                <Divider/>
              </div>
            )
          })}
        </Stack>
      </Box>
    </>
    
  )
}

export default HomePage



