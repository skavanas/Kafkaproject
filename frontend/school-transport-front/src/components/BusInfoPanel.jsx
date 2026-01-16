const BusInfoPanel = ({ buses }) => {
  return (
    <div style={styles.panel}>
      <h2 style={styles.header}>🚌 Bus en Temps Réel</h2>
      <p style={styles.count}>
        <strong>{buses.length}</strong> bus actif{buses.length > 1 ? "s" : ""}
      </p>

      <div style={styles.busContainer}>
        {buses.length === 0 ? (
          <div style={styles.emptyState}>
            <p>📭 Aucun bus détecté</p>
            <p style={styles.hint}>Envoyez une position via l'API</p>
          </div>
        ) : (
          buses.map((bus) => (
            <div key={bus.busId} style={styles.card}>
              <div style={styles.cardHeader}>
                <span style={styles.busId}>🚌 {bus.busId}</span>
                <span style={styles.status}>🟢 Actif</span>
              </div>

              <div style={styles.info}>
                <p>
                  <strong>📍 Latitude:</strong> {bus.latitude.toFixed(4)}
                </p>
                <p>
                  <strong>📍 Longitude:</strong> {bus.longitude.toFixed(4)}
                </p>
                <p style={styles.timestamp}>
                  🕒 {new Date(bus.timestamp).toLocaleString("fr-FR")}
                </p>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

const styles = {
  panel: {
    width: "30%",
    padding: "20px",
    background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
    color: "white",
    overflowY: "auto",
    borderRadius: "12px",
    boxShadow: "0 10px 30px rgba(0,0,0,0.2)",
    maxHeight: "600px",
  },
  header: {
    margin: "0 0 10px 0",
    fontSize: "24px",
    fontWeight: "bold",
  },
  count: {
    fontSize: "14px",
    marginBottom: "20px",
    opacity: 0.9,
  },
  busContainer: {
    display: "flex",
    flexDirection: "column",
    gap: "15px",
  },
  card: {
    background: "rgba(255, 255, 255, 0.15)",
    backdropFilter: "blur(10px)",
    padding: "15px",
    borderRadius: "10px",
    border: "1px solid rgba(255, 255, 255, 0.2)",
    transition: "transform 0.2s, box-shadow 0.2s",
    cursor: "pointer",
  },
  cardHeader: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: "12px",
  },
  busId: {
    fontSize: "18px",
    fontWeight: "bold",
  },
  status: {
    fontSize: "12px",
    background: "rgba(46, 213, 115, 0.3)",
    padding: "4px 10px",
    borderRadius: "12px",
  },
  info: {
    fontSize: "13px",
    lineHeight: "1.8",
  },
  timestamp: {
    fontSize: "11px",
    opacity: 0.8,
    marginTop: "8px",
  },
  emptyState: {
    textAlign: "center",
    padding: "40px 20px",
    opacity: 0.7,
  },
  hint: {
    fontSize: "12px",
    marginTop: "10px",
  },
};

export default BusInfoPanel;