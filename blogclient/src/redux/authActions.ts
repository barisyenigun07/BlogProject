import { createAsyncThunk } from "@reduxjs/toolkit";

export const userLogin = createAsyncThunk(
    'auth/login',
    async ({username, password}, {rejectWithValue}) => {
        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                body: JSON.stringify({username: username, password: password})
            });

            const data = await response.json();
        }
        catch (err) {
            rejectWithValue("");
        }
    }
)