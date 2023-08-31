import axios from 'axios';
import React, { useEffect, useState } from 'react'

const PostDetail = () => {
  const [post, setPost] = useState({});
  
  useEffect(() => {
    axios.get(`/post/`)
  }, []);
  return (
    <div>PostDetail</div>
  )
}

export default PostDetail