import ApiFetch from "./ApiContext";

export function loginUser(user){
    const url = `/api/login`;
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'POST',
        headers: {  'Content-Type': 'application/json',
                    'x-csrf-token': window.CSRF_TOKEN_HEADER},
        body: JSON.stringify(user)
    } ).then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            console.error(`Error: ${response.status}`);
        }
    }).catch(e => console.error(`Error: ${JSON.stringify(e)}`));
}
export function getUser(){
    const url = `/api/user/current`;
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'GET',
        headers: {  'Content-Type': 'application/json',
            'x-csrf-token': window.CSRF_TOKEN_HEADER}
    } ).then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            console.error(`Error: ${response.status}`);
        }
    }).catch(e => console.error(`Error: ${JSON.stringify(e)}`));
}
export function getUserDetail(id){
    const url = `/api/user/${id}`;
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'GET',
        headers: {  'Content-Type': 'application/json',
            'x-csrf-token': window.CSRF_TOKEN_HEADER}
    } ).then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            console.error(`Error: ${response.status}`);
        }
    }).catch(e => console.error(`Error: ${JSON.stringify(e)}`));
}
export function saveUserDetail(user){
    const url = `/api/user`;
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'PUT',
        headers: {  'Content-Type': 'application/json',
            'x-csrf-token': window.CSRF_TOKEN_HEADER},
        body: JSON.stringify(user)
    } ).then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            console.error(`Error: ${response.status}`);
        }
    }).catch(e => console.error(`Error: ${JSON.stringify(e)}`));
}


export function logoutUser() {
    const url = `/api/userLogout`;
    return fetch(url, {
        mode: 'cors',
        credentials: 'same-origin',
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'x-csrf-token': window.CSRF_TOKEN_HEADER
        }
    }).then(response => {
        console.log(response);
        if (response.status === 200) {
            return response.json();
        } else {
            console.error(`Error: ${response.status}`);
        }
    }).catch(e => {
        console.log("Error");
        console.error(`Error: ${JSON.stringify(e)}`);
    });
}

//
// export function addUserDetail(user){
//     return ApiFetch("/user","POST",user);
//     // const url = `/api/user`;
//     // return fetch(url, {
//     //     mode: 'cors',
//     //     credentials: 'same-origin',
//     //     method: 'POST',
//     //     headers: {  'Content-Type': 'application/json',
//     //         'x-csrf-token': window.CSRF_TOKEN_HEADER},
//     //     body: JSON.stringify(user)
//     // } ).then(response => {
//     //     if (response.status === 200) {
//     //         return response.json();
//     //     } else {
//     //         console.error(`Error: ${response.status}`);
//     //     }
//     // }).catch(e => console.error(`Error: ${JSON.stringify(e)}`));
// }