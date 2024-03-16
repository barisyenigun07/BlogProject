import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Register from './pages/Register';
import Login from './pages/Login';
import HomePage from './pages/HomePage';
import PostDetail from './pages/PostDetail';
import CreatePost from './pages/CreatePost';
import Navbar from './components/Navbar';
import UserProfile from './pages/UserProfile';
import UpdateProfile from './pages/UpdateProfile';

 
function App() {
  
  return (
    <BrowserRouter>
      <Navbar/>
      <Routes>
        <Route path='/register' element={<Register/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/' element={<HomePage/>}/>
        <Route path='/post/:id' element={<PostDetail/>}/>
        <Route path='/post/create' element={<CreatePost/>}/>
        <Route path='/user/:id' element={<UserProfile/>}/>
        <Route path='/update-profile' element={<UpdateProfile/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
