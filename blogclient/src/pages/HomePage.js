import React, { useEffect } from 'react'
import { useSelector } from 'react-redux'

const HomePage = () => {
  const { authUser } = useSelector((state) => state.auth);

  useEffect(() => {
    console.log(authUser);
  }, []);
  return (
    <>
      
    </>
    
  )
}

export default HomePage



