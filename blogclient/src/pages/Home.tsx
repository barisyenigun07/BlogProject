import React, { useEffect, useState } from 'react'
import { Post } from '../models/Post'
import PostCard from '../components/PostCard'

const Home = () => {
    const [posts, setPosts] = useState<Post[]>([]);

    const getPostsFromApi = async () => {
        const response = await fetch("http://localhost:8080/post");
        const data = await response.json();
        setPosts(data);
    }

    useEffect(() => {
        getPostsFromApi();
    }, []);
  return (
    <div className="flex flex-col justify-center items-center p-2">
        {posts.map((post, index) => {
            return (
                <PostCard key={index} post={post}/>
            )
        })}
    </div>
  )
}

export default Home