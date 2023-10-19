import { Divider, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'

const Dashboard = () => {
    const { authUser } = useSelector((state) => state.auth);
    const [posts, setPosts] = useState([]);

    useEffect(() => {

    }, []);

    return (
        <>
           <Typography>Gönderiler</Typography>
           <Divider/>
            
        </>
    )
}

export default Dashboard