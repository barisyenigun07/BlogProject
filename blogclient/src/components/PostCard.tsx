import React from 'react'
import { Post } from '../models/Post'
import { useNavigate } from 'react-router-dom'
import moment from 'moment';

const PostCard = ({post}: {post: Post}) => {
  const navigate = useNavigate();
  //format("DD.MM.YYYY HH.mm")  
  return (
    <div className="w-[900px] bg-white rounded-xl shadow-2xl flex mt-2 cursor-pointer" style={{width: "550px"}} onClick={() => navigate(`/post/${post.id}`)}>
        <div className="flex-none">
            <img className="w-36 h-40 rounded-l-xl object-cover" src={`https://d3mb2449r5ma1f.cloudfront.net/post_caption_photos/${post.captionPhotoLink}`} alt="Caption"/>
        </div>
        <div className="flex flex-col p-3 justify-between">
            <div>
              <p className="text-2xl font-bold">{post.title}</p>
              <p>{post.description}</p>
            </div>
            <div>
              <p>Yayınlanma Tarihi: {moment(post.publishedDate).format("DD.MM.YYYY HH.mm")}</p>
              <p>Oy: {post.averageRate} / 5.0</p>
            </div>
        </div>
    </div>
  )
}

export default PostCard;