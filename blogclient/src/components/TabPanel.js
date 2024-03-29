import React from 'react'
import PropTypes from 'prop-types';
import { Box, Typography } from '@mui/material'

const TabPanel = (props) => {
    const { children, value, index, ...other } = props;
  return (
    <div
        role="tabpanel"
        hidden={value !== index}
        id={`full-width-tabpanel-${index}`}
        {...other}
    >
        {value === index && (
            <Box>
                <Typography>
                    {children}
                </Typography>
            </Box>
        )}
    </div>
  )
}

TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.number.isRequired,
    value: PropTypes.number.isRequired,
};

export default TabPanel