import { Button, TextField } from '@mui/material';
import './App.css';
import ArticlePost from './components/ArticlePost';
import Navbar from './components/Navbar';
function App() {
  return (
    <div className="App">
      <Navbar/>
      <div>
        <TextField type='text' label="Title"/>
      </div>
      <ArticlePost/>
      
    </div>
  );
}

export default App;
