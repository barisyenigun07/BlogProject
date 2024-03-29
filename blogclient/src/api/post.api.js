import axios from "axios";

const token = localStorage.getItem("token");

export const createPost = async (formData) => {
    await axios.post("/post", formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
        .then(res => res.status)
        .catch(err => {throw err});
}

export const getPost = async (id) => {
    return await axios.get(`/post/${id}`)
                .then(res => res.data)
                .catch(err => {throw err});
}

export const getPosts = async () => {
    return await axios.get("/post")
                        .then(res => res.data)
                        .catch(err => {throw err});
}

export const getPostsByUser = async (userId) => {
    return await axios.get(`/post/user/${userId}`)
                      .then(res => res.data)
                      .catch(err => {throw err});
}

export const updatePost = async (id, formData) => {
     await axios.put(`/post/${id}`, formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
        .then(res => res.status)
        .catch(err => {throw err});
}

export const deletePost = async (id) => {
    await axios.delete(`/post/${id}`, {headers: {Authorization: `Bearer ${token}`}})
        .then(res => res.status)
        .catch(err => {throw err});
}

