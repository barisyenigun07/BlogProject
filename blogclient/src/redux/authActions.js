import { createAsyncThunk } from "@reduxjs/toolkit";
import { login, register } from "../api/auth.api";

export const registerUser = createAsyncThunk(
    'auth/register',
    async (data, thunkAPI) => {
        try {
            const response = await register(data);
            thunkAPI.dispatch();
            return response;
        }
        catch (err) {
           return thunkAPI.rejectWithValue(); 
        }
    }
);

export const userLogin = createAsyncThunk(
    'auth/login',
    async (data, thunkAPI) => {
        try {
            const response = await login(data);
            localStorage.setItem("token", response.token);
            return response;
        }
        catch(err) {
            return thunkAPI.rejectWithValue();
        }

    }
);