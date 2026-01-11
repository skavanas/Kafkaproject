const BusInfoPanel = ({ buses }) => {
  return (
    <div style={styles.panel}>
      <h2>🚌 Bus Information</h2>

      {buses.map((bus) => (
        <div key={bus.busId} style={styles.card}>
          <p><strong>ID:</strong> {bus.busId}</p>
          <p><strong>Latitude:</strong> {bus.latitude}</p>
          <p><strong>Longitude:</strong> {bus.longitude}</p>
          <p><strong>Updated:</strong> {bus.timestamp}</p>
        </div>
      ))}
    </div>
  );
};

const styles = {
  panel: {
    width: "30%",
    padding: "20px",
    background: "#f5f5f5",
    overflowY: "auto",
    borderRadius: "8px"
  },
  card: {
    background: "white",
    padding: "10px",
    marginBottom: "10px",
    borderRadius: "6px",
    boxShadow: "0 2px 5px rgba(0,0,0,0.1)"
  }
};

export default BusInfoPanel;
