import React, { useEffect, useState } from 'react';
import {getMemeImage, getWebImage} from "../../api/ImagesApi";

const MemeImage = ({imageId, altText, cssClass}) => {
    const [imageData, setData] = useState('');
    let finalAltText = altText !== undefined ? altText : imageId;
    let finalClassName = cssClass !== undefined ? cssClass : "";
    const getData = async () => {
        getMemeImage(imageId).then(imageData=>{
            setData(imageData);
            });
    }

    useEffect(() => {
        getData();
    }, []);
    return (

        <>
            {imageData !== undefined ? <img src={imageData} alt={finalAltText} className={finalClassName} />: ''}
        </>
    )
}
export default MemeImage;
