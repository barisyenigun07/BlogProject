import React from 'react';
import  {CKEditor}  from '@ckeditor/ckeditor5-react';
import Editor from 'ckeditor5-custom-build/build/ckeditor';
import  SimpleUploadAdapter  from '@ckeditor/ckeditor5-upload/src/adapters/simpleuploadadapter';
import { Box } from '@mui/material';


const ArticleEditor = () => {
  const token = localStorage.getItem("token");
  return (
    <Box>
        <CKEditor 
              editor={ Editor }
              data={this.state.text}
              config={{
                plugins: [SimpleUploadAdapter],
                simpleUpload: {
                  uploadUrl: 'post/article/image/upload'
                },
                headers: {
                  Authorization: `Bearer ${token}`
                }
              }}
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
              }}
               
        />
        
    </Box>
  )
}

export default ArticleEditor;

