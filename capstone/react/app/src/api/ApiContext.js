import {useEffect, useState} from "react";

const baseUrl = `/api/`;

const ApiFetch = (url, method, body) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(null);

    useEffect( () => {
        async function init(){
            try {
                const response = await fetch(baseUrl + url,
                    {
                        mode: 'cors',
                        credentials: 'same-origin',
                        method: method,
                        headers: {
                            'Content-Type': 'application/json',
                            'x-csrf-token': window.CSRF_TOKEN_HEADER
                        },
                        body: JSON.stringify(body)
                    });
                if (response.ok){
                    const json = await response.json();
                    setData(json);
                } else {
                    throw response;
                }
            } catch (e) {
                setError(e);
            } finally {
                setLoading(false);
            }
        }
        init().then(data=>{return data;});
    }, [url]);
    return { data, error, loading };
}
export default ApiFetch;