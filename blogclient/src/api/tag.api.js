import axios from "axios";

const token = localStorage.getItem("token");

export const getTags = async () => {
    return await axios.get("/tag")
                      .then(res => res.data)
                      .catch(err => {throw err});
}

export const getTag = async (id) => {
    return await axios.get(`/tag/${id}`)
                      .then(res => res.data)
                      .catch(err => {throw err});
}

export const createTag = async (data) => {
    axios.post("/tag", data, {headers: {Authorization: `Bearer ${token}`}})
         .catch(err => {throw err});
}