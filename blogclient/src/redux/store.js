import { combineReducers, configureStore } from "@reduxjs/toolkit";
import storage from 'redux-persist/lib/storage';
import { persistReducer, persistStore } from 'redux-persist';
import thunk from "redux-thunk";
import authSlice from "./authSlice";
import messageSlice from "./messageSlice";

const persistConfig = {
    key: 'root',
    storage
}

const reducer = combineReducers({
    auth: authSlice.reducer,
    message: messageSlice.reducer,
})

const persistedReducer = persistReducer(persistConfig, reducer);

const store = configureStore({
    reducer: persistedReducer,
    devTools: true,
    middleware: [thunk]
});

export const persistor = persistStore(store);
export default store;


