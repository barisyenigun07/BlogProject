import React from 'react'
import { useSelector } from 'react-redux'

const UpdateProfile = () => {
  const { authUser } = useSelector((state) => state.auth);  
  return (
    <div>
        <p>{authUser.name}</p>
        <p>{authUser.username}</p>
        <p>{authUser.email}</p>
    </div>
  )
}

export default UpdateProfile