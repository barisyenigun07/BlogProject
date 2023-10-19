import { Autocomplete, Box, Button, CircularProgress, Divider, IconButton, InputLabel, MenuItem, Select, Stack, TextField, Tooltip, Typography } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import CloseIcon from '@mui/icons-material/Close';
import HeadphonesIcon from '@mui/icons-material/Headphones';
import VideocamIcon from '@mui/icons-material/Videocam';
import  {CKEditor}  from '@ckeditor/ckeditor5-react';
import Editor from 'ckeditor5-custom-build/build/ckeditor';
//import  SimpleUploadAdapter  from '@ckeditor/ckeditor5-upload/src/adapters/simpleuploadadapter';
import React, { useEffect, useState } from 'react'
import { createTag, getTags } from '../api/tag.api';
import { createPost } from '../api/post.api';
import { useNavigate } from 'react-router-dom';
import TagFormDialog from '../components/TagFormDialog';

const CreatePost = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [isTagFormDialogOpen, setTagFormDialogOpen] = useState(false);

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [captionPhoto, setCaptionPhoto] = useState(null);
  const [tags, setTags] = useState([]);
  const [postType, setPostType] = useState("");
  const [content, setContent] = useState(null);

  const [captionPhotoPreview, setCaptionPhotoPreview] = useState(null);
  const [fileContentPreview, setFileContentPreview] = useState(null);

  const handleDeleteCaptionPhoto = () => {
    setCaptionPhotoPreview(null);
    setCaptionPhoto(null);
  }

  const handleDeleteFileContent = () => {
    setFileContentPreview(null);
    setContent(null);
  }
  
  const [allTags, setAllTags] = useState([]);

  const handleOpenTagFormDialog = () => {
    setTagFormDialogOpen(true);
  }

  const handleCloseTagFormDialog = () => {
    setTagFormDialogOpen(false);
  }

  const handleAddTag = async (data) => {
    try {
      const response = await createTag(data);
      setAllTags([...allTags, response]);
      handleCloseTagFormDialog();
    }
    catch (err) {
      
    }
  }

  useEffect(() => {
    const fetchAllTags = async () => {
      const allTags = await getTags();
      setAllTags(allTags);
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
      formData.append("tags", tags);
      formData.append("postType", postType);
      formData.append("content", content);
      createPost(formData);
      
      setTimeout(() => {
        navigate("/");
        window.location.reload();
      }, 1000)
    }
    catch(err) {
      setLoading(false);
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
              onChange={(e) => {
                setCaptionPhotoPreview(URL.createObjectURL(e.target.files[0]));
                setCaptionPhoto(e.target.files[0]);
              }}
            />
          </Button>
          <Box sx={{display: "flex", justifyContent: "center"}}>
            {captionPhotoPreview ? 
              <Stack spacing={2}>
                <Box sx={{display: "flex", justifyContent: "center"}}>
                  <img src={captionPhotoPreview} alt='previewedImage' width={"950px"} height={"550px"}/>
                </Box>
                <Box sx={{display: "flex", justifyContent: "center"}}>
                  <Button 
                    variant='contained' 
                    color='error'
                    startIcon={<CloseIcon/>} 
                    onClick={() => {
                      handleDeleteCaptionPhoto();
                    }}
                  >
                    Kapak Fotoğrafını Kaldır
                  </Button>
                </Box>
              </Stack> 
              : null
              }
          </Box>
          <Stack direction={"row"} spacing={2}>
            <Autocomplete
              disablePortal
              multiple
              placeholder='Etiket ara...'
              fullWidth
              options={allTags}
              getOptionLabel={(tag) => tag.tagName}
              renderInput={(params) => <TextField {...params} label="Etiket"/>}
              onChange={(e, values) => {
                setTags(values);
              }}
            />
            <Tooltip title="Yeni etiket ekle" onClick={handleOpenTagFormDialog}>
              <IconButton color='warning'>
                <AddIcon/>
              </IconButton>
            </Tooltip>
            <TagFormDialog
              open={isTagFormDialogOpen}
              onClose={handleCloseTagFormDialog}
              onTagAdd={handleAddTag}
            />
          </Stack>
          <InputLabel id="post-type-select-label">Gönderi Türü</InputLabel>
          <Select
            labelId='post-type-select-label'
            id='post-type-select'
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
                fullWidth
                startIcon={<VideocamIcon/>}
              >
                Video Ekle
                <input
                  type='file'
                  hidden
                  accept='video/*'
                  onChange={(e) => {
                    setFileContentPreview(URL.createObjectURL(e.target.files[0]));
                    setContent(e.target.files[0])
                  }}
                />
              </Button>
              :
              (postType === "PODCAST") ?
              <Button
                variant='contained'
                component="label"
                color='error'
                fullWidth
                startIcon={<HeadphonesIcon/>}
              >
                Podcast Ekle
                <input
                  type='file'
                  hidden
                  accept='audio/*'
                  onChange={(e) => {
                    setFileContentPreview(URL.createObjectURL(e.target.files[0]));
                    setContent(e.target.files[0])
                  }}
                />
              </Button>
              :
              null
          }
          </Box>
          <Box>
            {fileContentPreview && postType === "VIDEO" ? 
              <Stack spacing={2}>
                <Box sx={{display: "flex", justifyContent: "center"}}>
                  <video src={fileContentPreview} controls width={"950px"} height={"550px"}/>
                </Box>
                <Box sx={{display: "flex", justifyContent: "center"}}>
                  <Button 
                    variant='contained' 
                    color='error'
                    startIcon={<CloseIcon/>}
                    onClick={() => {
                      handleDeleteFileContent();
                  }}>
                    Videoyu Kaldır
                  </Button>
                </Box>
              </Stack>
              :
              fileContentPreview && postType === "PODCAST" ?
              <Stack spacing={2}>
                <Box sx={{display: "flex", justifyContent: "center"}}>
                  <audio src={fileContentPreview} controls/>
                </Box>
                <Box sx={{display: "flex", justifyContent: "center"}}>
                  <Button
                    variant='contained'
                    color='error'
                    startIcon={<CloseIcon/>}
                    onClick={() => {
                      handleDeleteFileContent();
                    }}
                  >
                    Podcasti Kaldır
                  </Button>
                </Box>
              </Stack>
              :
              null
            }
          </Box>
          <Box sx={{display: "flex", justifyContent: "center"}}>
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
              startIcon={loading ? <CircularProgress sx={{color: "white"}}/> : null}
            >
              Yayınla
            </Button>
          }
          </Box>
        </Stack>
      </form>
      </Box>
    </>
  )
}

export default CreatePost