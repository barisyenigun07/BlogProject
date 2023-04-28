import axios from "axios";

const token = sessionStorage.getItem("token");

export const createArticle = async (formData) => {
    axios.post("/post/article", formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
         .catch(err => {throw err});
}

export const createVideo = async (formData) => {
    axios.post("/post/video", formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
         .catch(err => {throw err});
}

export const createPodcast = async (formData) => {
    axios.post("/post/podcast", formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
         .catch(err => {throw err});
}

export const getPost = async (id) => {
    return axios.get(`/post/${id}`, {headers: {Authorization: `Bearer ${token}`}})
                .then(res => res.data)
                .catch(err => {throw err});
}

export const deletePost = async (id) => {
    axios.delete(`/post/${id}`, {headers: {Authorization: `Bearer ${token}`}})
         .catch(err => {throw err});
}

