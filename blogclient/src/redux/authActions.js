import { createAsyncThunk } from "@reduxjs/toolkit";
import { login, register } from "../api/auth.api";
import { messageActions } from "./messageSlice";


export const registerUser = createAsyncThunk(
    'auth/register',
    async (data, thunkAPI) => {
        try {
            await register(data);
            thunkAPI.dispatch(messageActions.setMessage("Kayıt olma işlemi başarılı!"));
        }
        catch (err) {
           thunkAPI.dispatch(messageActions.setMessage("Kayıt olma işlemi başarısız!")); 
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
            thunkAPI.dispatch(messageActions.setMessage("Giriş yapma işlemi başarılı!"));
            return response;
        }
        catch(err) {
            thunkAPI.dispatch(messageActions.setMessage("Giriş yapma işlemi başarısız!"));
            return thunkAPI.rejectWithValue();
        }

    }
);