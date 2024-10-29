import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Post } from '../models/Post';
import parse from "html-react-parser";
import moment from 'moment';

const PostDetail = () => {
    const { id } = useParams();

    const [post, setPost] = useState<Post | null>(null);

    const getPostFromApi = async () => {
        const response = await fetch(`http://localhost:8080/post/${id}`);
        const data = await response.json();
        setPost(data);
    }

    useEffect(() => {
      getPostFromApi();
    }, []);

  return (
    <div className="flex justify-center">
      {post ?
      <div className="flex flex-col justify-center items-center">
        <div className="text-center w-2/3 bg-white shadow-2xl rounded-lg mt-3 p-2 mb-3">
          {
            post.postType === "ARTICLE" ? 
            <div className="text-lg mt-5 flex flex-col items-center">
              <img className="rounded-lg" src={`https://d3mb2449r5ma1f.cloudfront.net/post_caption_photos/${post.captionPhotoLink}`} alt="" width={750} height={550}/>
              <p className="text-6xl font-bold text-black mt-5">{post.title}</p>
              <div className="text-left p-5">
                {parse(post.content)}
              </div>
            </div> 
            : 
            post.postType === "VIDEO" ? 
            <div>
              <video src={`https://d3mb2449r5ma1f.cloudfront.net/videos/${post.content}`} width={950} height={750} controls/>
              <p className="text-4xl font-bold">{post.title}</p>
            </div>
            :
            post.postType === "PODCAST" ? 
            <div>
              <audio src={`https://d3mb2449r5ma1f.cloudfront.net/podcasts/${post.content}`} controls/>
            </div>
            :
            null
          }
        </div>
        {post.comments.map((comment) => {
          return (
            <div className="w-1/3 bg-white p-3 mt-2 mb-2 rounded-lg shadow-xl">
              <p>{comment.content}</p>
              <p>{comment.user.name}</p>
              <p>{moment(comment.publishedDate).format("DD.MM.YYYY HH.mm")}</p>
            </div>
          )
        })}
        </div>
        :
        <p>Loading...</p>
      }
    </div>
  )
}

export default PostDetail