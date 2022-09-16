// API
// AIzaSyB3vyx08f3rFqpic0wsg5x7sVr-cwYy6hs

import React, { useEffect, useRef, useState } from "react";
import { Wrapper, Status } from "@googlemaps/react-wrapper";

const render = (status) => {
    if (status === Status.LOADING) return <h3>{status} ..</h3>;
    if (status === Status.FAILURE) return <h3>{status} ...</h3>;
    return null;
};

function MyMapComponent({ center, zoom, onPolygon }) {
    const ref = useRef();

    useEffect(() => {
        const map = new window.google.maps.Map(ref.current, {
            center,
            zoom
        });
        const drawingManager = new window.google.maps.drawing.DrawingManager({
            drawingMode: window.google.maps.drawing.OverlayType.POLYGON,
            drawingControl: false
        });

        drawingManager.setMap(map);
        window.google.maps.event.addListener(
            drawingManager,
            "overlaycomplete",
            function (event) {
                console.log(event);
                const polygonCoordinates = event.overlay.latLngs
                    .getArray()
                    .map((it) =>
                        it
                            .getArray()
                            .map((point) => [point.toJSON().lng, point.toJSON().lat])
                    );
                onPolygon(polygonCoordinates);
            }
        );
    }, []);

    return (
        <div
            style={{
                height: "90vh"
            }}
            ref={ref}
            id="map"
        />
    );
}

export default function MapWithGoogle() {
    const center = { lat: 42.360081, lng: -71.058884 };
    // latitude: 42.360081,
    //     longitude: -71.058884
    const zoom = 4;
    const [coords, setCoords] = useState([]);

    console.log("c=>", coords);

    return (
        <Wrapper
            libraries={["drawing"]}
            apiKey="AIzaSyB3vyx08f3rFqpic0wsg5x7sVr-cwYy6hs"
            render={render}
        >
            <MyMapComponent
                onPolygon={(polygonCoordinates) => {
                    console.log("onPolygon", polygonCoordinates);
                    setCoords([coords, polygonCoordinates]);
                }}
                center={center}
                zoom={zoom}
            />
            <pre>{JSON.stringify(coords, null, 2)}</pre>
        </Wrapper>
    );
}
