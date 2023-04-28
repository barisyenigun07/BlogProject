import axios from "axios";

export const register = async (data) => {
    axios.post("/register", data)
         .catch(err => {throw err});
}

export const login = async (data) => {
    return axios.post("/login", data)
                .then(res => res.data)
                .catch(err => {throw err});
}

