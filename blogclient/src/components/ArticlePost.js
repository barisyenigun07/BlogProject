import React, {Component} from 'react';
import  {CKEditor}  from '@ckeditor/ckeditor5-react';
import Editor from 'ckeditor5-custom-build/build/ckeditor';
import { Box, Button } from '@mui/material';
import axios from 'axios';


class ArticlePost extends Component {
    state = {
      title: "",
      text: "",
      tag: null,
    }

    onClickPost = (event) => {
        event.preventDefault();
        const {title,text,tag} = this.state;
        const {authToken} = localStorage.getItem("token");
        const body = {
          title: title,
          text: text,
          tag: tag
        };
        axios.post("/post", body, {headers:{Authorization: `Bearer ${authToken}`}});
    }
      
    render() {
        return (
          <Box>
            <CKEditor 
              editor={ Editor }
              
              data={this.state.text}
              onReady={
                (editor) => {
                  console.log("Editor is ready to use!",editor);
                }
              }
              onChange={(event, editor) => {
                const data = editor.getData();
                this.setState({
                  text:data
                });
              }} />
              <div>
                {this.state.text}
              </div>
              <div>
                <Button variant='contained' onClick={this.onClickPost}>Post</Button>
              </div>
            
          </Box>
        )
      }
  }

export default ArticlePost;


/*<CKEditor 
              editor={ Editor }
              
              data={this.state.text}
              onReady={
                (editor) => {
                  console.log("Editor is ready to use!",editor);
                }
              }
              onChange={(event, editor) => {
                const data = editor.getData();
                this.setState({
                  text:data
                });
              }} />*/