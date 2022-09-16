
export function getWebImage(id){
    const url = `/api/file/${id}`;
    console.log(url);
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'GET'
    } )
        .then((response) => response.blob())
        .then((image) => {
            let blobUrl = URL.createObjectURL(image);
            console.log(blobUrl);
            return blobUrl;
        });
}
export function getMemeImage(id){
    const url = `/api/meme/${id}`;
    console.log(url);
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'GET'
    } )
        .then((response) => response.blob())
        .then((image) => {
            let blobUrl = URL.createObjectURL(image);
            console.log(blobUrl);
            return blobUrl;
        });
}
export function getWebImages(){
    const url = `/api/file`;
    console.log(url);
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'GET'
    } ).then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                console.error(`Error: ${response.status}`);
            }
        }).catch(e => console.error(`Error: ${JSON.stringify(e)}`));
}
