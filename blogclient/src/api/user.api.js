import axios from "axios";

const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;




export const getUser = async (id) => {
    return await axios.get(`/user/${id}`)
                      .then(res => res.data)
                      .catch(err => {throw err});
} 

export const updateUser = async (formData) => {
    await axios.put("/user", formData, {headers: {Authorization: `Bearer ${token}`, "Content-Type": "multipart/form-data"}})
                .catch(err => {throw err});
}

export const deleteUser = async () => {
    await axios.delete("/user", {headers: {Authorization: `Bearer ${token}`}})
               .catch(err => {throw err});
}

