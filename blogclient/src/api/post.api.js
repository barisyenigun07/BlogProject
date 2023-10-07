import axios from "axios";

const token = localStorage.getItem("token");

export const createPost = (formData) => {
    axios.post("/post", formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
        .then(res => res.status)
        .catch(err => {throw err});
}

export const getPost = async (id) => {
    return axios.get(`/post/${id}`, {headers: {Authorization: `Bearer ${token}`}})
                .then(res => res.data)
                .catch(err => {throw err});
}

export const updatePost = (id, formData) => {
    axios.put(`/post/${id}`, formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
        .then(res => res.status)
        .catch(err => {throw err});
}

export const deletePost = (id) => {
    axios.delete(`/post/${id}`, {headers: {Authorization: `Bearer ${token}`}})
        .then(res => res.status)
        .catch(err => {throw err});
}

