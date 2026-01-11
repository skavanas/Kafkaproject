import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";

const BusMap = ({ buses }) => {
  return (
    <MapContainer
      center={[33.5731, -7.5898]} // Casablanca
      zoom={12}
      style={{ height: "500px", width: "100%" }}
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />

      {buses.map((bus) => (
        <Marker
          key={bus.busId}
          position={[bus.latitude, bus.longitude]}
        >
          <Popup>
            <strong>Bus {bus.busId}</strong><br />
            {bus.latitude}, {bus.longitude}
          </Popup>
        </Marker>
      ))}
    </MapContainer>
  );
};

export default BusMap;
