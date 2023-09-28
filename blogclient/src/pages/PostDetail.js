import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import { getPost } from '../api/post.api';

const PostDetail = () => {
  const [post, setPost] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    const fetchPost = async () => {
      const post = await getPost(id);
      setPost(post);
    }

    fetchPost();
  }, []);
  return (
    <>
      
    </>
  )
}

export default PostDetail