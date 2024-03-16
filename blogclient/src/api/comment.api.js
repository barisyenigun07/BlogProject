import axios from "axios";

const token = localStorage.getItem("token");

export const createComment = (data) => {
    axios.post("/comment", data, {headers: {Authorization: `Bearer ${token}`}})
         .then(res => res.status)
         .catch(err => {throw err});
}

export const replyComment = (id, data) => {
    axios.post(`/comment/${id}/reply`, data, {headers: {Authorization: `Bearer ${token}`}})
        .then(res => res.status)
        .catch(err => {throw err});
}

export const deleteComment = (id) => {
    axios.delete(`/comment/${id}`, {headers: {Authorization: `Bearer ${token}`}})
         .then(res => res.status)
         .catch(err => {throw err});
}