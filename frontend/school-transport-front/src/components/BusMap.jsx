import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import L from "leaflet";
import "leaflet/dist/leaflet.css";

// Fix pour les icônes Leaflet
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png",
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png",
  shadowUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png",
});

// Icône personnalisée pour les bus
const busIcon = new L.Icon({
  iconUrl: "https://cdn-icons-png.flaticon.com/512/3448/3448339.png",
  iconSize: [40, 40],
  iconAnchor: [20, 40],
  popupAnchor: [0, -40],
});

const BusMap = ({ buses }) => {
  return (
    <MapContainer
      center={[33.5731, -7.5898]} // Casablanca
      zoom={13}
      style={{ height: "600px", width: "100%", borderRadius: "8px" }}
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
      />

      {buses.map((bus) => (
        <Marker
          key={bus.busId}
          position={[bus.latitude, bus.longitude]}
          icon={busIcon}
        >
          <Popup>
            <div style={{ textAlign: "center" }}>
              <strong style={{ fontSize: "16px", color: "#2c3e50" }}>
                🚌 {bus.busId}
              </strong>
              <hr style={{ margin: "8px 0" }} />
              <p style={{ margin: "4px 0" }}>
                📍 <strong>Lat:</strong> {bus.latitude.toFixed(4)}
              </p>
              <p style={{ margin: "4px 0" }}>
                📍 <strong>Lng:</strong> {bus.longitude.toFixed(4)}
              </p>
              <p style={{ margin: "4px 0", fontSize: "12px", color: "#7f8c8d" }}>
                🕒 {new Date(bus.timestamp).toLocaleString()}
              </p>
            </div>
          </Popup>
        </Marker>
      ))}
    </MapContainer>
  );
};

export default BusMap;