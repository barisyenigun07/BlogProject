import React, {Component} from 'react';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import  Editor from '@ckeditor/ckeditor5-build-classic';
import { Button } from '@mui/material';



class ArticlePost extends Component {
  state = {
    title: "",
    tag: null,
    text: ""
  }

  render() {
    
    return (
      <div>
        <div>
          <CKEditor
            editor={ Editor }
            data=""
            onReady={ editor => {
              console.log("Editor is ready to use!",editor);
            }}
            onChange={ (event, editor ) => {
              const data = editor.getData();
              this.setState(
                {text: data}
              )
            }}
            />
        </div>
        
        <div>
          {this.state.text}
        </div>
        <div>
          <Button type='button' variant='contained'>Post</Button>
        </div>
        
      </div>
    )
  }
}

export default ArticlePost;

