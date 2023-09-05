import axios from "axios";

const token = localStorage.getItem("token");

export const createRate = (data, postId) => {
    axios.post(`/rate?post_id=${postId}`, data, {headers: {Authorization: `Bearer ${token}`}})
         .catch(err => {throw err});
}

export const getRate = () => {

}

