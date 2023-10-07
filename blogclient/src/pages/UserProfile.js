import { Avatar, Box, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux';
import { useParams } from 'react-router-dom'
import { getUser } from '../api/user.api';

const UserProfile = () => {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  
  const { authUser } = useSelector((state) => state.auth);

  useEffect(() => {
    const fetchUser = async () => {
      const userData = await getUser(id);
      setUser(userData);
    }

    fetchUser();
  }, [id]);  
  
  return (
    <>
      
    </>
  )
}

export default UserProfile