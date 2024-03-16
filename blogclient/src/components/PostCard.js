import { Box, Stack, Chip, Avatar, Card, CardContent, CardHeader, CardMedia, Typography, Rating, IconButton, Divider } from '@mui/material'
import { MoreVert } from '@mui/icons-material'
import { red } from '@mui/material/colors'
import React from 'react'
import moment from 'moment'
import { useNavigate } from "react-router-dom";
import randomHexColorCode from '../util/generateHexColorCode'

const PostCard = ({post}) => {
  const navigate = useNavigate();  
  return (
    <Card sx={{maxWidth: 345}}>
        <CardHeader
            avatar={
                (post?.user?.profilePhotoLink == null) ?
                 
                <Avatar sx={{bgcolor: red[500]}}>{post?.user?.username.charAt(0)}</Avatar> 
                : 
                <Avatar src={`http://localhost:8080/user/${post?.user.id}/profile-photo/download`}/>
            }
            action={
                <IconButton onClick={() => console.log("Hello World")}>
                    <MoreVert/>
                </IconButton>
            }
            title={`${post?.user?.name} @${post?.user?.username}`}
            subheader={`${moment(post?.publishedDate).format("DD.MM.YYYY HH:mm:ss")}`}
        />
        <CardMedia
            component={"img"}
            height={"194px"}
            src={`http://localhost:8080/post/${post?.id}/caption-photo/download`}
            alt='Caption Image'
            onClick={() => navigate(`/post/${post?.id}`)}
        />
        <CardContent>
            <Stack direction={"row"} spacing={3} marginBottom={2}>
                <Rating readOnly value={post?.averageRate} precision={0.5}/> 
                <Typography>{post?.averageRate} / 5.0</Typography>
            </Stack>
            <Typography variant='h6'>
                {post?.title}
            </Typography>
            <Typography variant='body2' color={"text.secondary"}>
                {post?.description}
            </Typography>
            <Typography variant='body2' marginTop={2}>
                {post?.comments?.length} Yorum
            </Typography>
            <Divider/>
            <Box marginTop={1}>
                <Typography>
                    Etiketler: {post?.tags.map((tag) => <Chip label={`${tag.tagName}`} sx={{backgroundColor: `${randomHexColorCode()}`}}/>)}
                </Typography>
            </Box>
        </CardContent>
    </Card>
  )
}

export default PostCard