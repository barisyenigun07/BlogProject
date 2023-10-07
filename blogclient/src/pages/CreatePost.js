import { Autocomplete, Box, Button, Chip, CircularProgress, Divider, IconButton, InputLabel, MenuItem, Select, Stack, TextField, Tooltip, Typography } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
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
  const [loading, setLoading] = useState(false);

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
      setLoading(true);
      const formData = new FormData();
      
      formData.append("title", title);
      formData.append("description", description);
      formData.append("captionPhoto", captionPhoto);
      formData.append("tag", tag);
      formData.append("postType", postType);
      formData.append("content", content);
      createPost(formData);
      
      setTimeout(() => {
        navigate("/");
        window.location.reload();
      }, 1000)
    }
    catch(err) {
      alert(err);
    }
  }

  return (
    <>
    <Typography variant='h3'>
      Gönderi Yayınla
    </Typography>
    <Divider></Divider>
    <Box m={5}>
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
            color='warning'
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
          {captionPhoto ? <img src={URL.createObjectURL(captionPhoto)} alt='previewedImage' width={"950px"} height={"550px"}/> : null}
          <Stack direction={"row"} spacing={2}>
            <Autocomplete
              disablePortal
              options={tags}
              sx={{width: 300}}
              renderInput={(params) => <TextField {...params} label="Etiket"/>}
              onChange={(e) => setTag(e.target.value)}
            />
            <Tooltip title="Etiket ekle">
              <IconButton color='warning'>
                <AddIcon/>
              </IconButton>
            </Tooltip>
            {tag ? <Chip label={tag.tagName} onDelete={() => setTag(null)}/> : null}
          </Stack>
          <InputLabel id="post-type-select-label">Gönderi Türü</InputLabel>
          <Select
            labelId='post-type-select-label'
            id='post-type-select'
            label="Gönderi Türü"
            value={postType}
            onChange={(e) => setPostType(e.target.value)}
            fullWidth
          >
            <MenuItem value="">-</MenuItem>
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
          {content && postType === "VIDEO" ?
            <video src={URL.createObjectURL(content)} controls/>
            :
            content && postType === "PODCAST" ?
            <audio src={URL.createObjectURL(content)} controls/>
            :
            null
          }
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
              color='warning' 
              type='submit'
              startIcon={loading ? <CircularProgress/> : null}
            >
              Yayınla
            </Button>
          }
        </Stack>
      </form>
      </Box>
    </>
  )
}

export default CreatePost