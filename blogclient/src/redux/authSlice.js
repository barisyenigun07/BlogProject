import { createSlice } from "@reduxjs/toolkit";
import { registerUser, userLogin } from "./authActions";



const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        token,
        isLoggedIn: false,
        loading: true,
        authUser: null,
    },
    extraReducers: {
        [registerUser.fulfilled]: (state, action) => {
            state.isLoggedIn = false;
        },
        [registerUser.rejected]: (state, action) => {
            state.isLoggedIn = false;
        },
        [userLogin.fulfilled]: (state, action) => {
            state.isLoggedIn = true;
            state.authUser = action.payload.user;
        },
        [userLogin.rejected]: (state, action) => {
            state.isLoggedIn = false;
            state.authUser = null;
        }
    }
});


export const authActions = authSlice.actions;
export default authSlice;

