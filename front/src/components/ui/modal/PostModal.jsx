import React from 'react';
import cl from './PostModal.module.css'

const PostModal = ({children, visible, setVisible}) => {
    const classes = [cl.postModal]
    if (visible) {
        classes.push(cl.active)
    }

    return (
        <div className={classes.join(' ')} onClick={() => setVisible(false)}>
            <div className={cl.postModalContent} onClick={e => e.stopPropagation()}>
                {children}
            </div>
        </div>
    );
};

export default PostModal;