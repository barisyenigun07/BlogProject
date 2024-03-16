import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import { getPost } from '../api/post.api';
import { Box, Typography, Divider, Chip } from "@mui/material";
import moment from 'moment';
import parse from "html-react-parser";
import randomHexColorCode from '../util/generateHexColorCode';

const PostDetail = () => {
  const [post, setPost] = useState({});
  const { id } = useParams();

  useEffect(() => {
    const fetchPost = async () => {
      const post = await getPost(id);
      setPost(post);
    }

    fetchPost();
  }, [id]);
  return (
    <>
      {post.postType === "ARTICLE" ? 
        <Box sx={{display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", pt: 3, pb: 3, boxShadow: 12}}>
          <img style={{borderRadius: "5px"}} src={`http://localhost:8080/post/${post?.id}/caption-photo/download`} alt='Post Caption' width={750} height={550}/>
          <Typography mt={2} variant="h2" style={{fontWeight: "inherit"}}>{post?.title}</Typography>
          <Divider style={{width: "100%"}}/>
          <Typography>Yayınlayan: <Link style={{textDecoration: "none", color: "black"}} to={`/user/${post?.user?.id}`}>{post.user.name} @{post?.user?.username}</Link> | Yayınlandığı Tarih: {moment(post?.publishedDate).format("DD.MM.YYYY HH:mm:ss")}</Typography>
          <Divider style={{width: "100%"}}/>
          {parse(post?.content)}
          {post.tags.map(tag => {
            return(
              <Chip label={`${tag.tagName}`} sx={{backgroundColor: `${randomHexColorCode()}`}}/>
            );
          })}
         
        </Box>
      :
      post.postType === "VIDEO" ? 
        <Box sx={{display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", pt: 3, pb: 3, boxShadow: 12}}>
          <video src={`http://localhost:8080/post/${post?.id}/content/download`} poster={`http://localhost:8080/post/${post?.id}/caption-photo/download`} alt="Content Video" controls>
            Your browser does not support this tag.
          </video>
        </Box>
      :
      post.postType === "PODCAST" ?
        <div style={{display: "flex", justifyContent: "center", flexDirection: "column", alignItems: "center"}}>
          <img src={`http://localhost:8080/post/${post?.id}/caption-photo/download`} alt='Post Caption' width={750} height={550}/>
          <audio style={{marginTop: "2.5rem"}} src={`http://localhost:8080/post/${post?.id}/content/download`} controls/>
        </div>
      :
      null
      }
    </>
  )
}

export default PostDetail