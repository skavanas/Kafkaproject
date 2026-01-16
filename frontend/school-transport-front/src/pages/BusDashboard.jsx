import { useEffect, useState } from "react";
import { getBuses } from "../services/busService";
import BusMap from "../components/BusMap";
import BusInfoPanel from "../components/BusInfoPanel";
import NotificationPanel from "../components/NotificationPanel";

const BusDashboard = () => {
  const [buses, setBuses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [lastUpdate, setLastUpdate] = useState(new Date());
  const [selectedParentId, setSelectedParentId] = useState("parent-123");

  const loadBuses = async () => {
    const data = await getBuses();
    setBuses(data);
    setLastUpdate(new Date());
    setLoading(false);
  };

  useEffect(() => {
    loadBuses();
    const interval = setInterval(loadBuses, 5000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div style={styles.container}>
      <header style={styles.header}>
        <div>
          <h1 style={styles.title}>📍 Bus Tracking Dashboard</h1>
          <p style={styles.subtitle}>Suivi en temps réel des bus scolaires</p>
        </div>
        <div style={styles.updateInfo}>
          <span style={styles.liveIndicator}>🔴 LIVE</span>
          <span style={styles.lastUpdate}>
            Dernière mise à jour : {lastUpdate.toLocaleTimeString("fr-FR")}
          </span>
        </div>
      </header>

      {loading ? (
        <div style={styles.loading}>
          <div style={styles.spinner}></div>
          <p>Chargement des bus...</p>
        </div>
      ) : (
        <div style={styles.mainContent}>
          {/* Colonne gauche: Infos + Notifications */}
          <div style={styles.leftColumn}>
            <BusInfoPanel buses={buses} />
            
            <div style={styles.notificationSection}>
              <NotificationPanel parentId={selectedParentId} />
            </div>
          </div>

          {/* Colonne droite: Carte */}
          <div style={styles.rightColumn}>
            <BusMap buses={buses} />
          </div>
        </div>
      )}
    </div>
  );
};

const styles = {
  container: {
    padding: "30px",
    background: "#f0f2f5",
    minHeight: "100vh",
  },
  header: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: "30px",
    background: "white",
    padding: "25px 30px",
    borderRadius: "12px",
    boxShadow: "0 2px 10px rgba(0,0,0,0.1)",
  },
  title: {
    margin: 0,
    fontSize: "32px",
    color: "#2c3e50",
  },
  subtitle: {
    margin: "5px 0 0 0",
    fontSize: "14px",
    color: "#7f8c8d",
  },
  updateInfo: {
    display: "flex",
    flexDirection: "column",
    alignItems: "flex-end",
    gap: "8px",
  },
  liveIndicator: {
    background: "#e74c3c",
    color: "white",
    padding: "6px 18px",
    borderRadius: "20px",
    fontSize: "12px",
    fontWeight: "bold",
    animation: "pulse 2s infinite",
  },
  lastUpdate: {
    fontSize: "12px",
    color: "#7f8c8d",
  },
  mainContent: {
    display: "flex",
    gap: "20px",
  },
  leftColumn: {
    width: "35%",
    display: "flex",
    flexDirection: "column",
    gap: "20px",
  },
  rightColumn: {
    flex: 1,
  },
  notificationSection: {
    flex: 1,
  },
  loading: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    height: "400px",
  },
  spinner: {
    width: "50px",
    height: "50px",
    border: "5px solid #f3f3f3",
    borderTop: "5px solid #667eea",
    borderRadius: "50%",
    animation: "spin 1s linear infinite",
  },
};

export default BusDashboard;