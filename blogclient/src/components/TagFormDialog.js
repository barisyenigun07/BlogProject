import { Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from '@mui/material';
import React, { useState } from 'react'

const TagFormDialog = ({open, onClose, onTagAdd}) => {
  const [tagName, setTagName] = useState("");
  
  const handleTagAdd = () => {
    if (tagName.trim() !== "") {
        onTagAdd({tagName: tagName});
        onClose();
    }
  }
  return (
    <Dialog open={open} onClose={onClose}>
        <DialogTitle sx={{backgroundColor: "#2E3B55"}} color={"white"}>Etiket Ekle</DialogTitle>
        <DialogContent>
          <Box sx={{display: "flex", justifyContent: "center"}}>
            <TextField
                placeholder='Etiket ismi girin...'
                type='text'
                fullWidth
                variant='standard'
                value={tagName}
                onChange={(e) => setTagName(e.target.value)}
            />
          </Box>
        </DialogContent>
        <DialogActions sx={{backgroundColor: "#2E3B55"}}>
            <Button onClick={onClose} color='warning'>Çık</Button>
            <Button onClick={handleTagAdd} color='warning'>Kaydet</Button>
        </DialogActions>
    </Dialog>
  )
}

export default TagFormDialog