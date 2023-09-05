import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Register from './pages/Register';
import Login from './pages/Login';
import HomePage from './pages/HomePage';
import PostDetail from './pages/PostDetail';
import CreatePost from './pages/CreatePost';


 
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<HomePage/>}/>
        <Route path='/register' element={<Register/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/post/:id' element={<PostDetail/>}/>
        <Route path='/post/create' element={<CreatePost/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
