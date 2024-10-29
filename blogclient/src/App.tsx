
import './App.css'
import TopBar from './components/TopBar';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Register from './pages/Register';
import Home from './pages/Home';
import PostDetail from './pages/PostDetail';
import Login from './pages/Login';

function App() {
  //from-indigo-400 from-10% via-blue-900 via-40% to-purple-700
  return (
    <div className="min-h-screen bg-gradient-to-r from-indigo-500 via-blue-500 to-cyan-500 flex flex-col">
      <TopBar/>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/register' element={<Register/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/post/:id' element={<PostDetail/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
