import { createSlice } from "@reduxjs/toolkit";
import { registerUser, userLogin } from "./authActions";



const authSlice = createSlice({
    name: 'auth',
    initialState: {
        registerSuccess: false,
        isLoggedIn: false,
        loading: false,
        authUser: null,
    },
    reducers: {
        logout: (state) => {
            localStorage.removeItem("token");
            state.authUser = null;
            state.isLoggedIn = false;
            state.loading = false;
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(registerUser.pending, (state) => {
                state.registerSuccess = false;
                state.loading = true;
            })
            .addCase(registerUser.fulfilled, (state) => {
                state.registerSuccess = true;
                state.loading = false;
            })
            .addCase(registerUser.rejected, (state) => {
                state.registerSuccess = false;
                state.loading = false;
            })
            .addCase(userLogin.pending, (state) => {
                state.isLoggedIn = false;
                state.loading = true;
            })
            .addCase(userLogin.fulfilled, (state, action) => {
                state.isLoggedIn = true;
                state.loading = false;
                state.authUser = action.payload.user;
            })
            .addCase(userLogin.rejected, (state) => {
                state.isLoggedIn = false;
                state.loading = false;
            })
    }
});


export const authActions = authSlice.actions;
export default authSlice;

