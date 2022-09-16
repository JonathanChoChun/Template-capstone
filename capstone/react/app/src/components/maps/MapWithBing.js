// API Key
// AvJgm3DKvQrS4gmbcqhvcSZD9CwbKdpnhA9cqVVOCa6LrrBaf4NkV7lhl9ZqswH7
import BingMapsReact from "bingmaps-react";
import {getMovieList} from "../../api/MovieApi";
import {useState} from "react";
import {Button} from "reactstrap";
const MapWithBing = () => {

    const handleClick = (event) => {
        if (event !== undefined) event.preventDefault();
        console.log("clicked");
        isShowMap(true);
    };
    const pushPin = {
        center: {
            latitude: 42.360081,
            longitude: -71.058884
        },
        options: {
            title: "Mt. Everest!!!",
            description: 'This is <b>pretty</b> cool...<a href="/" >home</a>',
        },

    }
    const [showMap, isShowMap] = useState(false);
    const pushPins = [pushPin];
    // const handleClick = (event) => {
    //
    // }
    return (
        <>
        <Button onClick={handleClick}>Show/Hide</Button>
            {showMap &&
                <BingMapsReact
                    bingMapsKey="AvJgm3DKvQrS4gmbcqhvcSZD9CwbKdpnhA9cqVVOCa6LrrBaf4NkV7lhl9ZqswH7"
                    height="500px"
                    mapOptions={{
                        navigationBarMode: "square",
                    }}
                    width="1200px"
                    viewOptions={{
                        center: {latitude: 42.360081, longitude: -71.058884},
                        mapTypeId: "aerial",
                    }}
                    pushPinsWithInfoboxes={pushPins}
                />
            }
        </>
    );
}
export default MapWithBing;