import axios from "axios";

export const register = async (data) => {
    axios.post("/register", data)
         .catch(err => {throw err});
}

export const login = async (data) => {
    axios.post("/login", data)
                .then(res => localStorage.setItem("token", res.data.token))
                .catch(err => {throw err});
}

