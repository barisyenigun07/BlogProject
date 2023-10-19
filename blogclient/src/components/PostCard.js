import { Avatar, Card, CardContent, CardHeader, CardMedia, Typography } from '@mui/material'
import { red } from '@mui/material/colors'
import React from 'react'

const PostCard = ({post}) => {
  return (
    <Card sx={{maxWidth: 345}}>
        <CardHeader
            avatar={
                (post?.user.profilePhotoUrl == null) ? 
                <Avatar sx={{bgcolor: red[500]}}>{post?.user.username.charAt(0)}</Avatar> 
                : 
                <Avatar src={`http://localhost:8080/user/${post?.user.id}/profile-photo/download`}/>
            }
            title={`${post?.user.name}`}
            subheader={`${post?.publishedDate}`}
        />
        <CardMedia
            component={"img"}
            height={"194px"}
            image={`http://localhost:8080/post/${post?.id}/caption-photo/download`}
            alt='Caption Image'
        />
        <CardContent>
            <Typography variant='h6'>
                {post?.title}
            </Typography>
            <Typography variant='body2' color={"text.secondary"}>
                {post?.description}
            </Typography>
        </CardContent>
    </Card>
  )
}

export default PostCard