import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { userLogin } from "./authActions";
import { User } from "../models/User";

type AuthResponse = {
    token: string,
    user: User
}

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        user: {id: 0, name: '', username: '', email: ''},
        token: '',
        isLoggedIn: false
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(userLogin.pending, (state) => {
                state.isLoggedIn = false;
                state.token = '';
                state.user = {id: 0, name: '', username: '', email: ''};
            })
            .addCase(userLogin.rejected, (state) => {
                state.isLoggedIn = false;
                state.token = '';
                state.user = {id: 0, name: '', username: '', email: ''};
            })
            .addCase(userLogin.fulfilled, (state, action: PayloadAction<AuthResponse>) => {
                state.isLoggedIn = true;
                state.token = action.payload.token;
                state.user = action.payload.user;
            });

    }
});

export default authSlice.reducer;