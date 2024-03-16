import { Divider, Typography, Button } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { getPosts } from '../api/post.api';

const Dashboard = () => {
    const { authUser } = useSelector((state) => state.auth);
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchPostsOfUser = async () => {
            const userPosts = await getPosts();
            setPosts(userPosts);
        }
    }, []);

    return (
        <>
           <Typography>Gönderiler</Typography>
           <Divider/>
        </>
    )
}

export default Dashboard