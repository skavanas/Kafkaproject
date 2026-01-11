import { useEffect, useState } from "react";
import { getBuses } from "../services/busService";
import BusMap from "../components/BusMap";
import BusInfoPanel from "../components/BusInfoPanel";

const BusDashboard = () => {
  const [buses, setBuses] = useState([]);

  useEffect(() => {
    getBuses().then(setBuses);
  }, []);

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>📍 Bus Tracking Dashboard</h1>

      <div style={styles.content}>
        <BusInfoPanel buses={buses} />
        <div style={styles.mapContainer}>
          <BusMap buses={buses} />
        </div>
      </div>
    </div>
  );
};

const styles = {
  container: {
    padding: "20px"
  },
  title: {
    textAlign: "center",
    marginBottom: "20px"
  },
  content: {
    display: "flex",
    gap: "20px"
  },
  mapContainer: {
    width: "70%"
  }
};

export default BusDashboard;
