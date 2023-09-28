import { Autocomplete, Box, Button, Grid, InputLabel, MenuItem, Select, Stack, TextField, Typography } from '@mui/material';
import  {CKEditor}  from '@ckeditor/ckeditor5-react';
import Editor from 'ckeditor5-custom-build/build/ckeditor';
//import  SimpleUploadAdapter  from '@ckeditor/ckeditor5-upload/src/adapters/simpleuploadadapter';
import React, { useEffect, useState } from 'react'
import { getTags } from '../api/tag.api';
import { createPost } from '../api/post.api';
import { useNavigate } from 'react-router-dom';
import { AudioFile, VideoFile } from '@mui/icons-material';

const CreatePost = () => {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [captionPhoto, setCaptionPhoto] = useState(null);
  const [tag, setTag] = useState(null);
  const [postType, setPostType] = useState("");
  const [content, setContent] = useState(null);

  const [tags, setTags] = useState([]);

  

  useEffect(() => {
    const fetchAllTags = async () => {
      const allTags = await getTags();
      setTags(allTags);
    }
    
    fetchAllTags();
  }, []);

  const handleSubmit = async (e) => {
    try {
      e.preventDefault();
      
      const formData = new FormData();
      
      formData.append("title", title);
      formData.append("description", description);
      formData.append("captionPhoto", captionPhoto);
      formData.append("tag", tag);
      formData.append("postType", postType);
      formData.append("content", content);
      
      await createPost(formData);
      navigate("/");
    }
    catch(err) {
      alert(err);
    }
  }

  return (
    <>
      <form onSubmit={handleSubmit}>
        <Stack spacing={2}>
          <TextField 
            type='text' 
            variant='standard' 
            label="Başlık" 
            fullWidth 
            onChange={(e) => setTitle(e.target.value)}
          />
          <TextField 
            type='text' 
            variant='outlined' 
            label="Açıklama"
            multiline 
            fullWidth 
            onChange={(e) => setDescription(e.target.value)}
          />
          <Button
            variant='contained'
            component="label"
          >
            Kapak Fotoğrafı Ekle
            <input
              type='file'
              hidden
              accept='image/*'
              onChange={(e) => setCaptionPhoto(e.target.files[0])}
            />
          </Button>
          <Autocomplete
            disablePortal
            options={tags}
            sx={{width: 300}}
            renderInput={(params) => <TextField {...params} label="Etiket"/>}
            onChange={(e) => setTag(e.target.value)}
          />
          <InputLabel id="post-type-select-label">Gönderi Türü</InputLabel>
          <Select
            labelId='post-type-select-label'
            label="Gönderi Türü"
            value={postType}
            onChange={(e) => setPostType(e.target.value)}
            fullWidth
          >
            <MenuItem value="ARTICLE">Makale</MenuItem>
            <MenuItem value="VIDEO">Video</MenuItem>
            <MenuItem value="PODCAST">Podcast</MenuItem>
          </Select>
          <Box>
          {
            (postType === "ARTICLE") ? 
              
              <CKEditor 
              editor={ Editor }
              data={content}
              onReady={
                (editor) => {
                  console.log("Editor is ready to use!",editor);
                }
              }
              onChange={(event, editor) => {
                const data = editor.getData();
                setContent(data);
              }}
              
              />
              
              :
              (postType === "VIDEO") ?
              <Button
                variant='contained'
                component="label"
                color='error'
                startIcon={<VideoFile/>}
              >
                Video Ekle
                <input
                  type='file'
                  hidden
                  accept='video/*'
                  onChange={(e) => setContent(e.target.files[0])}
                />
              </Button>
              :
              (postType === "PODCAST") ?
              <Button
                variant='contained'
                component="label"
                color='error'
                startIcon={<AudioFile/>}
              >
                Podcast Ekle
                <input
                  type='file'
                  hidden
                  accept='audio/*'
                  onChange={(e) => setContent(e.target.files[0])}
                />
              </Button>
              :
              null
          }
          </Box>
          {(content == null || content.length === 0) ? 
            <Button 
              disabled
              variant='contained'
              color='primary'
            >
              Yayınla
            </Button> 
            : 
            <Button 
              variant='contained' 
              color='primary' 
              type='submit'
            >
              Yayınla
            </Button>
          }
        </Stack>
      </form>
    </>
  )
}

export default CreatePost